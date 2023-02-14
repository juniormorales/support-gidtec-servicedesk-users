package com.pe.gidtec.servicedesk.users.user.repository;

import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsersRepository extends ReactiveMongoRepository<UserEntity,String> {

    Mono<Boolean> existsUserEntityByUserIdAndAuditDeletedFalse(String id);

    Mono<Boolean> existsUserEntityByEmailAndAuditDeletedFalse(String email);

    Mono<UserEntity> findUserEntityByUserIdAndAuditDeletedFalse(String id);

    Flux<UserEntity> findAllByRoleNameAndAuditDeletedFalse(String roleName);

    Flux<UserEntity> findAllByAuditDeletedFalse();
}
