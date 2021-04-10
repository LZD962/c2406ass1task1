package com.he.console.base.vehicles;

import com.he.console.base.Road;
import com.he.console.base.Vehicles;
import lombok.extern.slf4j.Slf4j;

/**
 * bus
 */
@Slf4j
public class Bus extends Vehicles {

    public Bus(String id, Road currentRoad) {
        super("bus"+id,currentRoad);
        this.length = super.getLength() * 3;
    }

}
