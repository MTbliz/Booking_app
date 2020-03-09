package com.example.booking_app.model;

import javax.validation.constraints.Pattern;

public class User {
    @Pattern(regexp="^[A-ZŻŹĆĄŚĘŁÓŃ]([a-zżźćńółęąś]){1,}([a-zżźćńółęąś])$",message="FirstName length must be at least 3 characters and starts from Capital letter.")
    private String firstName;
    @Pattern(regexp="^[A-ZŻŹĆĄŚĘŁÓŃ]([a-zżźćńółęąś]){1,}([a-zżźćńółęąś])$|^[A-ZŻŹĆĄŚĘŁÓŃ]([a-zżźćńółęąś]){0,}[-][A-ZŻŹĆĄŚĘŁÓŃ]([a-zżźćńółęąś])*",message="LastName length must be at least 3 characters, contains only letters or letters with single dash (two prats should start with capital letter)")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
