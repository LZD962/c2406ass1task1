package com.he.console.base.vehicles;

import com.he.console.base.Road;
import com.he.console.base.Vehicles;
import lombok.extern.slf4j.Slf4j;

/**
 * car
 */
@Slf4j
public class Car extends Vehicles {


    public Car(String id, Road currentRoad) {
        super("car"+id,currentRoad);
    }

}

