package dds.grupo4.tpimpacto.utils;

import dds.grupo4.tpimpacto.dtos.base.BaseResponse;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    public static <T extends BaseResponse> ResponseEntity<T> toResponseEntity(T response) {
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
