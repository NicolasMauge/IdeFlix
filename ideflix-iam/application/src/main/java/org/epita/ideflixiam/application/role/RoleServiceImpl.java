package org.epita.ideflixiam.application.role;

import org.epita.ideflixiam.application.exception.RoleInexistantException;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.infrastructure.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity recupererRoleParNomRole(String nomRole) {

        final RoleEntity role = roleRepository.findRoleByNomRole(nomRole);
        if (role == null) {
            throw new RoleInexistantException("Le rôle " + nomRole + " n'existe pas");
        }

        return role;
    }
}
