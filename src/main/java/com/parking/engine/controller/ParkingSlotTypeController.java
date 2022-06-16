package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.ParkingSlotTypeDTO;
import com.parking.engine.response.ParkingSlotTypeResponseDTO;
import com.parking.engine.service.ParkingSlotTypeService;
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
public class ParkingSlotTypeController {
    @Autowired
    private ParkingSlotTypeService service;

    @PostMapping(value = ApiPath.PARKING_SLOT_ADD_NEW)
    public ResponseEntity<ParkingSlotTypeResponseDTO> addNew(@RequestBody ParkingSlotTypeDTO request) {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPstCode()) {
                response.setMessage("Input Parking Slot Code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getDescription()) {
                response.setMessage("Input description");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIsDefault()) {
                response.setMessage("Input default");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String pstId = UUID.randomUUID().toString();
            request.setPstId(pstId);
            boolean isCreate =  service.create(request);
            if (!isCreate) {
                response.setMessage("Create new parking-slot fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create parking-slot");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new parking-slot:", ex);
            response.setMessage("Error when add new parking-slot: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_SLOT_UPDATE)
    public ResponseEntity<ParkingSlotTypeResponseDTO> update(@RequestBody ParkingSlotTypeDTO request) {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPstId()) {
                response.setMessage("Input parking-slot id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPstCode()) {
                response.setMessage("Input Parking Slot Code");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIsDefault()) {
                response.setMessage("Input default");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            boolean isUpdate =  service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking-slot fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create parking-slot");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update parking-slot:", ex);
            response.setMessage("Error when update parking-slot: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.PARKING_SLOT_GET_ALL)
    public ResponseEntity<ParkingSlotTypeResponseDTO> getAll() {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
        try {
            List<ParkingSlotTypeDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when get all parking-slot");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all parking-slot:", ex);
            response.setMessage("Error when get all parking-slot: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get parking-slot by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_SLOT_GET_BY_ID)
    public ResponseEntity<ParkingSlotTypeResponseDTO> findById(@RequestBody ParkingSlotTypeDTO request) {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ParkingSlotTypeDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find parking-slot");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find parking-slot by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get parking-slot by id:", ex);
            response.setMessage("Error when get parking-slot by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete parking-slot
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_SLOT_DELETE)
    public ResponseEntity<ParkingSlotTypeResponseDTO> delete(@RequestBody ParkingSlotTypeDTO request) {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
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
                response.setMessage("Error when delete parking-slot");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete parking-slot!!!");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete parking-slot:", ex);
            response.setMessage("Error when delete parking-slot: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_SLOT_SEARCH)
    public ResponseEntity<ParkingSlotTypeResponseDTO> search(@RequestBody PagingDTO<ParkingSlotTypeDTO> request) {
        ParkingSlotTypeResponseDTO response = new ParkingSlotTypeResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<ParkingSlotTypeDTO> list = service.search(request.getFilter());
            Page<ParkingSlotTypeDTO> pages = (Page<ParkingSlotTypeDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search parking-slot!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search parking-slot:", ex);
            response.setMessage("Error when search parking-slot: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
