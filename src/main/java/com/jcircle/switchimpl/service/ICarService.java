package com.jcircle.switchimpl.service;

import com.jcircle.switchimpl.request.CarRequest;
import com.jcircle.switchimpl.response.CarResponse;
import org.springframework.stereotype.Service;


public interface ICarService {

    CarResponse getCarInfo(CarRequest carRequest);

}
