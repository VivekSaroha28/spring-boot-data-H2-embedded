package com.iamvickyav.springboot.SpringBootRestWithH2.model;

import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@Getter
public class Employee {

    @Column(name = "id")
    @Id
    Integer id;

    @Column(name = "name")
    String name;
}
