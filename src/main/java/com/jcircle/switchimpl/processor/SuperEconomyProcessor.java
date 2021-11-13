package com.jcircle.switchimpl.processor;

import com.jcircle.switchimpl.model.CarCriteria;
import org.springframework.stereotype.Service;

@Service
public class SuperEconomyProcessor implements  ICarProcessor {
    @Override
    public String getCarCategoryType() {
        return "SuperEconomy";
    }

    @Override
    public CarCriteria getCarCriterias() {
        CarCriteria carCriteria = new CarCriteria();
        carCriteria.setMilegePerLtr("80");
        carCriteria.setEngineCapacity("1000");
        carCriteria.setIsEcoFriendly("Y");
        return carCriteria;
    }
}
