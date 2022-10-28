package com.codedidier.mspatient.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entité qui représente un patient dans la base de donnée
 */
@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Prénom
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Nom de famille
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Date de naissance
     */
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    /**
     * Sex
     */
    @Column(nullable = false)
    private char gender;

    /**
     * Adresse du domicile
     */
    @Column
    private String address;

    /**
     * Numéro de téléphone
     */
    @Column
    private String phone;
}
