package com.sda.example3.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class EmployeeUpdateDTO {
    @NotEmpty
    @Length(min = 3)
    private String firstName1;
    @NotEmpty
    @Length(min = 3)
    private String lastName1;
    @NotEmpty
    @Length(min = 3)
    private String firstName2;
    @NotEmpty
    @Length(min = 3)
    private String lastName2;

    public String getFirstName1() {
        return firstName1;
    }

    public void setFirstName1(String firstName1) {
        this.firstName1 = firstName1;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }
}
