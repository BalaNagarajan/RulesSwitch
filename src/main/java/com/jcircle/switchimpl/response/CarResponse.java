package com.jcircle.switchimpl.response;

import com.jcircle.switchimpl.model.Car;
import com.jcircle.switchimpl.model.CarCriteria;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class CarResponse extends BaseResponse {

    private CarCriteria carCriteria;

}
