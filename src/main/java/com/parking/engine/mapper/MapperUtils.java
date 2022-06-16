package com.parking.engine.mapper;

import com.parking.engine.entity.Camera;
import com.parking.engine.entity.ParkingLane;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtils {

    /**
     * Get parking lane name
     * @param camera
     */
    public static void getParkingLaneNameInit(Camera camera) {
        //get parking lane
        String parkingLaneName = StringUtils.EMPTY;
        List<ParkingLane> parkingLanes = camera.getParkingLanes().stream().collect(Collectors.toList());
        if (null != parkingLanes && 0 < parkingLanes.size()) {
            parkingLaneName = parkingLanes.get(0).getName();
        }
        camera.setParkingLaneName(parkingLaneName);
    }

    //end
}
