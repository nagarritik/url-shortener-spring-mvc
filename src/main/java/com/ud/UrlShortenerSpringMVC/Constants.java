package com.ud.UrlShortenerSpringMVC;

import java.util.Random;

public class Constants {
    public static String shortCodeGenerator(){
        StringBuilder stringBuffer = new StringBuilder();
        String masterString = "qwertyuiopasdfghjklzxcvbnm0123698547QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();

        for (int i=0;i<8;i++){
            stringBuffer.append(masterString.charAt(random.nextInt(0,masterString.length())));
        }

        return stringBuffer.toString();
    }
}
