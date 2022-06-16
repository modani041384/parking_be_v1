package com.parking.engine.service.impl;

import com.parking.engine.entity.UserProfile;
import com.parking.engine.mapper.UserProfileMapper;
import com.parking.engine.repository.UserProfileRepository;
import com.parking.engine.request.UserProfileDTO;
import com.parking.engine.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    UserProfileRepository repository;

    @Autowired
    UserProfileMapper mapper;

    @Override
    public boolean create(UserProfileDTO userProfileDTO) {
        try {
            UserProfile userProfile = mapper.convertDTOToEntity(userProfileDTO);
            repository.save(userProfile);
            return true;
        } catch (Exception ex) {
            log.error("Error when create userProfile: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<UserProfileDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<UserProfile> userProfiles = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(userProfiles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all userProfiles:", e);
        }
        return false;
    }

    @Override
    public boolean update(UserProfileDTO userProfileDTO) {
        try {
            UserProfile userProfile = mapper.convertDTOToEntity(userProfileDTO);
            repository.save(userProfile);
            return true;
        } catch (Exception ex) {
            log.error("Error when update userProfile: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<UserProfileDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<UserProfile> userProfiles = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(userProfiles);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all userProfiles:", e);
        }
        return false;
    }

    @Override
    public UserProfileDTO findById(Long id) {
        Optional<UserProfile> userProfile = repository.findById(id);
        return userProfile.isPresent() ? mapper.convertEntityToDTO(userProfile.get()) : new UserProfileDTO();
    }

    @Override
    public List<UserProfileDTO> findAll() {
        List<UserProfile> userProfiles = repository.findAll();
        if (null != userProfiles && 0 < userProfiles.size()) {
            return userProfiles.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UserProfileDTO> search(UserProfileDTO filter) {
        return null;
    }

    //end
}
