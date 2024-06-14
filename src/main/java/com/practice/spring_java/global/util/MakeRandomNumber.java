package com.practice.spring_java.global.util;

import java.util.Random;

public class MakeRandomNumber {

    //임의의 6자리 양수를 반환합니다.
    public static String makeRandomNumber() {
        Random r = new Random();
        String randomNumber = "";
        for (int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }

        return randomNumber;
    }
}
