package com.pe.gidtec.servicedesk.users.user.model.api.request;

import com.pe.gidtec.servicedesk.lib.util.Patterns;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ChangePasswordRequest")
public class ChangePasswordRequest {

    @NotBlank(message = "El valor de 'userId' no puede ser vacio.")
    @Schema(name = "userId",
            description = "Identificador del usuario a modificar",
            example = "1ab24sof5e1vxc")
    private String userId;

    @NotBlank(message = "El valor de 'oldPassword' no puede ser vacio.")
    @Schema(name = "oldPassword",
            description = "Contraseña antigua",
            example = "paSS1688$%")
    private String oldPassword;

    @NotBlank(message = "El valor de 'newPassword' no puede ser vacio.")
    @Size(min = 8, max = 16, message = "El valor de 'newPassword' debe contener entre 8 a 16 caracteres.")
    @Pattern(regexp = Patterns.PASSWORD, message = "El valor de 'newPassword' debe contener almenos una minuscula, una mayuscula, un numero y un simbolo")
    @Schema(name = "newPassword",
            description = "Contraseña nueva a cambiar",
            example = "paSS1610$%", pattern = Patterns.PASSWORD)
    private String newPassword;
}
