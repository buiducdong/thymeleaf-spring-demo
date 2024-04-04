package com.example.springthymeleaf.bean.jpa;

import com.example.springthymeleaf.bean.jpa.common.CommonEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_user")
public class UserEntity extends CommonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    @JsonProperty("userName")
    private String userName;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "user_avatar")
    @JsonProperty("userAvatar")
    private String userAvatar;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    public UserEntity(String userName, String email, String userAvatar, String password) {
        this.userName = userName;
        this.email = email;
        this.userAvatar = userAvatar;
        this.password = password;
    }

}
