package com.example.pageapp.fragments.classes;

public class currentUser {
    private static currentUser instance;
    private String name;

    // Private constructor to prevent instantiation
    private currentUser() { }

    // Public method to get the single instance (Thread-safe using synchronized)
    public static synchronized currentUser getInstance() {
        if (instance == null) {
            instance = new currentUser();
        }
        return instance;
    }

    // Getter and Setter for the name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
