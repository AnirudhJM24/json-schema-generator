package com.jsonschemagen.generator.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import java.util.List;

@Data
public class RequestBodyDTO {
    @JsonProperty("jsonFile")
    private ObjectNode json; //json for which schema has to be created

}
