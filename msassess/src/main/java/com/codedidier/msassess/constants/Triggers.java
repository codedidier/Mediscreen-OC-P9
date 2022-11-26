package com.codedidier.msassess.constants;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenant les mots clés, pour l'évaluation des risques.
 */
public class Triggers {

    public static final List<String> TRIGGERS = new ArrayList<String>() {
        {
            add("hémoglobine a1c");

            add("microalbumine");

            add("taille");

            add("poids");

            add("fumeur");

            add("anormal");

            add("cholestérol");

            add("vertige");

            add("rechute");

            add("réaction");

            add("anticorps");
        }

        ;
    };
}