package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.UserDTO;
import com.parking.engine.response.UserResponseDTO;
import com.parking.engine.service.UserService;
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

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService service;

    /**
     * Create user
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.USER_CREATE)
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserDTO request) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            //validate
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Error when create user");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create user");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when create user:", ex);
            response.setMessage("Error when create user: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Update user
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.USER_UPDATE)
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserDTO request) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            //validate
            boolean isCreate = service.update(request);
            if (!isCreate) {
                response.setMessage("Error when update user");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update user");
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
    @PostMapping(value = ApiPath.USER_GET_BY_ID)
    public ResponseEntity<UserResponseDTO> findById(@RequestBody UserDTO request) {
        UserResponseDTO response = new UserResponseDTO();
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
            UserDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find user");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find user by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when find user by id:", ex);
            response.setMessage("Error when find user by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Find all user
     * @return
     */
    @GetMapping(value = ApiPath.USER_FIND_ALL)
    public ResponseEntity<UserResponseDTO> findAll() {
        UserResponseDTO response = new UserResponseDTO();
        try {
            List<UserDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when find all user");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when find all user:", ex);
            response.setMessage("Error when find all user: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete user
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.USER_DELETE)
    public ResponseEntity<UserResponseDTO> delete(@RequestBody UserDTO request) {
        UserResponseDTO response = new UserResponseDTO();
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
                response.setMessage("Error when delete user");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete user");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete user:", ex);
            response.setMessage("Error when delete user: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.USER_SEARCH)
    public ResponseEntity<UserResponseDTO> search(@RequestBody PagingDTO<UserDTO> request) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<UserDTO> list = service.search(request.getFilter());
            Page<UserDTO> pages = (Page<UserDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search user!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search user:", ex);
            response.setMessage("Error when search user: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
