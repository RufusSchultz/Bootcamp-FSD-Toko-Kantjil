package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.UserInputDto;
import com.backend.tokokantjil.dtos.outputs.UserOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.UserMapper;
import com.backend.tokokantjil.models.Role;
import com.backend.tokokantjil.models.User;
import com.backend.tokokantjil.repositories.RoleRepository;
import com.backend.tokokantjil.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public List<UserOutputDto> getAllUsers() {
        List<UserOutputDto> list = new ArrayList<>();
        for (User i : this.userRepository.findAll()) {
            list.add(UserMapper.fromUserToUserOutputDto(i));
        }
        return list;
    }

    public UserOutputDto getUserById(String id, UserDetails userDetails) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user with username " + id + " found."));
        UserOutputDto userOutputDto = new UserOutputDto();

        if (userDetails.getUsername().equals(user.getUsername()) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            userOutputDto = UserMapper.fromUserToUserOutputDto(user);
        }
        return userOutputDto;
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User user = UserMapper.fromUserInputDtoToUser(userInputDto);
        user.setPassword(encoder.encode(userInputDto.password));
        Set<Role> userRoles = user.getRoles();
        for (String rolename : userInputDto.roles) {
            Optional<Role> optionalRoles = roleRepository.findById("ROLE_" + rolename);
            optionalRoles.ifPresent(userRoles::add);
        }
        this.userRepository.save(user);

        return UserMapper.fromUserToUserOutputDto(user);
    }

    public void deleteUser(String id) {
        if (this.userRepository.findById(id).isPresent()) {
            this.userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user with username " + id + " found.");
        }
    }

    public UserOutputDto updateUser(String id, UserInputDto userInputDto) {
        User oldUser = this.userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user with username " + id + " found."));
        User userUpdate = UserMapper.fromUserInputDtoToUser(userInputDto);

        userUpdate.setPassword(encoder.encode(userInputDto.password));
        Set<Role> userRoles = userUpdate.getRoles();
        for (String rolename : userInputDto.roles) {
            Optional<Role> optionalRoles = roleRepository.findById("ROLE_" + rolename);
            optionalRoles.ifPresent(userRoles::add);
        }

        User newUser = this.userRepository.save(UserMapper.fromUserToUpdatedUser(oldUser, userUpdate));

        return UserMapper.fromUserToUserOutputDto(newUser);
    }
}
