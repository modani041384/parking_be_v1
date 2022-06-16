package com.parking.engine.service.impl;

import com.parking.engine.entity.Card;
import com.parking.engine.mapper.CardMapper;
import com.parking.engine.repository.CardRepository;
import com.parking.engine.request.CardDTO;
import com.parking.engine.service.CardService;
import com.parking.engine.utils.SQLFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    private final static String TABLE = "Card";

    @Autowired
    CardRepository repository;

    @Autowired
    CardMapper mapper;

    @Autowired
    EntityManager em;

    @Override
    public boolean create(CardDTO cardDTO) {
        try {
            Card card = mapper.convertDTOToEntity(cardDTO);
            card.setActive("1");
            repository.save(card);
            return true;
        } catch (Exception ex) {
            log.error("Error when create card: ", ex);
        }
        return false;
    }

    @Override
    public boolean createList(List<CardDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Card> cards = list.stream().map(obj ->
                        mapper.convertDTOToEntity(obj)).collect(Collectors.toList());
                //save all data
                repository.saveAll(cards);
                return true;
            }
        } catch (Exception e){
            log.error("Error when create all cards:", e);
        }
        return false;
    }

    @Override
    public boolean update(CardDTO cardDTO) {
        try {
            Card getCard = repository.findByCardId(cardDTO.getCardId());
            if (null != getCard && null != getCard.getId()) {
                Card card = mapper.convertDTOToEntity(cardDTO);
                card.setId(getCard.getId());
                repository.save(card);
                return true;
            }
        } catch (Exception ex) {
            log.error("Error when update card: ", ex);
        }
        return false;
    }

    @Override
    public boolean updateList(List<CardDTO> list) {
        try {
            if (list != null && list.size() > 0) {
                List<Card> cards = new ArrayList<>();
                list.stream().forEach(obj -> {
                    Card getCard = repository.findByCardId(obj.getCardId());
                    Card card = mapper.convertDTOToEntity(obj);
                    card.setId(getCard.getId());
                    cards.add(card);
                });
                if (cards.size() > 0) {
                    //save all data
                    repository.saveAll(cards);
                    return true;
                }
            }
        } catch (Exception e){
            log.error("Error when update all cards:", e);
        }
        return false;
    }

    @Override
    public CardDTO findById(Long id) {
        Optional<Card> card = repository.findById(id);
        return card.isPresent() ? mapper.convertEntityToDTO(card.get()) : new CardDTO();
    }

    @Override
    public List<CardDTO> findAll() {
        List<Card> cards = repository.findAll();
        if (null != cards && 0 < cards.size()) {
            return cards.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
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
    public List<CardDTO> search(CardDTO filter) {
        if (null == filter) return new ArrayList<>();
        String sql = SQLFilterUtils.createSearchSqlEqual(TABLE, filter);
        Query query = em.createQuery(sql);
        //set value for query
        SQLFilterUtils.setValueForQuery(query, filter);
        List<Card> cards = query.getResultList();
        if (cards != null && cards.size() > 0) {
            return cards.stream().map(obj -> mapper.convertEntityToDTO(obj)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    //end
}
