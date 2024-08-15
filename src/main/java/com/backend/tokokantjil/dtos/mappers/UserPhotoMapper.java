package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.outputs.UserPhotoOutputDto;
import com.backend.tokokantjil.models.UserPhoto;

public class UserPhotoMapper {
    public static UserPhotoOutputDto fromUserPhotoToUserPhotoOutputDto(UserPhoto userPhoto) {
        UserPhotoOutputDto userPhotoOutputDto = new UserPhotoOutputDto();

        userPhotoOutputDto.setId(userPhoto.getId());
        userPhotoOutputDto.setTitle(userPhoto.getTitle());
        userPhotoOutputDto.setUrl(userPhoto.getUrl());
        userPhotoOutputDto.setContentType(userPhoto.getContentType());
        userPhotoOutputDto.setContents(userPhoto.getContents());

        return userPhotoOutputDto;
    }

    public static UserPhoto formUserPhotoOutputDtoToUserPhoto(UserPhotoOutputDto userPhotoOutputDto) {
        UserPhoto userPhoto = new UserPhoto();

        userPhoto.setId(userPhotoOutputDto.getId());
        userPhoto.setTitle(userPhotoOutputDto.getTitle());
        userPhoto.setUrl(userPhotoOutputDto.getUrl());
        userPhoto.setContentType(userPhoto.getContentType());
        userPhoto.setContents(userPhotoOutputDto.getContents());

        return userPhoto;
    }
}
