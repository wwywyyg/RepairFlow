package org.reapirflow.repairflow.Exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reapirflow.repairflow.Pojo.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.net.BindException;
import java.nio.file.AccessDeniedException;

//@RestControllerAdvice
//public class GlobalExceptionHandlerAdvice {
//
//    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
//
//    private ResponseEntity<ResponseMessage> build(int http, String msg, Object data) {
//        return ResponseEntity.status(http).body(new ResponseMessage(http, msg, data));
//    }
//
//
//
//    // 404
//    // 1) 404 资源不存在
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResponseMessage> handleNotFound(ResourceNotFoundException e) {
//        log.warn("404 Not Found: {}", e.getMessage());
//        return build(404, e.getMessage(), null);
//    }
//
//
//    // 2’) 400 Bean Validation（query/path）
//    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
//    public ResponseEntity<ResponseMessage> handleConstraintViolation(jakarta.validation.ConstraintViolationException e) {
//        String msg = e.getConstraintViolations().stream()
//                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
//                .findFirst().orElse("Validation error");
//        return build(400, msg, null);
//    }
//
//    // 3) 409 唯一键/外键冲突（Email 已存在等）
//    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
//    public ResponseEntity<ResponseMessage> handleDataIntegrity(org.springframework.dao.DataIntegrityViolationException e) {
//        String msg = "Data conflict";
//        // 你也可以按消息里是否包含 email/UK 名字来细化
//        if (e.getMessage() != null && e.getMessage().toLowerCase().contains("email")) {
//            msg = "Email already exists";
//        }
//        log.warn("409 Conflict: {}", e.getMessage());
//        return build(409, msg, null);
//    }
//
//    // 4) 401 未认证（JWT 无效/过期等）
//    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
//    public ResponseEntity<ResponseMessage> handleAuth(org.springframework.security.core.AuthenticationException e) {
//        return build(401, "Unauthorized", null);
//    }
//
//    // 5) 403 无权限
//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public ResponseEntity<ResponseMessage> handleAccessDenied(org.springframework.security.access.AccessDeniedException e) {
//        return build(403, "Forbidden", null);
//    }
//
//    // 6) 400 类型不匹配/请求体不可读
//    @ExceptionHandler({
//            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
//            org.springframework.http.converter.HttpMessageNotReadableException.class
//    })
//    public ResponseEntity<ResponseMessage> handleBadRequest(Exception e) {
//        return build(400, "Bad request", null);
//    }
//
//    // 7) 405 方法不被允许
//    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ResponseMessage> handleMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException e) {
//        return build(405, "Method Not Allowed", null);
//    }
//
//    // 8) 413 上传过大（如使用 Spring Boot 文件上传限制）
//    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
//    public ResponseEntity<ResponseMessage> handleMaxUpload(org.springframework.web.multipart.MaxUploadSizeExceededException e) {
//        return build(413, "Uploaded file too large", null);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
//        String errorMessage = e.getBindingResult().getFieldErrors().stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .findFirst()
//                .orElse("Validation error");
//        return ResponseEntity.status(400).body(new ResponseMessage(400, errorMessage, null));
//    }
//
//    // 9) 500 兜底
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessage> handleException(Exception e, jakarta.servlet.http.HttpServletRequest req) {
//        log.error("500 at {}: {}", req.getRequestURI(), e.getMessage(), e);
//        return build(500, "Internal Server Error", null);
//    }
//    //username not found
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseMessage handleUsernameNotFound(UsernameNotFoundException e) {
//        log.error("403 Forbidden: {}", e.getMessage());
//        return new ResponseMessage(404, e.getMessage(), null);
//    }
//    // password invalid
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseMessage handleBadCredentials(BadCredentialsException e) {
//        log.error("Bad credentials: {}", e.getMessage());
//        return new ResponseMessage(401, e.getMessage(), null);
//    }
//}
//
//@RestControllerAdvice
//public class GlobalExceptionHandlerAdvice {
//
//    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
//
//    private ResponseEntity<ResponseMessage<?>> build(int http, String msg, Object data) {
//        return ResponseEntity.status(http).body(new ResponseMessage<>(http, msg, data));
//    }
//
//    // 404 资源不存在（你自己的业务异常）
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResponseMessage<?>> handleNotFound(ResourceNotFoundException e) {
//        log.warn("404 Not Found: {}", e.getMessage());
//        return build(404, e.getMessage(), null);
//    }
//
//    // 400 Bean Validation（方法参数上的约束，如 @RequestParam / @PathVariable）
//    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
//    public ResponseEntity<ResponseMessage<?>> handleConstraintViolation(jakarta.validation.ConstraintViolationException e) {
//        String msg = e.getConstraintViolations().stream()
//                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
//                .findFirst().orElse("Validation error");
//        return build(400, msg, null);
//    }
//
//    // 400 对象绑定失败（表单/查询参数绑定到对象时）
//    @ExceptionHandler(org.springframework.validation.BindException.class)
//    public ResponseEntity<ResponseMessage<?>> handleBindException(org.springframework.validation.BindException e) {
//        String msg = e.getBindingResult().getFieldErrors().stream()
//                .map(err -> err.getField() + ": " + err.getDefaultMessage())
//                .findFirst().orElse("Validation error");
//        return build(400, msg, null);
//    }
//
//    // 400 请求体不可读 / 类型不匹配
//    @ExceptionHandler({
//            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
//            org.springframework.http.converter.HttpMessageNotReadableException.class
//    })
//    public ResponseEntity<ResponseMessage<?>> handleBadRequest(Exception e) {
//        return build(400, "Bad request", null);
//    }
//
//    // 409 唯一键/外键冲突（Email 已存在等）
//    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
//    public ResponseEntity<ResponseMessage<?>> handleDataIntegrity(org.springframework.dao.DataIntegrityViolationException e) {
//        String msg = "Data conflict";
//        String lower = String.valueOf(e.getMessage()).toLowerCase();
//        if (lower.contains("email") || lower.contains("uk_") || lower.contains("unique")) {
//            msg = "Email already exists";
//        }
//        log.warn("409 Conflict: {}", e.getMessage());
//        return build(409, msg, null);
//    }
//
//    // 401 未认证（JWT 无效/过期等，注意它是 BadCredentials/UsernameNotFound 的父类之一）
//    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
//    public ResponseEntity<ResponseMessage<?>> handleAuth(org.springframework.security.core.AuthenticationException e) {
//        return build(401, "Unauthorized", null);
//    }
//
//    // 具体到用户不存在（登录）
//    @ExceptionHandler(org.springframework.security.core.userdetails.UsernameNotFoundException.class)
//    public ResponseEntity<ResponseMessage<?>> handleUsernameNotFound(org.springframework.security.core.userdetails.UsernameNotFoundException e) {
//        log.warn("404 Username not found: {}", e.getMessage());
//        return build(404, e.getMessage(), null);
//    }
//
//    // 具体到密码错误（登录）
//    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
//    public ResponseEntity<ResponseMessage<?>> handleBadCredentials(org.springframework.security.authentication.BadCredentialsException e) {
//        log.warn("401 Bad credentials: {}", e.getMessage());
//        return build(401, e.getMessage(), null);
//    }
//
//    // 403 无权限
//    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
//    public ResponseEntity<ResponseMessage<?>> handleAccessDenied(org.springframework.security.access.AccessDeniedException e) {
//        return build(403, "Forbidden", null);
//    }
//
//    // 405 方法不被允许
//    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ResponseMessage<?>> handleMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException e) {
//        return build(405, "Method Not Allowed", null);
//    }
//
//    // 413 上传过大
//    @ExceptionHandler(org.springframework.web.multipart.MaxUploadSizeExceededException.class)
//    public ResponseEntity<ResponseMessage<?>> handleMaxUpload(org.springframework.web.multipart.MaxUploadSizeExceededException e) {
//        return build(413, "Uploaded file too large", null);
//    }
//
//    // 400 @RequestBody 校验失败（JSON -> 对象，带 @Valid）
//    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseMessage<?>> handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException e) {
//        String errorMessage = e.getBindingResult().getFieldErrors().stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .findFirst()
//                .orElse("Validation error");
//        return build(400, errorMessage, null);
//    }
//
//    // 500 兜底
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessage<?>> handleException(Exception e, jakarta.servlet.http.HttpServletRequest req) {
//        log.error("500 at {}: {}", req.getRequestURI(), e.getMessage(), e);
//        return build(500, "Internal Server Error", null);
//    }
//}
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    private ResponseEntity<ResponseMessage<?>> build(int http, String msg) {
        return ResponseEntity.status(http).body(new ResponseMessage<>(http, msg, null));
    }

    // 统一处理你抛的 AppException（核心）
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseMessage<?>> handleApp(AppException e, HttpServletRequest req) {
        HttpStatus status = switch (e.getFamily()) {
            case DATA_NOT_FOUND -> HttpStatus.NOT_FOUND;        // 404
            case DATA_CONFLICT  -> HttpStatus.CONFLICT;         // 409
            case VALIDATION     -> HttpStatus.BAD_REQUEST;      // 400
            case AUTH           -> HttpStatus.UNAUTHORIZED;     // 401
            case PERMISSION     -> HttpStatus.FORBIDDEN;        // 403
            default             -> HttpStatus.INTERNAL_SERVER_ERROR; // 500
        };
        // 统一日志
        if (status.is5xxServerError()) {
            log.error("{} {} -> {}", req.getMethod(), req.getRequestURI(), e.getMessage(), e);
        } else {
            log.warn("{} {} -> {}({})", req.getMethod(), req.getRequestURI(), status.value(), e.getMessage());
        }
        return build(status.value(), e.getMessage());
    }

    // 兼容 Spring/第三方常见异常，归并到家族（可逐步补齐）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage<?>> handleBodyValidation(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst().orElse("Validation error");
        return build(400, msg);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<ResponseMessage<?>> handleParamValidation(jakarta.validation.ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .findFirst().orElse("Validation error");
        return build(400, msg);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseMessage<?>> handleUkFk(DataIntegrityViolationException e) {
        String msg = (e.getMessage() != null && e.getMessage().toLowerCase().contains("email"))
                ? "Email already exists" : "Data conflict";
        return build(409, msg);
    }

    @ExceptionHandler({ BadCredentialsException.class, UsernameNotFoundException.class,
            org.springframework.security.core.AuthenticationException.class })
    public ResponseEntity<ResponseMessage<?>> handleAuth(Exception e) {
        return build(401, "Unauthorized");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage<?>> handleForbidden(AccessDeniedException e) {
        return build(403, "Forbidden");
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            BindException.class
    })
    public ResponseEntity<ResponseMessage<?>> handleBadRequest(Exception e) {
        return build(400, "Bad request");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseMessage<?>> handleMethod(HttpRequestMethodNotSupportedException e) {
        return build(405, "Method Not Allowed");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage<?>> handleMax(MaxUploadSizeExceededException e) {
        return build(413, "Uploaded file too large");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage<?>> handleAny(Exception e, HttpServletRequest req) {
        // 兜底
        LoggerFactory.getLogger(getClass()).error("500 at {}: {}", req.getRequestURI(), e.getMessage(), e);
        return build(500, "Internal Server Error");
    }
}