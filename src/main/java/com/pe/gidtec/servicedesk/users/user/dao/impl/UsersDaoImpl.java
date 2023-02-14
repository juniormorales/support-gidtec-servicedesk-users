package com.pe.gidtec.servicedesk.users.user.dao.impl;

import com.pe.gidtec.servicedesk.users.user.dao.UsersDao;
import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import com.pe.gidtec.servicedesk.users.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersDaoImpl implements UsersDao {

    private final UsersRepository usersRepository;

    @Override
    public Mono<UserEntity> saveUser(UserEntity entity) {
        return usersRepository.save(entity)
                .doOnSuccess(message -> log.info("Usuario " + message.toString() + " guardado con éxito."))
                .onErrorResume(ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar el usuario")));
    }

    @Override
    public Mono<Boolean> verifyIfExistsUserById(String userId) {
        return usersRepository.existsUserEntityByUserIdAndAuditDeletedFalse(userId);
    }

    @Override
    public Mono<Boolean> verifyIfExistsUserByEmail(String email) {
        return usersRepository.existsUserEntityByEmailAndAuditDeletedFalse(email);
    }

    @Override
    public Mono<UserEntity> getUserById(String userId) {
        return usersRepository.findUserEntityByUserIdAndAuditDeletedFalse(userId);
    }

    @Override
    public Flux<UserEntity> findUsersActive() {
        return usersRepository.findAllByAuditDeletedFalse();
    }

    @Override
    public Flux<UserEntity> findUsersByRole(String roleName) {
        return usersRepository.findAllByRoleNameAndAuditDeletedFalse(roleName);
    }
}
