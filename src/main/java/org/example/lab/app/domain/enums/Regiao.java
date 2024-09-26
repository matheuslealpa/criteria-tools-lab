package org.example.lab.app.domain.enums;

public enum Regiao {
    NORTE("Norte"),
    NORDESTE("Nordeste"),
    CENTRO_OESTE("Centro Oeste"),
    SUDESTE("Sudeste"),
    SUL("Sul");

    private String descricao;

    Regiao(String descricao) {
        this.descricao = descricao;
    }
}
