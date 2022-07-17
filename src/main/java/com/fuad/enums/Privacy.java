package com.fuad.enums;


public enum Privacy {
    PUBLIC("Public"),
    PRIVATE("Private");


    public final String label;
    Privacy(String label) {
        this.label = label;
    }
}
