package com.grv.restproject.socialNetwork.user.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by gaurav on 15/11/18.
 */
@ApiModel(description = "This is a member of social network")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should be at least 2 characters long")
    @ApiModelProperty(notes = "Name should be at least 2 characters long")
    private String name;

    @Past(message = "Birthdate should be in past.")
    @ApiModelProperty(notes = "Birthdate should be in past.")
    private Date dob;


    public User() {
    }

    public User(Integer id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
