package com.parking.engine.service.impl;

import com.parking.engine.entity.User;
import com.parking.engine.mapper.UserMapper;
import com.parking.engine.repository.UserRepository;
import com.parking.engine.request.UserDTO;
import com.parking.engine.service.UserService;
import com.parking.engine.utils.SQLFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final static String TABLE = "User";

    @Autowired
    UserRepository repository;

    @Autowired
    UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    /**
     * Create
     * @param userDTO
     * @return
     */
    @Override
    public boolean create(UserDTO userDTO) {
        try {
            User user = mapper.convertDTOToEntity(userDTO);
            String password = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(password);
            repository.save(user);
            return true;
        } catch (Exception ex) {
            log.error("Error when create user: ", ex);
        }
        return false;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public boolean createList(List<UserDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<User> users = list.stream().map(obj -> {
                    User user = mapper.convertDTOToEntity(obj);
                    String password = passwordEncoder.encode(obj.getPassword());
                    user.setPassword(password);
                    return user;
                }).collect(Collectors.toList());
                //save all data
                repository.saveAll(users);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all user:", e);
        }
        return false;
    }

    /**
     * Update
     * @param userDTO
     * @return
     */
    @Override
    public boolean update(UserDTO userDTO) {
        try {
            User user = mapper.convertDTOToEntity(userDTO);
            if (StringUtils.isNotBlank(userDTO.getPassword())) {
                String password = passwordEncoder.encode(userDTO.getPassword());
                user.setPassword(password);
            }
            repository.save(user);
            return true;
        } catch (Exception ex) {
            log.error("Error when update user: ", ex);
        }
        return false;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public boolean updateList(List<UserDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<User> users = list.stream().map(obj -> {
                    User user = mapper.convertDTOToEntity(obj);
                    if (StringUtils.isNotBlank(obj.getPassword())) {
                        String password = passwordEncoder.encode(obj.getPassword());
                        user.setPassword(password);
                    }
                    return user;
                }).collect(Collectors.toList());
                //save all data
                repository.saveAll(users);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all user:", e);
        }
        return false;
    }

    /**
     * Find by id
     * @param id
     * @return
     */
    @Override
    public UserDTO findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.isPresent() ? mapper.convertEntityToDTO(user.get()) : new UserDTO();
    }

    /**
     * Find all
     * @return
     */
    @Override
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();
        if (null != users && 0 < users.size()) {
            return users.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean createRawData(UserDTO userDTO) {
        try {
            User user = mapper.convertDTOToEntity(userDTO);
            repository.save(user);
            return true;
        } catch (Exception ex) {
            log.error("Error when create user: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateRawData(UserDTO userDTO) {
        try {
            User user = mapper.convertDTOToEntity(userDTO);
            repository.save(user);
            return true;
        } catch (Exception ex) {
            log.error("Error when update user: ", ex);
        }
        return false;
    }

    @Override
    public boolean createListRawData(List<UserDTO> users) {
        try {
            if (users != null && users.size() > 0) {
                List<User> list = users.stream().map(obj -> mapper.convertDTOToEntity(obj))
                        .collect(Collectors.toList());
                repository.saveAll(list);
                return true;
            }
        } catch (Exception ex) {
            log.error("Error when create user: ", ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.delete(id);
            log.info("Delete user success!!!");
            return true;
        } catch (Exception ex) {
            log.error("Error when delete user:", ex);
        }
        return false;
    }

    @Override
    public List<UserDTO> search(UserDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<User> users = query.getResultList();
        if (users != null && users.size() > 0) {
            return users.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
