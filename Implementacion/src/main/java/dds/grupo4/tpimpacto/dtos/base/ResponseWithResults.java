package dds.grupo4.tpimpacto.dtos.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ResponseWithResults<T> extends BaseResponse {
    private List<T> results;

    public ResponseWithResults(HttpStatus status, List<T> results) {
        super(status);
        this.results = results;
    }

    public ResponseWithResults(HttpStatus status, String message, List<T> results) {
        super(status, message);
        this.results = results;
    }
}
