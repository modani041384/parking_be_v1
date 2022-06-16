package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.CardDTO;
import com.parking.engine.response.CardResponseDTO;
import com.parking.engine.service.CardService;
import com.parking.engine.utils.PageableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class CardController {
    @Autowired
    CardService service;

    @PostMapping(value = ApiPath.CARD_ADD_NEW)
    public ResponseEntity<CardResponseDTO> addNew(@RequestBody CardDTO request) {
        CardResponseDTO response = new CardResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getCardCode()) {
                response.setMessage("Input code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIdentityCode()) {
                response.setMessage("Input identity_code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String cardId = UUID.randomUUID().toString();
            request.setCardId(cardId);
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new card fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create card");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new card:", ex);
            response.setMessage("Error when add new card: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.CARD_UPDATE)
    public ResponseEntity<CardResponseDTO> update(@RequestBody CardDTO request) {
        CardResponseDTO response = new CardResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getCardId()) {
                response.setMessage("Input card_id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getCardCode()) {
                response.setMessage("Input code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIdentityCode()) {
                response.setMessage("Input identity_code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update record
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update card fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success update card");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update card:", ex);
            response.setMessage("Error when update card: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.CARD_GET_ALL)
    public ResponseEntity<CardResponseDTO> getAll() {
        CardResponseDTO response = new CardResponseDTO();
        try {
            List<CardDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success get all card");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all card:", ex);
            response.setMessage("Error when get all card: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get card by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.CARD_GET_BY_ID)
    public ResponseEntity<CardResponseDTO> findById(@RequestBody CardDTO request) {
        CardResponseDTO response = new CardResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CardDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find card");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find card by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get card by id:", ex);
            response.setMessage("Error when get card by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete card
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.CARD_DELETE)
    public ResponseEntity<CardResponseDTO> delete(@RequestBody CardDTO request) {
        CardResponseDTO response = new CardResponseDTO();
        try {
            //validate
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //delete
            boolean isDelete = service.delete(request.getId());
            if (!isDelete) {
                response.setMessage("Error when delete card");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete card");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete card:", ex);
            response.setMessage("Error when delete card: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.CARD_SEARCH)
    public ResponseEntity<CardResponseDTO> search(@RequestBody PagingDTO<CardDTO> request) {
        CardResponseDTO response = new CardResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<CardDTO> list = service.search(request.getFilter());
            Page<CardDTO> pages = (Page<CardDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search card!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search card:", ex);
            response.setMessage("Error when search card: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
