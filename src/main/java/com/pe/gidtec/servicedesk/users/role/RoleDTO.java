package com.pe.gidtec.servicedesk.users.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "RoleDTO")
public class RoleDTO {

    @Schema(name = "id",
            description = "Identificador del rol",
            example = "1ab24sof5e1vxc")
    private String id;

    @Schema(name = "name",
            description = "Nombre del rol",
            example = "ADMIN")
    private String name;

}
