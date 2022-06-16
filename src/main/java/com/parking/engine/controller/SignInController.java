package com.parking.engine.controller;

import com.parking.engine.security.CustomUserDetails;
import com.parking.engine.security.JwtTokenProvider;
import com.parking.engine.consts.ApiPath;
import com.parking.engine.entity.User;
import com.parking.engine.repository.UserRepository;
import com.parking.engine.request.UserDTO;
import com.parking.engine.response.UserResponseDTO;
import com.parking.engine.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class SignInController {
    @Autowired
    RoleService service;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Create user
     * @param request
     * @return
     */
    @PostMapping(value = ApiPath.USER_SIGN_IN)
    public ResponseEntity<UserResponseDTO> authenticateUser(HttpServletRequest request, @RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            //verify userName, password
            User user = userRepository.findByUserName(userDTO.getUserName());
            if (user == null || user.getId() == null) {
                response.setMessage("Username don't correct ");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            boolean match = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());
            if (!match) {
                response.setMessage("Username and password don't correct ");
                return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            //get roles of user
            List<String> roles = service.getRoles(user.getUserId());
            UserDetails userDetails = new CustomUserDetails(user, roles);
            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // return jwt
            String jwt = tokenProvider.generateToken(user.getUserId());
            response.setAccessToken(jwt);
            response.setTokenType("Bearer");
            response.setRoles(roles);

        } catch (Exception ex) {
            log.error("Error when sign in:", ex);
            response.setMessage("Error when sign in: " + ex.getMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    //end
}
