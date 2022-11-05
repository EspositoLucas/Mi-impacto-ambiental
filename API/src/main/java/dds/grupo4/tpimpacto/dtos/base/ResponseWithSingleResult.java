package dds.grupo4.tpimpacto.dtos.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseWithSingleResult<T> extends BaseResponse {
    private T result;

    public ResponseWithSingleResult(HttpStatus status, T result) {
        super(status);
        this.result = result;
    }

    public ResponseWithSingleResult(HttpStatus status, String message, T result) {
        super(status, message);
        this.result = result;
    }
}
