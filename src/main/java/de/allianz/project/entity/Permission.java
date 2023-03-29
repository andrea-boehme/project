package de.allianz.project.entity;

public enum Permission {

    TODO_READ("todo: read"),
    TODO_READ_ALL("todo: read_all"),

    TODO_CREATE("todo: create"),

    TODO_UPDATE("todo: update"),

    TODO_DELETE("todo: delete");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}