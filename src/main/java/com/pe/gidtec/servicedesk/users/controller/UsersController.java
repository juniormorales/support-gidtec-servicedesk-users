package com.pe.gidtec.servicedesk.users.controller;

import com.pe.gidtec.servicedesk.lib.annotation.HttpHeadersMapping;
import com.pe.gidtec.servicedesk.users.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.users.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.users.user.model.api.request.ChangePasswordRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPostRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPutRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.response.UserResponse;
import com.pe.gidtec.servicedesk.users.user.service.UsersService;
import com.pe.gidtec.servicedesk.users.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/users")
@Tag(name = "Users", description = "Gestiona la información de los usuarios")
@Validated
public class UsersController {

    private final UsersService usersService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class)))
    },
            summary = "Permite obtener una lista de usuarios",
            method = "GET")
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<UserResponse>>>> getUsers() {
        return usersService.getUsers()
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class)))
    },
            summary = "Permite obtener una lista de usuarios por tipo de usuario",
            method = "GET")
    @GetMapping(value = "/search-role/{roleName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<List<UserResponse>>>> getUsersByRole(
            @Parameter(name = "roleName", example = "ADMIN", description = "Nombre del rol a filtrar")
            @PathVariable("roleName")
            @NotBlank
                    String roleName
    ) {
        return usersService.getUsersByRole(roleName)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = UserResponse.class)))
    },
            summary = "Permite obtener un usuario mediante su identificador 'userId'",
            method = "GET")
    @GetMapping(value = "/search-id/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<UserResponse>>> getUser(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Parameter(name = "userId", example = "s58xe8d0x6", description = "Identificador del usuario")
            @PathVariable("userId")
            @NotBlank
                    String userId
    ) {
        return usersService.getUserById(userId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar la información de un usuario",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<UserResponse>>> create(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Validated
            @RequestBody
                    UserPostRequest request
    ) {
        return usersService.createUser(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar la información de un usuario (rol)",
            method = "PUT")
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<UserResponse>>> update(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Validated
            @RequestBody
                    UserPutRequest request
    ) {
        return usersService.updateUser(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite eliminar un usuario del registro",
            method = "DELETE")
    @DeleteMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<UserResponse>>> delete(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Parameter(name = "userId", example = "s58xe8d0x6", description = "Identificador del usuario")
            @PathVariable("userId")
            @NotBlank
                    String userId
    ) {
        return usersService.deleteUser(userId, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar la contraseña del usuario",
            method = "PUT")
    @PutMapping(value = "/change-password", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<UserResponse>>> changePassword(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Validated
            @RequestBody
                    ChangePasswordRequest request
    ) {
        return usersService.changePassword(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }
}
