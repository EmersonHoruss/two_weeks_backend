package com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.BaseCreateDTO;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.entities.user.User;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserCreateDTO extends BaseCreateDTO<User> {
    @NotBlank
    private String nickanme;

    private String photo;

    @NotBlank
    private String roles;

    @NotNull
    private Long tenantId;

    @Override
    public User asEntity() {
        User user = new User();
        user.setNickname(this.getNickanme());
        user.setNicknameInTenant(this.getNickanme() + this.tenantId);
        user.setPhoto(this.getPhoto());
        user.setRoles(this.getRoles());
        user.setTenant(this.getTenantEntity());
        return user;
    }

    private Tenant getTenantEntity() {
        if (this.getTenantId() != null) {
            Tenant tenant = new Tenant();
            tenant.setId(this.getTenantId());
            return tenant;
        }
        return null;
    }
}
