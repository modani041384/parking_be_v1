package com.parking.engine.controller;

import com.parking.engine.consts.ApiPath;
import com.parking.engine.consts.ErrorCode;
import com.parking.engine.dto.PagingDTO;
import com.parking.engine.request.MultifunctionGateDTO;
import com.parking.engine.response.MultifunctionGateResponseDTO;
import com.parking.engine.service.MultifunctionGateService;
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
public class MultifunctionGateController {
    @Autowired
    MultifunctionGateService service;

    @PostMapping(value = ApiPath.MULTIFUNCTION_GATE_ADD_NEW)
    public ResponseEntity<MultifunctionGateResponseDTO> addNew(@RequestBody MultifunctionGateDTO request) {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getOem()) {
                response.setMessage("Input oem");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getProtocol()) {
                response.setMessage("Input protocol");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            String mfgId = UUID.randomUUID().toString();
            request.setMfgId(mfgId);
            boolean isCreate = service.create(request);
            if (!isCreate) {
                response.setMessage("Create new multifunction-gate fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when create multifunction-gate");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when add new multifunction-gate:", ex);
            response.setMessage("Error when add new multifunction-gate: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.MULTIFUNCTION_GATE_UPDATE)
    public ResponseEntity<MultifunctionGateResponseDTO> update(@RequestBody MultifunctionGateDTO request) {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getMfgId()) {
                response.setMessage("Input multifunction-gate-id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getName()) {
                response.setMessage("Input name");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getOem()) {
                response.setMessage("Input oem");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getProtocol()) {
                response.setMessage("Input protocol");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //save record
            boolean isUpdate = service.update(request);
            if (!isUpdate) {
                response.setMessage("Update multifunction-gate fail!!!");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when update multifunction-gate");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when update multifunction-gate:", ex);
            response.setMessage("Error when update multifunction-gate: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = ApiPath.MULTIFUNCTION_GATE_GET_ALL)
    public ResponseEntity<MultifunctionGateResponseDTO> getAll() {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
        try {
            List<MultifunctionGateDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success when get all multifunction-gate");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get all multifunction-gate:", ex);
            response.setMessage("Error when get all multifunction-gate: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Get multifunction gate by id
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.MULTIFUNCTION_GATE_GET_BY_ID)
    public ResponseEntity<MultifunctionGateResponseDTO> findById(@RequestBody MultifunctionGateDTO request) {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (null == request.getId()) {
                response.setMessage("Input id");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            MultifunctionGateDTO data = service.findById(request.getId());
            if (null == data || null == data.getId()) {
                response.setMessage("Cannot find multifunction gate ");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setData(data);
            response.setMessage("Success when find multifunction gate  by id");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when get multifunction gate by id:", ex);
            response.setMessage("Error when get multifunction gate by id: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Delete multifunction gate
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.MULTIFUNCTION_GATE_DELETE)
    public ResponseEntity<MultifunctionGateResponseDTO> delete(@RequestBody MultifunctionGateDTO request) {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
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
                response.setMessage("Error when delete multifunction gate");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setMessage("Success when delete multifunction gate!!!");
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when delete multifunction gate:", ex);
            response.setMessage("Error when delete multifunction gate: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = ApiPath.MULTIFUNCTION_GATE_SEARCH)
    public ResponseEntity<MultifunctionGateResponseDTO> search(@RequestBody PagingDTO<MultifunctionGateDTO> request) {
        MultifunctionGateResponseDTO response = new MultifunctionGateResponseDTO();
        try {
            if (null == request) {
                response.setMessage("Input body");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //search
            List<MultifunctionGateDTO> list = service.search(request.getFilter());
            Page<MultifunctionGateDTO> pages = (Page<MultifunctionGateDTO>) PageableUtils.toPage(list, request);
            response.setMessage("Success when search multifunction-gate!!!");
            response.setPages(pages);
            response.setErrorCode(ErrorCode.SUCCESS);
        } catch (Exception ex) {
            log.error("Error when search multifunction-gate:", ex);
            response.setMessage("Error when search multifunction-gate: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
