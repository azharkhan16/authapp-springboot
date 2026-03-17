package in.azhar.authapp.controller;

import in.azhar.authapp.io.ProfileRequest;
import in.azhar.authapp.io.ProfileResponse;
import in.azhar.authapp.service.EmailService;
import in.azhar.authapp.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final EmailService emailService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request) {
        System.out.println("REGISTER API HIT -> " + request.getEmail());
        ProfileResponse response = profileService.createProfile(request);
        //TODO: send welcome email
        emailService.sendWelcomeEmail(response.getEmail(), response.getName());
        return response;
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        return profileService.getProfile(email);
    }

}
