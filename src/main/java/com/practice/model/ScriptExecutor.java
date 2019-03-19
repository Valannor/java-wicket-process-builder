package com.practice.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ScriptExecutor {

    public static List<String> execute(String... command) {

        List<String> result = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s;
            while ((s = reader.readLine()) != null) {
                result.add(s);
            }

            System.out.println("Process finished with exit code " + process.waitFor());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
