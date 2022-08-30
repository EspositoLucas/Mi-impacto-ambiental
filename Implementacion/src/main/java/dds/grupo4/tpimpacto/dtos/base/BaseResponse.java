package dds.grupo4.tpimpacto.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }
}
