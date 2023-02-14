package com.pe.gidtec.servicedesk.users.user.service;

import com.pe.gidtec.servicedesk.users.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.users.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.users.user.model.api.request.ChangePasswordRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPostRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPutRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.response.UserResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UsersService {

    Mono<ResultResponse<UserResponse>> createUser(UserPostRequest request, CommonHeaders headers);

    Mono<ResultResponse<UserResponse>> updateUser(UserPutRequest request, CommonHeaders headers);

    Mono<ResultResponse<UserResponse>> deleteUser(String userId, CommonHeaders headers);

    Mono<ResultResponse<UserResponse>> changePassword(ChangePasswordRequest request, CommonHeaders headers);

    Mono<ResultResponse<UserResponse>> getUserById(String userId);

    Mono<ResultResponse<List<UserResponse>>> getUsers();

    Mono<ResultResponse<List<UserResponse>>> getUsersByRole(String roleName);

}
