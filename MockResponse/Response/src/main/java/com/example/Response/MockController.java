package com.example.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
@CrossOrigin(origins = { "http://localhost:4200", "http:localhost:4201" })
@RestController()public class MockController {


    HashMap<String, Object> responseObject;

    String result="this is the response form command repo service in the string format";

//response in string

    @GetMapping("/response")
    public ResponseEntity<HashMap<String, Object>> getCommands() {
        responseObject = new HashMap<>();
        responseObject.put("result", result);
        responseObject.put("message", "Success!");
        responseObject.put("error", "false");
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }


}
