package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.dtos.inputs.UserInputDto;
import com.backend.tokokantjil.dtos.outputs.UserOutputDto;
import com.backend.tokokantjil.dtos.outputs.UserPhotoOutputDto;
import com.backend.tokokantjil.services.UserPhotoService;
import com.backend.tokokantjil.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import static com.backend.tokokantjil.helpers.ValidationChecker.validationChecker;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService UserService;
    private final UserPhotoService userPhotoService;

    public UserController(UserService UserService, UserPhotoService userPhotoService) {
        this.UserService = UserService;
        this.userPhotoService = userPhotoService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(UserService.getAllUsers(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        UserOutputDto userOutputDto = UserService.getUserById(id, userDetails);

        if (userOutputDto.getUsername() != null){
            return ResponseEntity.ok(userOutputDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInputDto userInputDto, BindingResult br) {
            if (validationChecker(br) == null) {
                UserOutputDto userOutputDto = UserService.createUser(userInputDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + userOutputDto.getUsername()).toUriString());
                return ResponseEntity.created(uri).body(userOutputDto);
            } else {
                return validationChecker(br);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        UserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable String id, @RequestBody UserInputDto userInputDto, BindingResult br) {
            if (validationChecker(br) == null) {
                UserOutputDto userOutputDto = UserService.updateUser(id, userInputDto);
                return ResponseEntity.ok(userOutputDto);
            } else {
                return validationChecker(br);
            }
    }

    @PostMapping("{id}/photo")
    public ResponseEntity<UserOutputDto> setUserPhoto(@PathVariable String id, @RequestBody MultipartFile file) throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/")
                .path(Objects.requireNonNull(id))
                .path("/photo")
                .toUriString();

        UserOutputDto userOutputDto = UserService.addPhotoToUser(id, userPhotoService.storeFile(file, url, id));

        return ResponseEntity.created(URI.create(url)).body(userOutputDto);
    }

    @GetMapping("{id}/photo")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String id) {
        UserPhotoOutputDto userPhotoOutputDto = UserService.getPhotoFromUser(id);
        MediaType mediaType;

        try {
            mediaType = MediaType.parseMediaType(userPhotoOutputDto.getContentType());
        } catch (InvalidMediaTypeException ignore) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + userPhotoOutputDto.getTitle()).body(userPhotoOutputDto.getContents());
    }

    @DeleteMapping("/photos")
    public ResponseEntity<String> deleteAllUnusedPhotos() {
        userPhotoService.deleteAllUnusedPhotos();
        return ResponseEntity.noContent().build();
    }
}
