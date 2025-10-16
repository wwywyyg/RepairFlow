package org.reapirflow.repairflow.Exception;

/**
 * @author guangyang
 * @date 10/9/25 AM5:48
 * @description TODO: Description
 */
public class AppException extends RuntimeException {
    private final ErrorCombine family;
    private final Object details; // 可选：给前端调试或定位，生产可置空

    public AppException(ErrorCombine family, String message) {
        this(family, message, null);
    }
    public AppException(ErrorCombine family, String message, Object details) {
        super(message);
        this.family = family;
        this.details = details;
    }
    public ErrorCombine getFamily() { return family; }
    public Object getDetails() { return details; }

    // 便捷工厂（可选）
    public static AppException notFound(String msg)   { return new AppException(ErrorCombine.DATA_NOT_FOUND, msg); }
    public static AppException conflict(String msg)   { return new AppException(ErrorCombine.DATA_CONFLICT, msg); }
    public static AppException badInput(String msg)   { return new AppException(ErrorCombine.VALIDATION, msg); }
    public static AppException auth(String msg)       { return new AppException(ErrorCombine.AUTH, msg); }
    public static AppException forbidden(String msg)  { return new AppException(ErrorCombine.PERMISSION, msg); }
}
