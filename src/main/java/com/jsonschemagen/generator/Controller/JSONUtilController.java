package com.jsonschemagen.generator.Controller;

import com.jsonschemagen.generator.DTO.RequestBodyDTO;
import com.jsonschemagen.generator.DTO.ResponseReturnDTO;
import com.jsonschemagen.generator.Service.IJSONSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class JSONUtilController {

    @Autowired
    private IJSONSchemaService IJSONSchemaService;

    @CrossOrigin(origins = "*")
    @PostMapping("/json-schema")
    public ResponseEntity<ResponseReturnDTO> generateSchema(@RequestBody RequestBodyDTO requestBodyDTO){
        return ResponseEntity.ok(IJSONSchemaService.createSchema(requestBodyDTO));
    }

}
