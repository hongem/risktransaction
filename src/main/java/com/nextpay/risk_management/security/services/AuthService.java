package com.nextpay.risk_management.security.services;

import com.nextpay.risk_management.dto.request.LoginRequest;
import com.nextpay.risk_management.dto.request.SignupRequest;
import com.nextpay.risk_management.dto.response.JwtResponse;
import com.nextpay.risk_management.dto.response.MessageResponse;
import com.nextpay.risk_management.model.*;
import com.nextpay.risk_management.repository.RoleRepo;
import com.nextpay.risk_management.repository.UserRepo;
import com .nextpay.risk_management.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    public Optional<User> getUserFromToken(HttpServletRequest req){
        return userRepository.findByEmail(jwtUtils.getEmailFromJwtToken(jwtUtils.resolveToken(req)));
    }

    boolean CheckLength(String str){
        if(str.length() < 20){
            return true;
        }
        return false;
    }

    boolean CheckEmail(String email) {
        String EMAIL_PATTERN = "^[a-z]{1,}([0-9]{0,})+@((vimo)|(mpos)).vn$";
        if((Pattern.matches(EMAIL_PATTERN, email)==true && CheckLength(email) == true)){
            return true;
        }
        return false;
    }

    boolean CheckPassword(String password) {
        String PASS_PATTERN = "^\\w*(?=\\w{6,})(?=\\w*[0-9])(?=\\w*[a-z])(?=\\w*[A-Z])\\w*$";
        if(CheckLength(password) == true && Pattern.matches(PASS_PATTERN, password) == true){
            return true;
        }
        return false;
    }

    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if(CheckEmail(signUpRequest.getEmail())==false){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error: Email Format!"));
        }

        if(CheckPassword(signUpRequest.getPassword())==false){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error: Email Format!"));
        }
        try {
            User user = new User(
                    signUpRequest.getEmail(),
                    passwordEncoder.encode(signUpRequest.getPassword())
            );

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Error: Email is already in use!"));
            }

            Set<Role> roles = new HashSet<>();
            user.setRoles(roles);
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setRoles(roles);
            //set default account status
            user.setAccStatus(EaccountStatus.Acc_Active.toString());


            userRepository.save(user);

            MessageResponse message = new MessageResponse(HttpStatus.OK.value(),"Success");
            return ResponseEntity
                    .ok()
                    .body(message);
        }catch (Exception ex){
            //Log
            MessageResponse message = new MessageResponse(HttpStatus.BAD_REQUEST.value(), "Something wrong");
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

    }


    public ResponseEntity<?> login(LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            //check active acc
            Optional<User> user = userRepo.findByEmail(loginRequest.getEmail());
            if(user.isPresent()){
                User thisUser;
                thisUser = user.get();
                if(thisUser.getAccStatus() == null) {
                    return new ResponseEntity<>(new MessageResponse(401,"Account not active!"),HttpStatus.UNAUTHORIZED);
                }
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getEmail()));
        }catch(AuthenticationException e){
            return new ResponseEntity<>(new MessageResponse(401,"Email or password not correct!"),HttpStatus.UNAUTHORIZED);
        }
    }
}
