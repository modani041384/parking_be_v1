package com.parking.engine.service.impl;

import com.parking.engine.entity.Subscription;
import com.parking.engine.mapper.SubscriptionMapper;
import com.parking.engine.repository.SubscriptionRepository;
import com.parking.engine.request.SubscriptionDTO;
import com.parking.engine.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository repository;

    @Autowired
    SubscriptionMapper mapper;

    @Override
    public boolean create(SubscriptionDTO subscriptionDTO) {
        try {
            Subscription subscription = mapper.convertDTOToEntity(subscriptionDTO);
            repository.save(subscription);
            return true;
        } catch (Exception ex) {
            log.error("Error when create subscription: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<SubscriptionDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Subscription> subscriptions = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(subscriptions);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all subscriptions:", e);
        }
        return false;
    }

    @Override
    public boolean update(SubscriptionDTO subscriptionDTO) {
        try {
            Subscription subscription = mapper.convertDTOToEntity(subscriptionDTO);
            repository.save(subscription);
            return true;
        } catch (Exception ex) {
            log.error("Error when update subscription: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<SubscriptionDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Subscription> subscriptions = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(subscriptions);
                return true;
            }
        } catch (Exception e){
            log.error("Error when update all subscriptions:", e);
        }
        return false;
    }

    @Override
    public SubscriptionDTO findById(Long id) {
        Optional<Subscription> subscription = repository.findById(id);
        return subscription.isPresent() ? mapper.convertEntityToDTO(subscription.get()) : new SubscriptionDTO();
    }

    @Override
    public List<SubscriptionDTO> findAll() {
        List<Subscription> subscriptions = repository.findAll();
        if (null != subscriptions && 0 < subscriptions.size()) {
            return subscriptions.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<SubscriptionDTO> search(SubscriptionDTO filter) {
        return null;
    }

    //end
}
