package com.pe.gidtec.servicedesk.users.user.dao;

import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersDao {

    Mono<UserEntity> saveUser(UserEntity entity);

    Mono<Boolean> verifyIfExistsUserById(String userId);

    Mono<Boolean> verifyIfExistsUserByEmail(String email);

    Mono<UserEntity> getUserById(String userId);

    Flux<UserEntity> findUsersActive();

    Flux<UserEntity> findUsersByRole(String roleName);
}
