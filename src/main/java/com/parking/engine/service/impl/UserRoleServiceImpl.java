package com.parking.engine.service.impl;

import com.parking.engine.entity.UserRole;
import com.parking.engine.mapper.UserRoleMapper;
import com.parking.engine.repository.UserRoleRepository;
import com.parking.engine.request.UserRoleDTO;
import com.parking.engine.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository repository;

    @Autowired
    UserRoleMapper mapper;

    /**
     * Create
     * @param userRoleDTO
     * @return
     */
    @Override
    public boolean create(UserRoleDTO userRoleDTO) {
        try {
            UserRole userRole = mapper.convertDTOToEntity(userRoleDTO);
            //store database
            repository.save(userRole);
            return true;
        } catch (Exception e){
            log.error("Error when create userRole:", e);
        }
        return false;
    }

    @Override
    public boolean createList(List<UserRoleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<UserRole> userRoles = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //save all data
                repository.saveAll(userRoles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all userRole:", e);
        }
        return false;
    }

    /**
     * Update
     * @param userRoleDTO
     * @return
     */
    @Override
    public boolean update(UserRoleDTO userRoleDTO) {
        try {
            UserRole userRole = mapper.convertDTOToEntity(userRoleDTO);
            //update database
            repository.save(userRole);
            return true;
        } catch (Exception e){
            log.error("Error when update userRole:", e);
        }
        return false;
    }

    @Override
    public boolean updateList(List<UserRoleDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<UserRole> userRoles = list.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                //update all data
                repository.saveAll(userRoles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all userRole:", e);
        }
        return false;
    }

    /**
     * Find object by id
     * @param id
     * @return
     */
    @Override
    public UserRoleDTO findById(Long id) {
        Optional<UserRole> userRole = repository.findById(id);
        return userRole.isPresent() ? mapper.convertEntityToDTO(userRole.get()) : new UserRoleDTO();
    }

    /**
     * Find all
     * @return
     */
    @Override
    public List<UserRoleDTO> findAll() {
        List<UserRole> userRoles = repository.findAll();
        if (null != userRoles && 0 < userRoles.size()) {
            return userRoles.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UserRoleDTO> search(UserRoleDTO filter) {
        return null;
    }

    //end
}
