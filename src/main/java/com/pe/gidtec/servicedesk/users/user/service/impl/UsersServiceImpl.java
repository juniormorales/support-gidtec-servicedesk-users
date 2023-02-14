package com.pe.gidtec.servicedesk.users.user.service.impl;

import com.pe.gidtec.servicedesk.users.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.users.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.users.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.users.role.entity.RoleEntity;
import com.pe.gidtec.servicedesk.users.user.dao.UsersDao;
import com.pe.gidtec.servicedesk.users.user.mapper.UsersMapper;
import com.pe.gidtec.servicedesk.users.user.model.api.request.ChangePasswordRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPostRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPutRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.response.UserResponse;
import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import com.pe.gidtec.servicedesk.users.user.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersDao usersDao;

    private final UsersMapper usersMapper;


    @Override
    public Mono<ResultResponse<UserResponse>> createUser(UserPostRequest request, CommonHeaders headers) {
        return usersDao.verifyIfExistsUserByEmail(request.getEmail())
                .flatMap( bool -> {
                    if(Boolean.FALSE.equals(bool)) {
                        return usersDao.saveUser(usersMapper.userPostRequestToEntity(request,headers))
                                .map(response -> ResultResponse.ok(usersMapper.entityToUserResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_USER));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.ERROR_EMAIL_ALREADY_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<UserResponse>> updateUser(UserPutRequest request, CommonHeaders headers) {
        return usersDao.verifyIfExistsUserById(request.getUserId())
                .flatMap( bool -> {
                    if(Boolean.TRUE.equals(bool)) {
                        return usersDao.getUserById(request.getUserId())
                                .map(entity -> buildRequestForUpdate(entity,request,headers))
                                .flatMap(usersDao::saveUser)
                                .map(response -> ResultResponse.ok(usersMapper.entityToUserResponse(response)))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_USER));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_USER_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<UserResponse>> deleteUser(String userId, CommonHeaders headers) {
        return usersDao.verifyIfExistsUserById(userId)
                .flatMap( bool -> {
                    if(Boolean.TRUE.equals(bool)) {
                        return usersDao.getUserById(userId)
                                .map(entity -> buildRequestForDelete(entity,headers))
                                .flatMap(usersDao::saveUser)
                                .map(response -> ResultResponse.ok(UserResponse.builder().build()))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_USER));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_USER_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<UserResponse>> changePassword(ChangePasswordRequest request, CommonHeaders headers) {
        return usersDao.verifyIfExistsUserById(request.getUserId())
                .flatMap( bool -> {
                    if(Boolean.TRUE.equals(bool)) {
                        return usersDao.getUserById(request.getUserId())
                                .flatMap(entity -> {
                                    if(request.getOldPassword().equals(entity.getPassword())) {
                                        entity.setPassword(request.getNewPassword());
                                        return usersDao.saveUser(entity)
                                                .map(response -> ResultResponse.ok(usersMapper.entityToUserResponse(response)))
                                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_USER));
                                    } else {
                                        return Mono.just(ResultResponse.error(ResponseStatus.ERROR_PASSWORD_NOT_MATCH));
                                    }
                                });
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.BAD_ID_USER_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<UserResponse>> getUserById(String userId) {
        return usersDao.getUserById(userId)
                .map(usersMapper::entityToUserResponse)
                .map(ResultResponse::ok);
    }

    @Override
    public Mono<ResultResponse<List<UserResponse>>> getUsers() {
        return usersDao.findUsersActive()
                .map(usersMapper::entityToUserResponse)
                .collectList()
                .map(ResultResponse::ok);
    }

    @Override
    public Mono<ResultResponse<List<UserResponse>>> getUsersByRole(String roleName) {
        return usersDao.findUsersByRole(roleName)
                .map(usersMapper::entityToUserResponse)
                .collectList()
                .map(ResultResponse::ok);
    }

    private UserEntity buildRequestForUpdate(UserEntity entity, UserPutRequest request, CommonHeaders headers) {
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setRole(RoleEntity.builder()
                .id(request.getRole().getId())
                .name(request.getRole().getName())
                .build());
        entity.getAudit().setStatusCode(request.getAudit().getStatusCode());
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        return entity;
    }

    private UserEntity buildRequestForDelete(UserEntity entity, CommonHeaders headers) {
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        entity.getAudit().setDeleted(Boolean.TRUE);
        return entity;
    }
}
