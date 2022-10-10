package dds.grupo4.tpimpacto.dtos;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LoginResponse extends BaseResponse {
    private String accessToken;

    public LoginResponse(HttpStatus status, String message) {
        super(status, message);
    }
}
