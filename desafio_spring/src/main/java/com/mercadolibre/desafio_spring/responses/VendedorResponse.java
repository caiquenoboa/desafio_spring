package com.mercadolibre.desafio_spring.responses;

public class VendedorResponse {
    private int id;
    private String name;
    private int followersCount;

    public VendedorResponse(int id, String name, int followersCount) {
        this.id = id;
        this.name = name;
        this.followersCount = followersCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
}
