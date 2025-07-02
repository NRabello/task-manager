package nrabello.back.inbound.controller.Handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseContent<T> {

    private final T data;

    private final String message;

    private final int status;

    private final Object params;

    private final Object error;

}