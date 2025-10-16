package org.reapirflow.repairflow.Services;

import jakarta.transaction.Transactional;
import org.reapirflow.repairflow.Exception.AppException;
import org.reapirflow.repairflow.Exception.ResourceNotFoundException;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.*;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginReq;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginResponse;
import org.reapirflow.repairflow.Pojo.UserPojo.Role;
import org.reapirflow.repairflow.Pojo.UserPojo.User;
import org.reapirflow.repairflow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // BCrypt

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserDto createUser(UserCreateReq userCreateReq) {
        // check email exist
        String email = userCreateReq.email().trim().toLowerCase();
        if(userRepository.existsByEmail(userCreateReq.email())){
            throw AppException.conflict("Email already exists :" + userCreateReq.email());
        }
        User user = new User();
        user.setFirstName(userCreateReq.firstName());
        user.setLastName(userCreateReq.lastName());
        user.setEmail(userCreateReq.email());
        user.setPhone(userCreateReq.phone());
        // Encrypt Password
        String encodedPassword = passwordEncoder.encode(userCreateReq.password());
        user.setPasswordHash(encodedPassword);

        //check user role stats and active
        if(user.getRole() == null){
            user.setRole(Role.CUSTOMER);
        }
        user.setActive(true);

        User saved  = userRepository.save(user);


        return UserMapper.toUserDto(saved);
    }

    // get user
    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> AppException.conflict("User not found - " + "User Id :" + id));
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDto)
                .toList();
    }

    @Override
    public Page<UserDto> PageUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest)
                        .map(UserMapper::toUserDto);

    }

    @Transactional
    @Override
    public UserDto userUpdateSelf(Long id, UserUpdateReq userUpdateReq) {
        User user = userRepository.findById(id).orElseThrow(() -> AppException.conflict("User not found - id: " + id));
        user.setFirstName(userUpdateReq.firstName());
        user.setLastName(userUpdateReq.lastName());
        user.setPhone(userUpdateReq.phone());
        User saved = userRepository.save(user);
        return UserMapper.toUserDto(saved);
    }

    @Transactional
    @Override
    public UserDto userUpdateAdmin(Long id, AdminUserUpdateReq userUpdateAdminReq) {
        User user = userRepository.findById(id).orElseThrow(() ->  AppException.conflict("User not found - id: " + id));
        if(userUpdateAdminReq.role() != null){
            user.setRole(userUpdateAdminReq.role());
        }
        if(userUpdateAdminReq.isActive() != null){
            user.setActive(userUpdateAdminReq.isActive());
        }
        return UserMapper.toUserDto(user);

    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw AppException.conflict("User not found" + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserLoginResponse userLogin(UserLoginReq userLoginReq) {
        User user = userRepository.findByEmail(userLoginReq.getEmail())
                .orElseThrow(() -> AppException.conflict("Email not found"));
        if (!passwordEncoder.matches(userLoginReq.getPassword(), user.getPasswordHash())) {
            throw AppException.auth("Invalid password");
        }
        String token = "token";
        return new UserLoginResponse(token,new UserDto(user));

    }
}
