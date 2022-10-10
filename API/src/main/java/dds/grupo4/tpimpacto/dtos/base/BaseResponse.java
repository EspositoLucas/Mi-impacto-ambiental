package dds.grupo4.tpimpacto.dtos.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse {

    @JsonIgnore
    private HttpStatus status;

    private String message;

    public BaseResponse(HttpStatus status) {
        this.status = status;
    }

    public BaseResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
