package com.epam.mjc.nio;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Profile profile = fileReader.getDataFromFile(new File("src/main/resources/Profile.txt"));
        System.out.println(profile);
    }
}
