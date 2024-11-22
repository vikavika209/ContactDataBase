package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateContactDto {

    @JsonProperty("param")
    private String param;

    public UpdateContactDto() {
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
