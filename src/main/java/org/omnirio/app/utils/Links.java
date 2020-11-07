package org.omnirio.app.utils;

public enum Links {
    CREATE_USER("POST -> /customer/"), GET_USER("GET -> /customer/{customerId}"),
    UPDATE_USER("PUT -> /customer/{customerId}"), DELETE_USER("/customer/{customerId}");

    String link;

    Links (String s) {
        this.link = s;
    }

    public String getLink () {
        return link;
    }
}
