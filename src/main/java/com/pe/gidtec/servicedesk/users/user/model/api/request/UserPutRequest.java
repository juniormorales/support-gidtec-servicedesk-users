package com.pe.gidtec.servicedesk.users.user.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import com.pe.gidtec.servicedesk.users.audit.request.AuditPut;
import com.pe.gidtec.servicedesk.users.role.RoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserPutRequest")
public class UserPutRequest {

    @NotBlank(message = "El valor de 'userId' no puede ser vacio.")
    @Schema(name = "userId",
            description = "Identificador del usuario a modificar",
            example = "1ab24sof5e1vxc")
    private String userId;

    @NotBlank(message = "El valor de 'phoneNumber' no puede ser vacio.")
    @Pattern(regexp = Patterns.NUMERIC)
    @Schema(name = "phoneNumber",
            description = "Número de teléfono del usuario a actualizar",
            example = "967123456", pattern = Patterns.NUMERIC)
    private String phoneNumber;

    @NotNull(message = "El valor de 'role' no puede ser nulo")
    @Schema(name = "role",
            description = "Rol del usuario a modificar")
    private RoleDTO role;

    @NotNull(message = "El valor de 'audit' no puede ser nulo")
    @Schema(name = "audit",
            description = "Datos de auditoria")
    private AuditPut audit;
}
