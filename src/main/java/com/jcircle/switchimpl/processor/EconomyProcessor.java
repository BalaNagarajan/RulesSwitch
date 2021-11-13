package com.jcircle.switchimpl.processor;

import com.jcircle.switchimpl.model.CarCriteria;
import org.springframework.stereotype.Service;

@Service
public class EconomyProcessor implements ICarProcessor {
    @Override
    public String getCarCategoryType() {
        return "Economy";
    }

    @Override
    public CarCriteria getCarCriterias() {
        CarCriteria carCriteria = new CarCriteria();
        carCriteria.setMilegePerLtr("60");
        carCriteria.setEngineCapacity("500");
        carCriteria.setIsEcoFriendly("Y");
        return carCriteria;
    }
}
