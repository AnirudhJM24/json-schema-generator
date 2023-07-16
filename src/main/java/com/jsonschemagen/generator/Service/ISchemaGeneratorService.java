package com.jsonschemagen.generator.Service;

import com.jsonschemagen.generator.DTO.RequestBodyDTO;
import com.jsonschemagen.generator.DTO.ResponseReturnDTO;

public interface ISchemaGeneratorService {

    public ResponseReturnDTO createSchema(RequestBodyDTO requestBodyDTO);
}
