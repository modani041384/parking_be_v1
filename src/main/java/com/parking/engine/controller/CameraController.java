package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.CameraDTO;
import com.parking.engine.response.CameraResponseDTO;
import com.parking.engine.response.ParkingSlotTypeResponseDTO;
import com.parking.engine.service.CameraService;
import com.parking.engine.utils.PageableUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class CameraController {
    @Autowired
    CameraService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = ApiPath.CAMERA_ADD_NEW)
    public ResponseEntity<CameraResponseDTO> addNew(@RequestBody CameraDTO request) {
        CameraResponseDTO response = new CameraResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getServerName()) {
                response.setMessage("Input server name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getUserName()) {
                response.setMessage("Input user name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPassword()) {
                response.setMessage("Input password");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getUrlTemplate()) {
                response.setMessage("Input url-template");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getProtocol()) {
                response.setMessage("Input protocol");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIdOfCamera()) {
                response.setMessage("Input camera-id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getStreamId()) {
                response.setMessage("Input stream-id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String cameraId = UUID.randomUUID().toString();
            request.setCameraId(cameraId);
            String password = passwordEncoder.encode(request.getPassword());
            request.setPassword(password);
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new camera fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create camera");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new camera:", ex);
            response.setMessage("Error when add new camera: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.CAMERA_UPDATE)
    public ResponseEntity<CameraResponseDTO> update(@RequestBody CameraDTO request) {
        CameraResponseDTO response = new CameraResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getCameraId()) {
                response.setMessage("Input Id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getServerName()) {
                response.setMessage("Input server name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getUserName()) {
                response.setMessage("Input user name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getPassword()) {
                response.setMessage("Input password");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getUrlTemplate()) {
                response.setMessage("Input url-template");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getProtocol()) {
                response.setMessage("Input protocol");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getIdOfCamera()) {
                response.setMessage("Input camera-id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getStreamId()) {
                response.setMessage("Input stream-id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //update record
            String password = passwordEncoder.encode(request.getPassword());
            request.setPassword(password);
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update camera fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update camera");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update camera:", ex);
            response.setMessage("Error when update camera: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.CAMERA_GET_ALL)
    public ResponseEntity<CameraResponseDTO> getAll() {
        CameraResponseDTO response = new CameraResponseDTO();
        try {
            PagingDTO<CameraDTO> request = new PagingDTO<>();
            List<CameraDTO> list = service.findAll();
            Page<CameraDTO> pages = (Page<CameraDTO>) PageableUtils.toPage(list, request);
            response.setPages(pages);
            response.setMessage("Success when get all camera");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all camera:", ex);
            response.setMessage("Error when get all camera: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get camera by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.CAMERA_GET_BY_ID)
    public ResponseEntity<CameraResponseDTO> findById(@RequestBody CameraDTO request) {
        CameraResponseDTO response = new CameraResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            CameraDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find camera");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find camera by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get camera by id:", ex);
            response.setMessage("Error when get camera by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete camera
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.CAMERA_DELETE)
    public ResponseEntity<ParkingSlotTypeResponseDTO> delete(@RequestBody CameraDTO request) {
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
                response.setMessage("Error when delete camera");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete camera!!!");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete camera:", ex);
            response.setMessage("Error when delete camera: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.CAMERA_SEARCH)
    public ResponseEntity<CameraResponseDTO> search(@RequestBody PagingDTO<CameraDTO> request) {
        CameraResponseDTO response = new CameraResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<CameraDTO> list = service.search(request.getFilter());
            Page<CameraDTO> pages = (Page<CameraDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search camera!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search camera:", ex);
            response.setMessage("Error when search camera: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
