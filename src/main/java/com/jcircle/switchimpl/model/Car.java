package com.jcircle.switchimpl.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Car {
    private String name;
    private String category;
    private Boolean inSales;
}
