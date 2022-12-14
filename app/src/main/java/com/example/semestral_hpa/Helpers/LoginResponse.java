package com.example.semestral_hpa.Helpers;

import com.example.semestral_hpa.Models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("usuario")
    @Expose
    private User usuario;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }


}
