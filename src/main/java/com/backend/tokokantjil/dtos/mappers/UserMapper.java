package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.UserInputDto;
import com.backend.tokokantjil.dtos.outputs.UserOutputDto;
import com.backend.tokokantjil.models.User;

public class UserMapper {
    public static User fromUserInputDtoToUser(UserInputDto userInputDto) {
        User user = new User();

        user.setUsername(userInputDto.username);
        user.setFirstName(userInputDto.firstName);
        user.setLastName(userInputDto.lastName);
        user.setEmail(userInputDto.email);
        user.setPhoneNumber(Long.valueOf(userInputDto.phoneNumber));
        user.setNotes(userInputDto.notes);
        user.setSalary(userInputDto.salary);

        return user;
    }

    public static UserOutputDto fromUserToUserOutputDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();

        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setRoles(user.getRoles());
        userOutputDto.setFirstName(user.getFirstName());
        userOutputDto.setLastName(user.getLastName());
        userOutputDto.setEmail(user.getEmail());
        userOutputDto.setPhoneNumber(user.getPhoneNumber());
        userOutputDto.setSalary(user.getSalary());
        userOutputDto.setNotes(user.getNotes());
        if (user.getUserPhoto() != null) {
            userOutputDto.setUserPhoto(user.getUserPhoto());
        }

        return userOutputDto;
    }

    public static User fromUserToUpdatedUser(User user, User userUpdate) {

        user.setUsername(userUpdate.getUsername());
        user.setPassword(userUpdate.getPassword());
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setEmail(userUpdate.getEmail());
        user.setPhoneNumber(userUpdate.getPhoneNumber());
        user.setSalary(userUpdate.getSalary());
        user.setNotes(userUpdate.getNotes());

        return user;
    }
}
