// UserModel.java
package com.example.profileapp;

public class UserModel {
    String name;
    String age;
    String index;

    public UserModel(String name, String age, String index) {
        this.name = name;
        this.age = age;
        this.index = index;
    }

    public String getName() { return name; }
    public String getAge() { return age; }
    public String getIndex() { return index; }
}
