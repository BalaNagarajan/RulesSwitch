package com.jcircle.switchimpl.service.impl;

import com.jcircle.switchimpl.model.CarCriteria;
import com.jcircle.switchimpl.processor.ICarProcessor;
import com.jcircle.switchimpl.request.CarRequest;
import com.jcircle.switchimpl.response.CarResponse;
import com.jcircle.switchimpl.service.ICarService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("carService")
public class CarServiceImpl implements ICarService  {


    private CarResponse carResponse;

    private List<ICarProcessor> carProcessors;
    private static final Map<String, ICarProcessor> processorCache = new HashMap<>();

    @Autowired
    private KieSession kieSession;

    /**
     * During the container initialization , it sets the list of processors implements the ICarProcessor
     * @param carProcessors
     */
    public CarServiceImpl(final List<ICarProcessor>  carProcessors) {
        this.carProcessors = carProcessors;
    }

    /**
     * After the constructor injection it populates the Map with Key / value of the processor.
     */
    @PostConstruct
    public void loadProcessors() {
        System.out.println("----Initializing the Post-----");
        for (ICarProcessor processor : this.carProcessors) {

            processorCache.put(processor.getCarCategoryType(), processor);
        }
    }

    private ICarProcessor getCarProcessor(final String type) {
        ICarProcessor processor = processorCache.get(type);
        if (processor == null) {
            // Throws Exception
        }
        return processor;
    }

    @Override
    public CarResponse getCarInfo(CarRequest carRequest) {

        System.out.println("----Invoking DAO-----");
        CarResponse carResponse = new CarResponse();
        CarCriteria carCriteria = new CarCriteria();
        /**
        ICarProcessor carProcessor = this.getCarProcessor(carRequest.getCategoryType());
        CarCriteria carCriteria = carProcessor.getCarCriterias();
        carResponse.setCarCriteria(carCriteria);
         */
        kieSession.insert(carRequest);
        kieSession.setGlobal("carResponse",carResponse);
        kieSession.setGlobal("carCriteria",carCriteria);
        kieSession.fireAllRules();

        return  carResponse;


    }
}
