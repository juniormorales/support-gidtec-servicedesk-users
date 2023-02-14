package com.pe.gidtec.servicedesk.users.user.mapper;

import com.pe.gidtec.servicedesk.users.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.users.common.util.MappingHelper;
import com.pe.gidtec.servicedesk.users.user.model.api.request.UserPostRequest;
import com.pe.gidtec.servicedesk.users.user.model.api.response.UserResponse;
import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {MappingHelper.class},
        builder = @Builder(disableBuilder = true)
)
public interface UsersMapper {

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromUser")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "userId", source = "entity.userId")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "names", source = "entity.names")
    @Mapping(target = "lastNames", source = "entity.lastNames")
    @Mapping(target = "phoneNumber", source = "entity.phoneNumber")
    @Mapping(target = "companyName", source = "entity.companyName")
    @Mapping(target = "webSite", source = "entity.webSite")
    @Mapping(target = "role", source = "entity.role")
    UserResponse entityToUserResponse(UserEntity entity);

    @Mapping(target = "audit.statusCode", constant = "AC")
    @Mapping(target = "audit.createdBy", source = "headers.userCode")
    @Mapping(target = "audit.createdDate", expression = "java(getLocalDateTime())")
    @Mapping(target = "audit.deleted", constant = "false")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "names", source = "request.names")
    @Mapping(target = "lastNames", source = "request.lastNames")
    @Mapping(target = "phoneNumber", source = "request.phoneNumber")
    @Mapping(target = "companyName", source = "request.companyName")
    @Mapping(target = "webSite", source = "request.webSite")
    @Mapping(target = "role", source = "request.role")
    @Mapping(target = "password", source = "request.password")
    UserEntity userPostRequestToEntity(UserPostRequest request, CommonHeaders headers);

    default LocalDateTime getLocalDateTime() { return LocalDateTime.now(); }
}
