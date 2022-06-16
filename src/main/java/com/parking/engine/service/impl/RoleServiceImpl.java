package com.parking.engine.service.impl;

import com.parking.engine.entity.Role;
import com.parking.engine.mapper.RoleMapper;
import com.parking.engine.repository.RoleRepository;
import com.parking.engine.request.RoleDTO;
import com.parking.engine.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;

    @Autowired
    RoleMapper mapper;

    /**
     * Create role
     * @param roleDTO
     * @return
     */
    @Override
    public boolean create(RoleDTO roleDTO) {
        try {
            Role role = mapper.convertDTOToEntity(roleDTO);
            repository.save(role);
            return true;
        } catch (Exception ex) {
            log.error("Error when create role: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<RoleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Role> roles = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(roles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all role:", e);
        }
        return false;
    }

    /**
     * Update role
     * @param roleDTO
     * @return
     */
    @Override
    public boolean update(RoleDTO roleDTO) {
        try {
            Role role = mapper.convertDTOToEntity(roleDTO);
            repository.save(role);
            return true;
        } catch (Exception ex) {
            log.error("Error when update role: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<RoleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Role> roles = list.stream().map(obj -> mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(roles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all role:", e);
        }
        return false;
    }

    /**
     * Find by id
     * @param id
     * @return
     */
    @Override
    public RoleDTO findById(Long id) {
        Optional<Role> role = repository.findById(id);
        return role.isPresent() ? mapper.convertEntityToDTO(role.get()) : new RoleDTO();
    }

    /**
     * Find all
     * @return
     */
    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = repository.findAll();
        if (null != roles && 0 < roles.size()) {
            return roles.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoles(String userId) {
        return repository.getRole(userId);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<RoleDTO> search(RoleDTO filter) {
        return null;
    }

    //end
}
