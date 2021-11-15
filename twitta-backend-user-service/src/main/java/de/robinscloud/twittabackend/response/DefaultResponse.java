package de.robinscloud.twittabackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;

@Data
@AllArgsConstructor
public class DefaultResponse {

    private int status;
    private HashMap<String, Object> data;

}
