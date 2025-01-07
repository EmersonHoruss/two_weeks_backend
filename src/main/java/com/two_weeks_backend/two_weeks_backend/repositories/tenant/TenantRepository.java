package com.two_weeks_backend.two_weeks_backend.repositories.tenant;

import org.springframework.stereotype.Repository;

import com.two_weeks_backend.two_weeks_backend.entities.tenant.Tenant;
import com.two_weeks_backend.two_weeks_backend.repositories.BaseRepository;

@Repository
public interface TenantRepository extends BaseRepository<Tenant> {

}
