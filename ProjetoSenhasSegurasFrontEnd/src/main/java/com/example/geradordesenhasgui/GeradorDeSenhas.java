package com.example.geradordesenhasgui;

import java.security.SecureRandom;

public class GeradorDeSenhas {
    private static final String CARACTERES = "abcdefghijklmnopqrstuvwyzABCDEFHIJKLMNOPQRSTUVWYZ0123456789!@#$%*()_+-=[]|,./?><";

    public static String gerarSenha(int comprimento){
        SecureRandom geradorDeNumAleatorio = new SecureRandom();
        StringBuilder senha = new StringBuilder(comprimento);

        for (int i = 0; i < comprimento; i++){
            int indice = geradorDeNumAleatorio.nextInt(CARACTERES.length());
            senha.append(CARACTERES.charAt(indice));

        }

        return senha.toString();

    }
}
