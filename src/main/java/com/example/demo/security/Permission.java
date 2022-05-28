package com.example.demo.security;


public enum Permission {
    DATA_READ("can read data"),
    DATA_WRITE("can read,write and edit(delete,etc)");

    private final String permission_info;

    Permission(String permission_info) {
        this.permission_info = permission_info;
    }

    public String getPermission_info() {
        return permission_info;
    }


}