package com.fiap.guardian_bff.model;

import lombok.Data;

@Data
public class Localizacao {
    private int id;
    private String cidade;
    private String estado;
    private String regiao;
    private String pais;
    private double latitude;
    private double longitude;
}