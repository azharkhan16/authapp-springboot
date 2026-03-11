package in.azhar.authapp.service;

import in.azhar.authapp.io.ProfileRequest;
import in.azhar.authapp.io.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile(ProfileRequest request);
}
