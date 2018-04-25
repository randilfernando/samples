package com.alternate.sample.services;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String getGreetingText(String name) {
        return "Hello, " + name + ". Welcome to the server";
    }
}
