package com.he.console.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * vehicles
 */
@Slf4j
@Data
public class Vehicles {
    //起始车速
    //car speed is 0m/s
    private static final int    STOPPED         = 0;
    //下一个路指数
    private static final int    NEXT_ROAD_INDEX = 0;
    //开始位置
    private static final int    START_POSITION  = 1;
    // unique identifier
    //车子唯一标识
    protected            String id;
    // number of segments occupied, 1 for ease of prototype.
    //车子默认占用的长度
    protected            float  length          = 1f;
    //车子默认宽度
    private              float  breadth         = length * 0.5f;
    //segments moved per turn
    //车子速度
    private              int    speed           = 0;
    // position on current road
    //车子当前位置
    private              int    position        = 1;
    // current Road object
    //车子当前道路
    private              Road   currentRoad;


    public Vehicles(String id, Road currentRoad) {
        this.id = id;
        this.currentRoad = currentRoad;
        this.currentRoad.getCarsOnRoad().add(this);
    }


    /**
     * move
     */
    public void move() {
        //如果当前是红灯，车速设置为起始车速
        if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.position == this.currentRoad.getLightsOnRoad().get(0).getPosition() && this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
            this.speed = STOPPED;
        } else {
            //设置车速与道路限速一致
            this.speed = this.currentRoad.getSpeedLimit();
            //如果到达道路尽头，并且有其他支路
            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getNextConnectedRoads().isEmpty()) {
                //从之前的道路上移出车辆
                this.currentRoad.getCarsOnRoad().remove(this);
                //车辆行驶到其他支路，默认第一条路
                this.currentRoad = this.currentRoad.getNextConnectedRoads().get(NEXT_ROAD_INDEX);
                this.currentRoad.getCarsOnRoad().add(this);
                this.position = START_POSITION;
            }
            //如果未到达道路尽头，车子位置等原有车子位置加上车速
            else if (this.currentRoad.getLength() > this.getPosition()) {
                this.position = (this.position + this.speed);
            }
            //其他情况默认车子停止
            else {
                this.speed = STOPPED;
            }
        }
    }

    public void printCarStatus() {
        log.info("vehicles{} is {}m/s{} road {} location", this.getId(), this.getSpeed(), this.getCurrentRoad().getId(), this.getPosition());
        //System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().getId(), this.getPosition());
    }


}

