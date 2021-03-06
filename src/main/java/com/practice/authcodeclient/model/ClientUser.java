package com.practice.authcodeclient.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String accessToken;

    private Calendar accessTokenValidity;

    private String refreshToken;

    @Transient
    private List<Entry> entries = new ArrayList<>();

}
