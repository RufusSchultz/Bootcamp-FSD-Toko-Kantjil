package com.servicedto.techiteasy.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationChecker {

    public static ResponseEntity<String> validationCheckToNullOrResponse(BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.unprocessableEntity().body(sb.toString());
        } else {
            return null;
        }
    }

}
