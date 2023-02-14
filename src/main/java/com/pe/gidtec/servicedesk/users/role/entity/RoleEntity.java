package com.pe.gidtec.servicedesk.users.role.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleEntity {

    @Field(name = "id")
    private String id;

    @Field(name = "name")
    private String name;
}
