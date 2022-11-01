package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        String[] data = readDataToString(file).split("\\r\\n|[\\r\\n ]");

        for (int i = 0; i < data.length; i++) {
            switch (data[i]) {
                case "Name:":
                    profile.setName(data[i + 1]);
                    break;
                case "Age:":
                    profile.setAge(Integer.parseInt(data[i + 1]));
                    break;
                case "Email:":
                    profile.setEmail(data[i + 1]);
                    break;
                case "Phone:":
                    profile.setPhone(Long.parseLong(data[i + 1]));
                    break;
                default:
            }
        }

        return profile;
    }

    private String readDataToString(File file) {
        StringBuilder data = new StringBuilder();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            FileChannel inChannel = randomAccessFile.getChannel();
            long fileSize = randomAccessFile.length();

            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            inChannel.read(buffer);
            buffer.flip();

            for (int i = 0; i < fileSize; i++) {
                data.append((char) buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
