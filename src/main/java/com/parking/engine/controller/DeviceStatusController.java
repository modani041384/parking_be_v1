package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.DeviceStatusDTO;
import com.parking.engine.response.DeviceStatusResponseDTO;
import com.parking.engine.service.DeviceStatusService;
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
public class DeviceStatusController  {
    @Autowired
    DeviceStatusService service;

    @PostMapping(value = ApiPath.DEVICE_STATUS_NEW)
    public ResponseEntity<DeviceStatusResponseDTO> addNew(@RequestBody DeviceStatusDTO request) {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String deviceStatusId = UUID.randomUUID().toString();
            request.setDeviceStatusId(deviceStatusId);
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new device-status fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create device-status");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new device-status:", ex);
            response.setMessage("Error when add new device-status: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.DEVICE_STATUS_UPDATE)
    public ResponseEntity<DeviceStatusResponseDTO> update(@RequestBody DeviceStatusDTO request) {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update record
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update device-status fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success update device-status");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update device-status:", ex);
            response.setMessage("Error when update device-status: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.DEVICE_STATUS_GET_ALL)
    public ResponseEntity<DeviceStatusResponseDTO> getAll() {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
        try {
            List<DeviceStatusDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success get all device-status");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all device-status:", ex);
            response.setMessage("Error when get all device-status: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get device-status by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.DEVICE_STATUS_GET_BY_ID)
    public ResponseEntity<DeviceStatusResponseDTO> findById(@RequestBody DeviceStatusDTO request) {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            DeviceStatusDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find device-status");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find device-status by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get device-status by id:", ex);
            response.setMessage("Error when get device-status by id: " + ex.getMessage());
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
    @PostMapping(value = ApiPath.DEVICE_STATUS_DELETE)
    public ResponseEntity<DeviceStatusResponseDTO> delete(@RequestBody DeviceStatusDTO request) {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
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
                response.setMessage("Error when delete device-status");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete device-status");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete device-status:", ex);
            response.setMessage("Error when delete device-status: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.DEVICE_STATUS_SEARCH)
    public ResponseEntity<DeviceStatusResponseDTO> search(@RequestBody PagingDTO<DeviceStatusDTO> request) {
        DeviceStatusResponseDTO response = new DeviceStatusResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<DeviceStatusDTO> list = service.search(request.getFilter());
            Page<DeviceStatusDTO> pages = (Page<DeviceStatusDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search device-status!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search device-status:", ex);
            response.setMessage("Error when search device-status: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
