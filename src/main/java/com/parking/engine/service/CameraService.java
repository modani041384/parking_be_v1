package com.parking.engine.service;

import com.parking.engine.request.CameraDTO;

import java.util.List;

public interface CameraService extends BaseService<CameraDTO> {
    List<CameraDTO> search(CameraDTO filter);
}
