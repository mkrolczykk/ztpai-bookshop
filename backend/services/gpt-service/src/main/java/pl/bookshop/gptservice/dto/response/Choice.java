package pl.bookshop.gptservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.bookshop.gptservice.dto.request.Message;
import lombok.Data;

import java.io.Serializable;

@Data
public class Choice implements Serializable {
    private Integer index;
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;
}
