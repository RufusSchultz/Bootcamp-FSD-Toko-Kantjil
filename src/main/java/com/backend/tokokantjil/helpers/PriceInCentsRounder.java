package com.backend.tokokantjil.helpers;

public class PriceInCentsRounder {
    public static double priceInCentsRounder (double price) {
        return (double) Math.round(price * 100) /100;
    }


}
