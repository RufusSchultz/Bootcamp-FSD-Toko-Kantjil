package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.mappers.UserPhotoMapper;
import com.backend.tokokantjil.dtos.outputs.UserPhotoOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.User;
import com.backend.tokokantjil.models.UserPhoto;
import com.backend.tokokantjil.repositories.UserPhotoRepository;
import com.backend.tokokantjil.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPhotoService {
    private final UserPhotoRepository userPhotoRepository;
    private final UserRepository userRepository;

    public UserPhotoService(UserPhotoRepository userPhotoRepository, UserRepository userRepository) {
        this.userPhotoRepository = userPhotoRepository;
        this.userRepository = userRepository;
    }

    public UserPhotoOutputDto storeFile(MultipartFile file, String url, String userId) throws IOException {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("No user with username " + userId + " found."));
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        UserPhoto userPhoto = new UserPhoto(originalFileName, contentType, url, bytes);
        userPhoto.setTitle(user.getUsername() + "_photo");
        userPhotoRepository.save(userPhoto);

        return UserPhotoMapper.fromUserPhotoToUserPhotoOutputDto(userPhoto);
    }

    public void deleteAllUnusedPhotos() {
        List<Long> usedPhotosList = new ArrayList<>();
        List<User> userList = this.userRepository.findAll();
        List<UserPhoto> allPhotosList = this.userPhotoRepository.findAll();

        for (User user : userList) {
            if (user.getUserPhoto() != null) {
                usedPhotosList.add(user.getUserPhoto().getId());
            }
        }

        for (UserPhoto userPhoto : allPhotosList) {
            if (!usedPhotosList.contains(userPhoto.getId())) {
                this.userPhotoRepository.deleteById(userPhoto.getId());
            }
        }
    }
}
