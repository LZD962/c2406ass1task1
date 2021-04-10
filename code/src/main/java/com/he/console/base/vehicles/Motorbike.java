package com.he.console.base.vehicles;

import com.he.console.base.Road;
import com.he.console.base.Vehicles;

/**
 * motorbike
 */
public class Motorbike extends Vehicles {

    public Motorbike(String id, Road currentRoad) {
        super("motorbike"+id,currentRoad);
        this.length = super.getLength() * 0.5f;
    }

}
