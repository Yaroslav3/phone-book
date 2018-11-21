package com.phonebook.phonebook.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@DynamicUpdate
@Builder
@Table(name = "phone_book")
public class PhoneBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_phone_book")
    private long id;


    @NotNull
    @Size(min = 4, message = "name must by at 4 characters!")
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 4, message = "name must by at 4 characters!")
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Size(min = 4, message = "name must by at 4 characters!")
    @Column(name = "patronymic")
    private String patronymic;

    @NotEmpty(message = "Phone should not be blank.")
    @Size(min = 12, max = 12, message = "in field must be 12 digits!")
    @Column(name = "phoneMobil")
    private String phoneMobile;

    @Column(name = "phoneHome")
    private String phoneHome;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


}
