package org.reapirflow.repairflow.Exception;

/**
 * @author guangyang
 * @date 10/9/25 AM5:47
 * @description TODO: Description
 */
public enum ErrorCombine {
    DATA_NOT_FOUND,     // 查无此数据
    DATA_CONFLICT,      // 唯一键/状态冲突
    VALIDATION,         // 参数校验
    AUTH,               // 未认证/登录失败
    PERMISSION,         // 已登录但无权限
    SYSTEM              // 非预期错误
}
