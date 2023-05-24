package com.example.zdarzenia_w_springu_1_2.transformer;

public class FileTransformer {
    public String getData(String fileName){
        StringBuilder result = new StringBuilder();
        int index = fileName.lastIndexOf("\\") + 1;
        String name = fileName.substring(index);
        result.append(name).append("\n");
        return result.toString();
    }
}
