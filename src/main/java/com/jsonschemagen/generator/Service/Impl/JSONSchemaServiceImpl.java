package com.jsonschemagen.generator.Service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jsonschemagen.generator.DTO.RequestBodyDTO;
import com.jsonschemagen.generator.DTO.ResponseReturnDTO;
import com.jsonschemagen.generator.Service.IJSONSchemaService;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class JSONSchemaServiceImpl implements IJSONSchemaService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseReturnDTO createSchema(RequestBodyDTO requestBodyDTO) {
        ObjectNode jsonData = requestBodyDTO.getJson();
        ObjectNode schema = (ObjectNode) generateJsonSchema(jsonData);
        ResponseReturnDTO responseReturnDTO = new ResponseReturnDTO();
        responseReturnDTO.setSchema(schema);
        return responseReturnDTO;
    }


    public JsonNode generateJsonSchema(JsonNode data) {
        if (data.isObject()) {
            ObjectNode schema = objectMapper.createObjectNode();
            schema.put("type", "object");

            ObjectNode properties = objectMapper.createObjectNode();
            ObjectNode objectData = (ObjectNode) data;
            final ArrayNode required = data.withArray("required");

            objectData.fields().forEachRemaining(entry -> {

                if(!Objects.equals(entry.getKey(), "required")){

                    properties.set(entry.getKey(),generateJsonSchema(entry.getValue()));
                }

            });

            schema.set("properties", properties);
            if(!required.isEmpty())
                schema.set("required",required);
            return schema;
        } else if (data.isArray()) {
            ArrayNode schemaArray = objectMapper.createArrayNode();
            ArrayNode arrayData = (ArrayNode) data;

            if (!arrayData.isEmpty()) {
                JsonNode arrayItem = arrayData.get(0);
                schemaArray.add(generateJsonSchema(arrayItem));
            }

            ObjectNode schema = objectMapper.createObjectNode();
            schema.put("type", "array");
            schema.set("items", schemaArray);
            return schema;
        } else {
            return determinePrimitiveType(data);
        }
    }

    private JsonNode determinePrimitiveType(JsonNode value) {
        if (value.isBoolean()) {
            return objectMapper.createObjectNode().put("type", "boolean");
        } else if (value.isInt() || value.isLong()) {
            return objectMapper.createObjectNode().put("type", "integer");
        } else if (value.isDouble() || value.isFloat()) {
            return objectMapper.createObjectNode().put("type", "number");
        } else if (value.isTextual()) {
            return objectMapper.createObjectNode().put("type", "string");
        } else {
            return objectMapper.createObjectNode().put("type", "null");
        }
    }
}
