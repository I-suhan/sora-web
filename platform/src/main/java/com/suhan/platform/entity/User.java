package com.suhan.platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "u_name")
    private String name;

    @Column(name = "u_age")
    private Integer age;

    @Column(name = "u_gender")
    private Gender gender;

    @OneToMany
    private List<User> friends;
}
