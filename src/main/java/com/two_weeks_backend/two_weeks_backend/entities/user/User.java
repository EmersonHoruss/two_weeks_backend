package com.two_weeks_backend.two_weeks_backend.entities.user;

import com.two_weeks_backend.two_weeks_backend.DTOs.entities.user.user.UserShowDTO;
import com.two_weeks_backend.two_weeks_backend.entities.BaseEntity;
import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "uq_nickanme_in_tenant", columnNames = "nicknameInTenant")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity<UserShowDTO> {
    @Column(name = "nickname", length = 255, nullable = false)
    private String nickname;

    @Column(name = "nicknameInTenant", length = 255, nullable = false, unique = true)
    private String nicknameInTenant;

    @Column(name = "photo", length = 255)
    private String photo;

    @Column(name = "roles", length = 255, nullable = false)
    private String roles;

    @Column(name = "activated", columnDefinition = "boolean default true", nullable = false)
    private Boolean activated;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_type_tenant"))
    private Tenant tenant;

    @PrePersist
    private void prePersist() {
        if (this.activated == null) {
            this.activated = true;
        }

        this.nicknameInTenant = this.nickname + this.tenant.getId();
    }

    @Override
    public UserShowDTO asShowDTO() {
        UserShowDTO userShowDTO = new UserShowDTO();
        userShowDTO.setId(this.getId());
        userShowDTO.setNickame(this.getNickname());
        userShowDTO.setPhoto(this.getPhoto());
        userShowDTO.setRoles(this.getRoles());
        userShowDTO.setActivated(this.getActivated());
        return userShowDTO;
    }
}
