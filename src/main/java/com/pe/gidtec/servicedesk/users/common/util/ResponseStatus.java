package com.pe.gidtec.servicedesk.users.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    OK("00", "la solicitud ha tenido éxito."),
    ERROR_SAVE_USER("01", "Ocurrió un error al intentar guardar el usuario."),
    BAD_ID_USER_NOT_EXISTS("02","El identificador del usuario proporcionado no existe."),
    ERROR_GET_USER("03","Ocurrió un error al intentar listar los usuarios"),
    ERROR_EMAIL_ALREADY_EXISTS("04", "El email proporcionado ya está siendo usado. Intente con otro"),
    ERROR_PASSWORD_NOT_MATCH("05", "La contraseña vieja ingresada no coincide con la actual");

    private final String code;
    private final String description;
}
