package com.example.areaselectorlibrary;

public class AreaBean {
    public String name;
    public String id;

    public AreaBean(String id, String name) {
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
