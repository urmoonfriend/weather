package tech.jusan.weather.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.jusan.weather.enums.ResultCode;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultMessage<T> {
    private boolean success;
    private ResultCode code;
    private T message;
    private String error;

    public static <T> ResponseEntity<ResultMessage<T>> success(T message) {
        return ResponseEntity.ok(
                new ResultMessage<T>()
                        .setSuccess(true)
                        .setCode(ResultCode.OK)
                        .setMessage(message)
        );
    }

    public static <T> ResponseEntity<ResultMessage<T>> notFound(String error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ResultMessage<T>()
                                .setSuccess(false)
                                .setCode(ResultCode.NOT_FOUND)
                                .setError(error)
                );
    }

    public static <T> ResponseEntity<ResultMessage<T>> unknownError() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(
                        new ResultMessage<T>()
                                .setSuccess(false)
                                .setCode(ResultCode.UNKNOWN_ERROR)
                );
    }

    public static <T> ResponseEntity<ResultMessage<T>> serverError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ResultMessage<T>()
                                .setSuccess(false)
                                .setCode(ResultCode.SERVER_ERROR)
                );
    }
}
