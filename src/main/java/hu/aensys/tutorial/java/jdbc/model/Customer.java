package hu.aensys.tutorial.java.jdbc.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Customer {

    private Long id;

    private String fullName;

    private Date birthDate;

    private Address address = new Address();

}
