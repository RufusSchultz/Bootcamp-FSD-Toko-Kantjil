package com.backend.tokokantjil.helpers;

import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.exceptions.EnumerationValueIsUnprocessableException;

import java.util.Arrays;
import java.util.stream.Stream;

public class ProductStateInputChecker {
    public static void productStateInputChecker(String stateInput) {
        String[] stateList = Stream.of(State.values()).map(State::name).toArray(String[]::new);
        boolean isStateInputAState = false;

        for (String string : stateList) {
            if (string.equals(stateInput)) {
                isStateInputAState = true;
                break;
            }
        }
        if(!isStateInputAState){
            throw new EnumerationValueIsUnprocessableException("State value has to be one of " + Arrays.toString(stateList));
        }
    }
}
