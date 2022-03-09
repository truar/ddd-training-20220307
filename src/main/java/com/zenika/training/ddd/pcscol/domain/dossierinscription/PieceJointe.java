package com.zenika.training.ddd.pcscol.domain.dossierinscription;

public class PieceJointe {
    private final String url;
    private final PieceJointeType type;

    public PieceJointe(String url, PieceJointeType type) {
        this.url = url;
        this.type = type;
    }

    public PieceJointeType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
