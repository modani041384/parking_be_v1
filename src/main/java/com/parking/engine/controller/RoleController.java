package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.request.RoleDTO;
import com.parking.engine.response.RoleResponseDTO;
import com.parking.engine.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RoleController {
    @Autowired
    RoleService service;

    /**
     * Create role
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.ROLE_CREATE)
    public ResponseEntity<RoleResponseDTO> create(@RequestBody RoleDTO request) {
        RoleResponseDTO response = new RoleResponseDTO();
        try {
            //validate
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Error when create role");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create role");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when create role:", ex);
            response.setMessage("Error when create role: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Update role
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.ROLE_UPDATE)
    public ResponseEntity<RoleResponseDTO> update(@RequestBody RoleDTO request) {
        RoleResponseDTO response = new RoleResponseDTO();
        try {
            //validate
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Error when update role");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update role");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update user:", ex);
            response.setMessage("Error when update user: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get user by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.ROLE_GET_BY_ID)
    public ResponseEntity<RoleResponseDTO> findById(@RequestBody RoleDTO request) {
        RoleResponseDTO response = new RoleResponseDTO();
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
            RoleDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find role");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find role by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when find role by id:", ex);
            response.setMessage("Error when find role by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Find all user
     * @return
     */
    @GetMapping(value = ApiPath.ROLE_FIND_ALL)
    public ResponseEntity<RoleResponseDTO> findAll() {
        RoleResponseDTO response = new RoleResponseDTO();
        try {
            List<RoleDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when find all role");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when find all role:", ex);
            response.setMessage("Error when find all role: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
