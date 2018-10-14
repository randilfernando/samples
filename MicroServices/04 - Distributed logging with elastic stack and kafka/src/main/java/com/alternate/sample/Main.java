package com.alternate.sample;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";

        while (!"exit".equals(input)) {
            System.out.print("Enter log message: ");
            input = scanner.nextLine();
            LOGGER.info(input);
        }
    }
}
