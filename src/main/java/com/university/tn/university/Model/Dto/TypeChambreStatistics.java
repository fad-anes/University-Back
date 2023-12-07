package com.university.tn.university.Model.Dto;

public class TypeChambreStatistics {

    private String blocName;
    private long countChambresSimples;
    private long countChambresDoubles;
    private long countChambresTriples;

    public String getBlocName() {
        return blocName;
    }

    public void setBlocName(String blocName) {
        this.blocName = blocName;
    }

    public long getCountChambresSimples() {
        return countChambresSimples;
    }

    public void setCountChambresSimples(long countChambresSimples) {
        this.countChambresSimples = countChambresSimples;
    }

    public long getCountChambresDoubles() {
        return countChambresDoubles;
    }

    public void setCountChambresDoubles(long countChambresDoubles) {
        this.countChambresDoubles = countChambresDoubles;
    }

    public long getCountChambresTriples() {
        return countChambresTriples;
    }

    public void setCountChambresTriples(long countChambresTriples) {
        this.countChambresTriples = countChambresTriples;
    }
}
