package com.codedidier.msassess.model;

import lombok.Data;

import java.time.LocalDate;

    /**
     * Données d'un patient
    */
    @Data
    public class PatientModel {

        /**
         * ID MySQL
         */
        private long id;

        /**
         * Nom de famille
         */
        private String firstName;

        /**
         * Prénom
         */
        private String lastName;

        /**
         * Date de naissance
         */
        private LocalDate dateOfBirth;

        /**
         * Sexe
         */
        private char gender;

        /**
         * Adresse du domicile
         */
        private String address;

        /**
         * Numéro de téléphone
         */
        private String phone;

        }
