package com.sda.example3.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class EmployeeDeleteDTO {
    @NotEmpty
    @Length(min = 3)
    private String firstName;
    @NotEmpty
    @Length(min = 3)
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
