package org.example;


import java.util.Random;

public class User {
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public static User getRandom() {
        Random random = new Random();
        return new User(
                "Иван" + random.nextInt(10000),
                "ivan" + random.nextInt(10000) + "@example.com",
                "Secret123"
        );
    }
}
