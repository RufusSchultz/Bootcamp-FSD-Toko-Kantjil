package com.backend.tokokantjil.helpers;

import com.backend.tokokantjil.exceptions.UserInputIsUnprocessableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationChecker {

    public static void validationChecker(BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            throw new UserInputIsUnprocessableException(sb.toString());
        }
    }
}
