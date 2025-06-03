package com.koleksiyoner.entities;

import com.koleksiyoner.enums.EGender;
import com.koleksiyoner.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String firstname;
    @Enumerated(EnumType.STRING)
    private EGender eGender = EGender.UNSPECIFIED;
    private boolean iWantToReceiveMail = false;
    private boolean iWantToReceiveSms = false;
    @NotBlank
    private String lastname;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_to_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private EStatus eStatus;
    private String username;

}