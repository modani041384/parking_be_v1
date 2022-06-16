package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.ParkingLaneDTO;
import com.parking.engine.response.ParkingLaneResponseDTO;
import com.parking.engine.service.ParkingLaneService;
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
public class ParkingLaneController {
    @Autowired
    ParkingLaneService service;

    @PostMapping(value = ApiPath.PARKING_LANE_ADD_NEW)
    public ResponseEntity<ParkingLaneResponseDTO> addNew(@RequestBody ParkingLaneDTO request) {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String parkingLaneId = UUID.randomUUID().toString();
            request.setParkingLaneId(parkingLaneId);
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new parking-lane fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create parking-lane");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new parking-lane:", ex);
            response.setMessage("Error when add new parking-lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_LANE_UPDATE)
    public ResponseEntity<ParkingLaneResponseDTO> update(@RequestBody ParkingLaneDTO request) {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getParkingLaneId()) {
                response.setMessage("Input parking_lane_id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update record
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update parking-lane fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success update parking-lane");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update parking-lane:", ex);
            response.setMessage("Error when update parking-lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.PARKING_LANE_GET_ALL)
    public ResponseEntity<ParkingLaneResponseDTO> getAll() {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
        try {
            List<ParkingLaneDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when get all parking-lane");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all parking-lane:", ex);
            response.setMessage("Error when get all parking-lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get parking-lane by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_LANE_GET_BY_ID)
    public ResponseEntity<ParkingLaneResponseDTO> findById(@RequestBody ParkingLaneDTO request) {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ParkingLaneDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find parking-lane");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find parking-lane by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get parking-lane by id:", ex);
            response.setMessage("Error when get parking-lane by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete parking-lane
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.PARKING_LANE_DELETE)
    public ResponseEntity<ParkingLaneResponseDTO> delete(@RequestBody ParkingLaneDTO request) {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
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
                response.setMessage("Error when delete parking-lane");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete parking-lane");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete parking-lane:", ex);
            response.setMessage("Error when delete parking-lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.PARKING_LANE_SEARCH)
    public ResponseEntity<ParkingLaneResponseDTO> search(@RequestBody PagingDTO<ParkingLaneDTO> request) {
        ParkingLaneResponseDTO response = new ParkingLaneResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<ParkingLaneDTO> list = service.search(request.getFilter());
            Page<ParkingLaneDTO> pages = (Page<ParkingLaneDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search parking-lane!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search parking-lane:", ex);
            response.setMessage("Error when search parking-lane: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
