package com.example.miss.model;

public class Children {
    private String path;
    private String component;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Children{" +
                "path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
