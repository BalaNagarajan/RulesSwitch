package com.jcircle.switchimpl.processor;

import com.jcircle.switchimpl.model.CarCriteria;
import org.springframework.stereotype.Service;

@Service
public class PremiumCarProcessor implements ICarProcessor {
    @Override
    public String getCarCategoryType() {
        return "Premium";
    }

    @Override
    public CarCriteria getCarCriterias() {
        CarCriteria carCriteria = new CarCriteria();
        carCriteria.setMilegePerLtr("20");
        carCriteria.setEngineCapacity("5000");
        carCriteria.setIsEcoFriendly("N");
        return carCriteria;
    }
}
