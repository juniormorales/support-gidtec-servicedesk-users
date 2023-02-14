package com.pe.gidtec.servicedesk.users.common.util;

import com.pe.gidtec.servicedesk.users.config.AuditProperties;
import com.pe.gidtec.servicedesk.users.user.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MappingHelper {

    private final AuditProperties properties;

    @Named("getAuditStatusDescriptionFromUser")
    public String getAuditStatusDescriptionFromUser(UserEntity entity) {
        return properties.getStatusCode().get(entity.getAudit().getStatusCode());
    }

}
