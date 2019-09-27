package com.sda.example3.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class EmployeeFindByNameDTO {
    @NotEmpty
    @Length(min = 3)
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
