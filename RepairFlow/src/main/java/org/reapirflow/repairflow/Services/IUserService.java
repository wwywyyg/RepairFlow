package org.reapirflow.repairflow.Services;

import org.reapirflow.repairflow.Pojo.UserPojo.Dto.AdminUserUpdateReq;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginReq;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.Login.UserLoginResponse;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.UserCreateReq;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.UserDto;
import org.reapirflow.repairflow.Pojo.UserPojo.Dto.UserUpdateReq;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IUserService {
    /***
     *  Create User
     * @param userCreateReq
     * @return
     */
    UserDto createUser(UserCreateReq userCreateReq);

    /***
     *   Get One User Info
     * @param id
     * @return
     */
    UserDto getUser(Long id);

    /***
     *  Get All User Info
     * @return
     */
    List<UserDto> getAllUsers();


    /***
     *  Get User Info By Page
     * @param page
     * @param size  // how many in one page
     * @return
     */
    Page<UserDto> PageUsers(int page, int size);

    /***
     * User Update Info
     * @param id
     * @param userUpdateReq
     * @return
     */
    UserDto userUpdateSelf(Long id, UserUpdateReq userUpdateReq);

    /***
     * Admin update User Info
     * @param id
     * @param userUpdateReq
     * @return
     */
    UserDto userUpdateAdmin(Long id, AdminUserUpdateReq userUpdateReq);
    /***
     *  Delete User
     * @param id
     */
    void deleteUser(Long id);

    UserLoginResponse userLogin(UserLoginReq userLoginReq);
}
