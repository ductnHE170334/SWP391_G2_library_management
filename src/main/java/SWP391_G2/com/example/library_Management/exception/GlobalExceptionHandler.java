package SWP391_G2.com.example.library_Management.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý tất cả các ngoại lệ chưa được bắt
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        // Log thông tin chi tiết của ngoại lệ
        System.err.println("Exception occurred: " + exception.getMessage());
        return ResponseEntity.badRequest().body("An unexpected error occurred: " + exception.getMessage());
    }

    // Xử lý RuntimeException
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        // Log thông tin ngoại lệ runtime
        System.err.println("RuntimeException occurred: " + exception.getMessage());
        return ResponseEntity.badRequest().body("A runtime error occurred: " + exception.getMessage());
    }

    // Xử lý lỗi validation từ MethodArgumentNotValidException
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) {
        // Lấy tất cả các lỗi field validation
        List<String> errorMessages = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        // Ghép tất cả các thông báo lỗi thành một chuỗi, mỗi lỗi nằm trên một dòng
        String combinedErrorMessage = String.join("\n", errorMessages);

        // Log chi tiết các lỗi
        System.err.println("Validation errors: " + combinedErrorMessage);

        return ResponseEntity.badRequest().body("Validation failed:\n" + combinedErrorMessage);
    }
}
