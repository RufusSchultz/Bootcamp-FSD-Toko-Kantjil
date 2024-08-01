package com.backend.tokokantjil.helpers;

import com.backend.tokokantjil.enumerations.Status;
import com.backend.tokokantjil.exceptions.EnumerationValueIsUnprocessableException;

import java.util.Arrays;
import java.util.stream.Stream;

public class OrderStatusInputChecker {
    public static void orderStatusInputChecker(String statusInput) {
        String[] statusList = Stream.of(Status.values()).map(Status::name).toArray(String[]::new);
        boolean isStatusInputAStatus = false;

        for (String string : statusList) {
            if (string.equals(statusInput)) {
                isStatusInputAStatus = true;
                break;
            }
        }
        if(!isStatusInputAStatus){
            throw new EnumerationValueIsUnprocessableException("Status value has to be one of " + Arrays.toString(statusList));
        }
    }
}
