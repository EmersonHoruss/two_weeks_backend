package com.two_weeks_backend.two_weeks_backend.services.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.two_weeks_backend.two_weeks_backend.entities.user.User;
import com.two_weeks_backend.two_weeks_backend.services.BaseServiceImplementation;
import com.two_weeks_backend.two_weeks_backend.utils.specification.Specification;

@Service
public class UserService extends BaseServiceImplementation<User> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User create(User user) {
        String nicknameInTenant = user.getNicknameInTenant();

        boolean existsUserInTenant = this.existsUserInTenant(nicknameInTenant);
        if (existsUserInTenant)
            throw new RuntimeException("Ya existe el usuario " + user.getNickname());

        return this.baseRepository.save(user);
    }

    private boolean existsUserInTenant(String nicknameInTenant) {
        String attribute = "nicknameInTenant";
        String operator = "<eq>";
        String query = attribute + operator + nicknameInTenant;
        Specification<User> specification = new Specification<>(query);

        Pageable pageable = PageRequest.of(0, 1);

        Page<User> page = super.get(specification, pageable);

        return !page.isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        User retrievedUser = this.baseRepository.getReferenceById(user.getId());

        if (retrievedUser == null)
            throw new RuntimeException("No se ha encontrado el usuario");

        String nicknameInTenant = user.getNickname() + retrievedUser.getTenant().getId();
        boolean existsUserInTenant = this.existsUserInTenant(nicknameInTenant);
        if (existsUserInTenant && !user.getNickname().equals(retrievedUser.getNickname()))
            throw new RuntimeException("Ya existe el usuario " + user.getNickname());

        if (!retrievedUser.getActivated())
            throw new RuntimeException("El usuario " + retrievedUser.getNickname() + " está eliminado");

        if (user.getNickname().equals(retrievedUser.getNickname()) && user.getPhoto().equals(retrievedUser.getPhoto())
                && user.getRoles().equals(retrievedUser.getRoles()))
            return retrievedUser;

        retrievedUser.setNickname(user.getNickname());
        retrievedUser.setPhoto(user.getPhoto());
        retrievedUser.setRoles(user.getRoles());
        return this.baseRepository.save(retrievedUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public User setActivation(User user) {
        User retrievedUser = this.baseRepository.getReferenceById(user.getId());

        if (retrievedUser == null)
            throw new RuntimeException("No se ha encontrado el usuario");

        if (user.getActivated() == retrievedUser.getActivated())
            return retrievedUser;

        retrievedUser.setActivated(user.getActivated());
        return this.baseRepository.save(retrievedUser);
    }
}
