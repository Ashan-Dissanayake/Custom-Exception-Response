package lk.ashan.demo.util;

import lk.ashan.demo.exception.ErrorCode;
import lk.ashan.demo.model.response.APIErrorResponse;
import lk.ashan.demo.model.response.APISuccessResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class APIResponseBuilder {

    // Success Response
    public static <T> ResponseEntity<APISuccessResponse<T>> success(
            T data,
            Map<String, Object> meta,
            Map<String, String> links
    ) {
        return ResponseEntity.ok(new APISuccessResponse<>(data, meta, links));
    }

    // Error Response
    public static ResponseEntity<APIErrorResponse> error(
            ErrorCode errorCode,
            String detail,
            String instance
    ) {
        APIErrorResponse errorResponse = new APIErrorResponse(
                "https://localhost" + errorCode.getTypeUri(),
                errorCode.getTitle(),
                errorCode.getStatus(),
                errorCode,
                detail,
                instance
        );

        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
