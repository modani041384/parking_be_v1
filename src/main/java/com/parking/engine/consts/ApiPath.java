package com.parking.engine.consts;

public interface ApiPath {
    String API = "/api/v1";

    //user
    String USER_CREATE =  API + "/user/create";
    String USER_UPDATE =  API + "/user/update";
    String USER_GET_BY_ID =  API + "/user/get-by-id";
    String USER_FIND_ALL =  API + "/user/find-all";
    String USER_DELETE =  API + "/user/delete";
    String USER_SEARCH = API + "/user/search";

    //role
    String ROLE_CREATE =  API + "/user-role/create";
    String ROLE_UPDATE =  API + "/user-role/update";
    String ROLE_GET_BY_ID =  API + "/user-role/get-by-id";
    String ROLE_FIND_ALL =  API + "/user-role/find-all";

    //sign-in
    String USER_SIGN_IN = API + "/sign-in";

    //ping
    String PING = API + "/smart-parking/ping";
    String PARKING_ADD_NEW = API + "/parking/add-new";
    String PARKING_GET_ALL = API + "/parking/get-all";
    String PARKING_SEARCH = API + "/parking/search";
    String PARKING_UPDATE_GENERAL_INFORMATION =  API + "/parking/update-general-information";
    String PARKING_UPDATE_PARKING_LANE = API + "/parking/update-parking-lane";
    String PARKING_UPDATE_SLOT_TYPE_CONFIG = API + "/parking/update-slot-type-configure";
    String PARKING_UPDATE_CARD_ASSIGNMENT = API + "/parking/update-card-assignment";
    String PARKING_UPDATE_PRICE_BOOKING_ASSIGNMENT = API + "/parking/update-price-booking-assignment";
    String PARKING_GET_BY_ID = API + "/parking/get-by-id";
    String PARKING_DELETE = API + "/parking/delete";

    //parking-slot
    String PARKING_SLOT_ADD_NEW = API + "/parking-slot/add-new";
    String PARKING_SLOT_UPDATE = API + "/parking-slot/update";
    String PARKING_SLOT_GET_ALL = API + "/parking-slot/get-all";
    String PARKING_SLOT_GET_BY_ID = API + "/parking-slot/get-by-id";
    String PARKING_SLOT_DELETE = API + "/parking-slot/delete";
    String PARKING_SLOT_SEARCH = API + "/parking-slot/search";

    //camera
    String CAMERA_ADD_NEW = API + "/camera/add-new";
    String CAMERA_UPDATE = API + "/camera/update";
    String CAMERA_GET_ALL = API + "/camera/get-all";
    String CAMERA_GET_BY_ID = API + "/camera/get-by-id";
    String CAMERA_DELETE = API + "/camera/delete";
    String CAMERA_SEARCH = API + "/camera/search";

    //multifunction-gate
    String MULTIFUNCTION_GATE_ADD_NEW = API + "/multifunction-gate/add-new";
    String MULTIFUNCTION_GATE_UPDATE = API + "/multifunction-gate/update";
    String MULTIFUNCTION_GATE_GET_ALL = API + "/multifunction-gate/get-all";
    String MULTIFUNCTION_GATE_GET_BY_ID = API + "/multifunction-gate/get-by-id";
    String MULTIFUNCTION_GATE_DELETE = API + "/multifunction-gate/delete";
    String MULTIFUNCTION_GATE_SEARCH = API + "/multifunction-gate/search";

    //parking-lane
    String PARKING_LANE_ADD_NEW = API + "/parking-lane/add-new";
    String PARKING_LANE_UPDATE = API + "/parking-lane/update";
    String PARKING_LANE_GET_ALL =  API + "/parking-lane/get-all";
    String PARKING_LANE_GET_BY_ID = API + "/parking-lane/get-by-id";
    String PARKING_LANE_DELETE = API + "/parking-lane/delete";
    String PARKING_LANE_SEARCH = API + "/parking-lane/search";

    //card
    String CARD_ADD_NEW = API + "/card/add-new";
    String CARD_UPDATE = API + "/card/update";
    String CARD_GET_ALL = API + "/card/get-all";
    String CARD_GET_BY_ID = API + "/card/get-by-id";
    String CARD_DELETE = API + "/card/delete";
    String CARD_SEARCH = API + "/card/search";

    //device-status
    String DEVICE_STATUS_NEW = API + "/device-status/add-new";
    String DEVICE_STATUS_UPDATE = API + "/device-status/update";
    String DEVICE_STATUS_GET_ALL = API + "/device-status/get-all";
    String DEVICE_STATUS_GET_BY_ID = API + "/device-status/get-by-id";
    String DEVICE_STATUS_DELETE = API + "/device-status/delete";
    String DEVICE_STATUS_SEARCH = API + "/device-status/search";

    //end
}
