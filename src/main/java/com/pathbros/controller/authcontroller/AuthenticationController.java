package com.pathbros.controller.authcontroller;


import com.pathbros.dtos.authentication.AuthRequest;
import com.pathbros.dtos.authentication.AuthResponse;
import com.pathbros.dtos.user.UserSignUpDto;
import com.pathbros.enums.Role;
import com.pathbros.jwt.JwtUtils;
import com.pathbros.repositories.AdminRepo;
import com.pathbros.repositories.CompanyRepo;
import com.pathbros.repositories.UserRepo;
import com.pathbros.service.userdetailservice.CustomUserDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pathbros.models.User;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailsService;
    private final JwtUtils jwtUtils;

    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private final CompanyRepo companyRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup/user")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto){
        if (userRepo.findByUserEmailAndUserIsActiveTrue(userSignUpDto.getUserEmail()).isPresent()){
            return ResponseEntity.badRequest().body("User already exists!");
        }


        User user=new User();
        user.setUserName(userSignUpDto.getUserName());
        user.setUserEmail(userSignUpDto.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(userSignUpDto.getUserPassword()));
        user.setUserLocation(userSignUpDto.getUserLocation());
        user.setUserSkillSets(userSignUpDto.getUserSkillSets());
        user.setUserCollegeName(userSignUpDto.getUserCollegeName());
        user.setUserExperience(userSignUpDto.getUserExperience());
        user.setUserRole(Role.ROLE_USER);

        userRepo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Signed Up Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var userDetails=userDetailsService.loadUserByUsername(request.getEmail());

        String jwt=jwtUtils
                .generateToken(userDetails.getUsername(),
                        Role.valueOf(userDetails.getAuthorities().iterator().next().getAuthority())
                );

        return ResponseEntity.ok(new AuthResponse(jwt));

    }


}
