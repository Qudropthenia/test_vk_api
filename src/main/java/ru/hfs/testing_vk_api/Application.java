package ru.hfs.testing_vk_api;

import java.io.IOException;
import java.util.logging.LogManager;

public class Application {
    public static void main(String[] args) {
        try {
            LogManager
                    .getLogManager()
                    .readConfiguration(
                            Application.class.getResourceAsStream("/logging.properties")
                    );
            new Testing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
