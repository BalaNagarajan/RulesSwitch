package com.jcircle.switchimpl.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jcircle.switchimpl.request.CarRequest;
import com.jcircle.switchimpl.response.BaseResponse;
import com.jcircle.switchimpl.response.CarResponse;
import com.jcircle.switchimpl.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SelectCriteriaCntrl {

    @Autowired
    ICarService carService;

    @CrossOrigin
    @PostMapping(value = "/v1/carinfo")
    public ResponseEntity<BaseResponse> getCarInfo(@RequestBody CarRequest carRequest) {
        ResponseEntity<BaseResponse> responseEntity = null;
        System.out.println("Inside Controller"+carRequest.getCategoryType());
        CarResponse carResponse = carService.getCarInfo(carRequest);
        responseEntity = new ResponseEntity<BaseResponse>(carResponse, HttpStatus.OK);
        return responseEntity;

    }


}
