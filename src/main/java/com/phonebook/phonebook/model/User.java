package com.phonebook.phonebook.model;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@DynamicUpdate
@Builder
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @NotNull
    @Size(min = 5, message = "name must by at 5 characters!")
    @Column(name = "login")
    private String login;

    @NotNull
    @Size(min = 5, message = "name must by at 5 characters!")
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(min = 5, message = "name must by at 5 characters!")
    @Column(name = "name")
    private String name;

    @Size(min = 5, message = "name must by at 5 characters!")
    @Column(name = "surname")
    private String surname;

    @Size(min = 5, message = "name must by at 5 characters!")
    @Column(name = "patronymic")
    private String patronymic;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PhoneBook> phoneBooks;


    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public String getUsername() {
        return getName();
    }

}
