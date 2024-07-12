package com.alura.foro_hub.DtoGetData.user;

import com.alura.foro_hub.models.Profile;

public record DtoCreateUserToDatabase (
        String username,
        String email,
        String passwordEncrypted,
        Profile typeOfProfile){
}
