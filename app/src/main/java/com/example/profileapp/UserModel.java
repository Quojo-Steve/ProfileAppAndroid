// UserModel.java
package com.example.profileapp;

public class UserModel {
    String name;
    String age;
    String index;
    String programme;
    String dob;

    public UserModel(String name, String age, String index, String programme, String dob) {
        this.name = name;
        this.age = age;
        this.index = index;
        this.programme = programme;
        this.dob = dob;
    }

    public String getName() { return name; }
    public String getAge() { return age; }
    public String getIndex() { return index; }
    public String getProgramme() { return programme; }
    public String getDob() { return dob; }
}
