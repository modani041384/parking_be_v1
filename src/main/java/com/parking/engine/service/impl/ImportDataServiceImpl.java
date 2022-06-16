package com.parking.engine.service.impl;

import com.google.common.reflect.TypeToken;
import com.parking.engine.request.RoleDTO;
import com.parking.engine.request.UserDTO;
import com.parking.engine.request.UserRoleDTO;
import com.parking.engine.service.ImportDataService;
import com.parking.engine.service.RoleService;
import com.parking.engine.service.UserRoleService;
import com.parking.engine.service.UserService;
import com.parking.engine.utils.FileResourcesUtil;
import com.parking.engine.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Slf4j
public class ImportDataServiceImpl implements ImportDataService {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    FileResourcesUtil fileResourcesUtil;

    @Value("${import.data.isRun}")
    String isRun;

    @Override
    public void importDataInitial() {
        if (Boolean.parseBoolean(isRun)) {
            //import role initial
            Type listTypeRole = new TypeToken<List<RoleDTO>>() {}.getType();
            String role = fileResourcesUtil.convertInputStreamToStr(fileResourcesUtil.getFileFromResourceAsStream("import_data/role.json"));
            List<RoleDTO> roles = GsonUtils.convertJsonToArrayByString(role, listTypeRole);
            if (roles != null && roles.size() > 0) {
                //save all data
                roleService.createList(roles);
            }
            //import user initial
            Type listTypeUser = new TypeToken<List<UserDTO>>() {}.getType();
            String user = fileResourcesUtil.convertInputStreamToStr(fileResourcesUtil.getFileFromResourceAsStream("import_data/user.json"));
            List<UserDTO> users = GsonUtils.convertJsonToArrayByString(user, listTypeUser);
            if (users != null && users.size() > 0) {
                //save all data
                userService.createListRawData(users);
            }
            //import user_role initial
            Type listTypeUserRole = new TypeToken<List<UserRoleDTO>>() {}.getType();
            String userRole = fileResourcesUtil.convertInputStreamToStr(fileResourcesUtil.getFileFromResourceAsStream("import_data/user_role.json"));
            List<UserRoleDTO> userRoles = GsonUtils.convertJsonToArrayByString(userRole, listTypeUserRole);
            if (userRoles != null && userRoles.size() > 0) {
                //save all data
                userRoleService.createList(userRoles);
            }
        }
    }
    //end
}
