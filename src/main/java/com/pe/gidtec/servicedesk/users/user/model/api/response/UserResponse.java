package com.pe.gidtec.servicedesk.users.user.model.api.response;

import com.pe.gidtec.servicedesk.users.audit.response.Audit;
import com.pe.gidtec.servicedesk.users.role.RoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "UserResponse")
public class UserResponse {

    @Schema(name = "userId",
            description = "Identificador del usuario a modificar",
            example = "1ab24sof5e1vxc")
    private String userId;

    @Schema(name = "email",
            description = "Correo electrónico del usuario",
            example = "user1@gedtec.com.pe")
    private String email;

    @Schema(name = "names",
            description = "Nombres del usuario",
            example = "Kimberly Jazmin")
    private String names;

    @Schema(name = "lastNames",
            description = "Apellidos del usuario",
            example = "Japay Rojas")
    private String lastNames;

    @Schema(name = "companyName",
            description = "Nombre de la empresa del usuario",
            example = "Latina PE")
    private String companyName;

    @Schema(name = "phoneNumber",
            description = "Número de teléfono del usuario",
            example = "967123456")
    private String phoneNumber;

    @Schema(name = "webSite",
            description = "URL de la pagina web de la empresa a la que pertence el usuario.",
            example = "www.company.com.pe")
    private String webSite;

    @Schema(name = "role",
            description = "Rol del usuario")
    private RoleDTO role;

    @NotNull
    private Audit audit;
}
