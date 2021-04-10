package com.he.console.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * road
 */
@Data
@Slf4j
public class Road {
    public static String             left           = "left";
    public static String             right          = "right";
    public static String             line           = "line";
    //道路唯一标识
    private       String             id;
    //道路限速
    private       int                speedLimit;
    // 道路长度
    private       int                length;
    //开始位置
    private       int                startLocation;
    //结束位置
    private       int                endLocation;
    private       String             direction;
    //道路上的车子
    private       List<Vehicles>     carsOnRoad     = new ArrayList<>();
    //道路上的信号灯
    private       List<TrafficLight> lightsOnRoad       = new ArrayList<>();
    //道路之后连接的道路
    private       List<Road>         nextConnectedRoads = new ArrayList<>();
    ////道路之前连接的道路
    //private       List<Road>         lastConnectedRoads = new ArrayList<>();

    public Road(String id, int speedLimit, int length) {
        this.id = "road" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        //only works for horizontal roads;
        this.endLocation = this.length;
        this.direction = line;

    }

    public void printRoadInfo() {
        log.info("road{}at{}at{}limit of{} m/s,length{}", this.getId(), this.getStartLocation(), this.getEndLocation(), this.getSpeedLimit(), this.getLength());
        //System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n", this.getId(), this.getSpeedLimit(), this.getLength(), this.printStartLocation(), this.printEndLocation());
    }


}
