package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.ParkingDTO;
import com.parking.engine.response.ParkingResponseDTO;
import com.parking.engine.service.ParkingService;
import com.parking.engine.utils.PageableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ParkingController {
    @Autowired
    ParkingService service;

    @GetMapping(value = ApiPath.PING)
    @PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
    public String welcome() {
        return "Welcome to smart parking";
    }

    @PostMapping(value = ApiPath.PARKING_SEARCH)
    public ResponseEntity<ParkingResponseDTO> search(@RequestBody PagingDTO<ParkingDTO> request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<ParkingDTO> list = service.search(request.getFilter());
            Page<ParkingDTO> pages = (Page<ParkingDTO>) PageableUtils.toPage(list, request);
            response.setPages(pages);
            response.setMessage("Success when search parking!!!!");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search parking:", ex);
            response.setMessage("Error when search parking: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.PARKING_GET_ALL)
    public ResponseEntity<ParkingResponseDTO> getAll() {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            List<ParkingDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when get all parking");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all parking:", ex);
            response.setMessage("Error when get all parking: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_ADD_NEW)
    public ResponseEntity<ParkingResponseDTO> addNewParking(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getAddress()) {
                response.setMessage("Input address");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getArea()) {
                response.setMessage("Input area");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new parking fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create parking");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when create parking:", ex);
            response.setMessage("Error when create parking: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_UPDATE_GENERAL_INFORMATION)
    public ResponseEntity<ParkingResponseDTO> updateGeneralInformationParking(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingId()) {
                response.setMessage("Input parkingId");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update parking");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update general information parking:", ex);
            response.setMessage("Error when update general information parking: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_UPDATE_PARKING_LANE)
    public ResponseEntity<ParkingResponseDTO> updateParkingLane(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingId()) {
                response.setMessage("Input parkingId");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingLanes()) {
                response.setMessage("Input parking lanes");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking lanes fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update parking lanes");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update parking lane:", ex);
            response.setMessage("Error when update parking lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_UPDATE_SLOT_TYPE_CONFIG)
    public ResponseEntity<ParkingResponseDTO> updateSlotTypeConfigure(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingId()) {
                response.setMessage("Input parkingId");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getSlotTypeConfigs()) {
                response.setMessage("Input parking slot type config");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getQuantity()) {
                response.setMessage("Input quantity");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking slot type config fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update parking slot type config");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update slot type configure:", ex);
            response.setMessage("Error when update slot type configure: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_UPDATE_CARD_ASSIGNMENT)
    public ResponseEntity<ParkingResponseDTO> updateCardAssignment(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingId()) {
                response.setMessage("Input parkingId");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getCardAssignments()) {
                response.setMessage("Input price card assignment");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking card assignment fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update  update card assignment config");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update card assignment:", ex);
            response.setMessage("Error when update card assignment: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_UPDATE_PRICE_BOOKING_ASSIGNMENT)
    public ResponseEntity<ParkingResponseDTO> updatePriceBookingAssignment(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingId()) {
                response.setMessage("Input parkingId");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPriceBooks()) {
                response.setMessage("Input price booking assignment");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking price booking assignment fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update card assignment config");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update price booking assignment:", ex);
            response.setMessage("Error when update price booking assignment: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get parking by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_GET_BY_ID)
    public ResponseEntity<ParkingResponseDTO> findById(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ParkingDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find parking");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find parking by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get parking by id:", ex);
            response.setMessage("Error when get parking by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete parking
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_DELETE)
    public ResponseEntity<ParkingResponseDTO> delete(@RequestBody ParkingDTO request) {
        ParkingResponseDTO response = new ParkingResponseDTO();
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
                response.setMessage("Error when delete parking");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete parking!!!");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete parking:", ex);
            response.setMessage("Error when delete parking: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}