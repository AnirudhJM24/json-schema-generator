package com.jsonschemagen.generator.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jsonschemagen.generator.DTO.RequestBodyDTO;
import com.jsonschemagen.generator.DTO.ResponseReturnDTO;

public interface IJSONSchemaService {

    public ResponseReturnDTO createSchema(RequestBodyDTO requestBodyDTO);

}
