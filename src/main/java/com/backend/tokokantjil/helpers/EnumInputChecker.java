package com.backend.tokokantjil.helpers;

import com.backend.tokokantjil.exceptions.UserInputIsUnprocessableException;

import java.util.Arrays;

public class EnumInputChecker {
    public static void enumInputChecker(String[] enumList, String enumInput) {
        boolean isInputCorrectEnumValue = false;

        for (String string : enumList) {
            if (string.equals(enumInput)) {
                isInputCorrectEnumValue = true;
                break;
            }
        }
        if (!isInputCorrectEnumValue) {
            throw new UserInputIsUnprocessableException("Unprocessable value " + enumInput + ". Value has to be one of " + Arrays.toString(enumList));
        }
    }
}
