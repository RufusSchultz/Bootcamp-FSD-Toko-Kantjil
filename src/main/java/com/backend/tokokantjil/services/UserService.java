package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.UserInputDto;
import com.backend.tokokantjil.dtos.outputs.UserOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.UserMapper;
import com.backend.tokokantjil.models.Role;
import com.backend.tokokantjil.models.User;
import com.backend.tokokantjil.repositories.RoleRepository;
import com.backend.tokokantjil.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserOutputDto> getAllUsers() {
        List<UserOutputDto> list = new ArrayList<>();
        for (User i: this.userRepository.findAll()) {
            list.add(UserMapper.fromUserToUserOutputDto(i));
        }
        return list;
    }

    public UserOutputDto getUserById(String id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user with username " + id + " found."));
        return UserMapper.fromUserToUserOutputDto(user);
    }

    public UserOutputDto createUser(UserInputDto userInputDto) {
        User user = UserMapper.fromUserInputDtoToUserWithoutRoles(userInputDto);
        Set<Role> userRoles = user.getRoles();
        for (String rolename : userInputDto.roles) {
            Optional<Role> optionalRoles = roleRepository.findById("ROLE_" + rolename);
            optionalRoles.ifPresent(userRoles::add);
        }
        this.userRepository.save(user);

        return UserMapper.fromUserToUserOutputDto(user);
    }

    public void deleteUser(String id) {
        if(this.userRepository.findById(id).isPresent()) {
            this.userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user with username " + id + " found.");
        }
    }

    public UserOutputDto updateUser(String id, UserInputDto userInputDto) {
        User oldUser = this.userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user with username " + id + " found."));
        User userUpdate = UserMapper.fromUserInputDtoToUserWithoutRoles(userInputDto);

        Set<Role> userRoles = userUpdate.getRoles();
        for (String rolename : userInputDto.roles) {
            Optional<Role> optionalRoles = roleRepository.findById("ROLE_" + rolename);
            optionalRoles.ifPresent(userRoles::add);
        }

        User newUser = this.userRepository.save(UserMapper.fromUserToUpdatedUser(oldUser, userUpdate));

        return UserMapper.fromUserToUserOutputDto(newUser);
    }
}
