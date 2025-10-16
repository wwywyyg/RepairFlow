package org.reapirflow.repairflow.Controller;

import jakarta.validation.Valid;
import org.reapirflow.repairflow.Pojo.ResponseMessage;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.*;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginReq;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginResponse;
import org.reapirflow.repairflow.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/user") // localhost:8088/user/***
@Validated
public class UserController {
    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    //REST
    //add User
    @PostMapping // URL:localhost:8080/user
    public ResponseEntity<ResponseMessage<UserDto>> createUser(@RequestBody @Valid UserCreateReq userCreateReq) {
        UserDto created = userService.createUser(userCreateReq);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(ResponseMessage.success("User Create Success!" ,created));
    }


    //Read User
    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDto>> getOne(@PathVariable Long id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(ResponseMessage.success(userDto));
    }
    // read List
    @GetMapping
    public ResponseEntity<ResponseMessage<Page<UserDto>>> listUsers(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "20") int size) {
        Page<UserDto> result = userService.PageUsers(page,size);
        return ResponseEntity.ok(ResponseMessage.success(result));
    }



    // update User
    // User Update
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<UserDto>> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateReq userUpdateReq) {
        UserDto userDto = userService.userUpdateSelf(id,userUpdateReq);
        return ResponseEntity.ok(ResponseMessage.success("User Update Success!!",userDto));
    }
    // Admin update
    @PatchMapping("/{id}/admin")
    public ResponseEntity<ResponseMessage<UserDto>> adminUpdate(@PathVariable Long id , @RequestBody @Valid AdminUserUpdateReq adminUserUpdateReq) {
        UserDto userDto = userService.userUpdateAdmin(id,adminUserUpdateReq);
        return ResponseEntity.ok(ResponseMessage.success("Admin Update Success!!",userDto));
    }


    //delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ResponseMessage.success("User Delete Success", null));
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<UserDto>> userLogin(@RequestBody @Valid UserLoginReq userLoginReq) {
        UserLoginResponse userLoginResponse = userService.userLogin(userLoginReq);
        return ResponseEntity.ok(ResponseMessage.success("Login Success! " , userLoginResponse.getUser()));
    }
}
