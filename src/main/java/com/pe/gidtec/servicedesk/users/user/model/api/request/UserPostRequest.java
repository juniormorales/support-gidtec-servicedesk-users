package com.pe.gidtec.servicedesk.users.user.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import com.pe.gidtec.servicedesk.users.audit.request.AuditPut;
import com.pe.gidtec.servicedesk.users.role.RoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserPostRequest")
public class UserPostRequest {

    @NotBlank(message = "El valor de 'email' no puede ser vacio.")
    @Email(message = "El valor de 'email' debe tener el formato de correo electronico.")
    @Schema(name = "email",
            description = "Correo electrónico del usuario a registrar",
            example = "user1@gedtec.com.pe")
    private String email;

    @NotBlank(message = "El valor de 'names' no puede ser vacio.")
    @Schema(name = "names",
            description = "Nombres del usuario a registrar",
            example = "Kimberly Jazmin")
    private String names;

    @NotBlank(message = "El valor de 'lastNames' no puede ser vacio.")
    @Schema(name = "lastNames",
            description = "Apellidos del usuario a registrar",
            example = "Japay Rojas")
    private String lastNames;

    @NotBlank(message = "El valor de 'companyName' no puede ser vacio.")
    @Schema(name = "companyName",
            description = "Nombre de la empresa del usuario a registrar",
            example = "Latina PE")
    private String companyName;

    @NotBlank(message = "El valor de 'phoneNumber' no puede ser vacio.")
    @Pattern(regexp = Patterns.NUMERIC, message = "El valor de 'phoneNumber' debe contener solo numeros.")
    @Schema(name = "phoneNumber",
            description = "Número de teléfono del usuario a registrar",
            example = "967123456")
    private String phoneNumber;

    @NotBlank(message = "El valor de 'webSite' no puede ser vacio.")
    @Schema(name = "webSite",
            description = "URL de la pagina web de la empresa del usuario a registrar.",
            example = "www.company.com.pe")
    private String webSite;

    @NotBlank(message = "El valor de 'password' no puede ser vacio.")
    @Size(min = 8, max = 16, message = "El valor de 'newPassword' debe contener entre 8 a 16 caracteres.")
    @Pattern(regexp = Patterns.PASSWORD, message = "El valor de 'newPassword' debe contener almenos una minuscula, una mayuscula, un numero y un simbolo")
    @Schema(name = "password",
            description = "Contraseña del usuario a registrar",
            example = "s1dt3Sd208ho&0", pattern = Patterns.PASSWORD)
    private String password;

    @NotNull(message = "El valor de 'role' no puede ser nulo")
    @Schema(name = "role",
            description = "Rol del usuario a registrar")
    private RoleDTO role;
}
