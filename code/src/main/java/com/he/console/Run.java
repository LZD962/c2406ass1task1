package com.he.console;

import cn.hutool.core.util.RandomUtil;
import com.he.console.base.Vehicles;
import com.he.console.base.vehicles.Bus;
import com.he.console.base.vehicles.Car;
import com.he.console.base.Road;
import com.he.console.base.TrafficLight;
import com.he.console.base.vehicles.Motorbike;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Slf4j
public class Run {

    public static void main(String[] args) {
        //道路数量
        int roadNum = 6;
        //车子数量
        int carNum = 3;
        //信号灯数量
        int lightNum = roadNum;
        //道路最小长度
        int roadLengthMini = 10;
        //道路最大长度
        int roadLengthMax = 31;
        //打印监控信息时间间隔
        int sleepMillis = 1000;


        log.info("{}", "At first");
        List<Road> roads = new ArrayList<>();
        for (int i = 0; i < roadNum; i++) {
            int roadLimit = get3RandomInt();
            int roadLength = RandomUtil.randomInt(roadLengthMini, roadLengthMax);
            Road road = new Road(Integer.toString(i), roadLimit, roadLength);
            //打印道路信息
            road.printRoadInfo();
            roads.add(road);
        }
        log.info("{}", "connect road");
        Road road = roads.get(0);
        List<Road> subList = roads.subList(1, roads.size());
        Iterator<Road> subItr = subList.iterator();
        while (subItr.hasNext()) {
            Road next = subItr.next();
            int result = 0;
            do {
                result = connectRoad(road, next);
            } while (result == 0);

            List<Road> nextConnectedRoads = road.getNextConnectedRoads();
            for (Road nextConnectedRoad : nextConnectedRoads) {
                if (nextConnectedRoad.getDirection().equals(Road.line)) {
                    road = nextConnectedRoad;
                    break;
                }
            }
        }


        log.info("{}", "TrafficLight at first");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightNum; i++) {
            TrafficLight light = new TrafficLight(Integer.toString(i), roads.get(i));
            //打印信号灯信息
            light.printLightStatus();
            lights.add(light);
        }
        log.info("{}", "vehicles at first");
        ArrayList<Vehicles> vehiclesList = new ArrayList<>();
        for (int i = 0; i < carNum; i++) {
            int roadRandomInt = RandomUtil.randomInt(1, roadNum);
            Road tempRoad = roads.get(roadRandomInt);
            List<Vehicles> carsOnRoad = tempRoad.getCarsOnRoad();
            if (!carsOnRoad.isEmpty()) {
                for (Vehicles vehicles : carsOnRoad) {
                    vehicles.move();
                }
            }
            int randomInt = get3RandomInt();
            Vehicles vehicles;
            if (randomInt == 1) {
                vehicles = new Car(Integer.toString(i), tempRoad);
            } else if (randomInt == 2) {
                vehicles = new Bus(Integer.toString(i), tempRoad);
            } else {
                vehicles = new Motorbike(Integer.toString(i), tempRoad);
            }
            vehicles.printCarStatus();
            vehiclesList.add(vehicles);
        }

        log.info("{}", "monitor");
        int carsFinished = 0;
        //有车子行驶在道路上就一直执行
        while (carsFinished < vehiclesList.size()) {
            for (TrafficLight light : lights) {
                light.operate(new Random().nextInt());
                light.printLightStatus();
            }
            for (Vehicles vehicles : vehiclesList) {
                vehicles.move();
                vehicles.printCarStatus();
                //车子行驶到道路尽头并且没有支路并且车速为0，则视为改车已完成，不再打印改车信息
                if (vehicles.getCurrentRoad().getNextConnectedRoads().isEmpty() && (vehicles.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }


    }

    private static int get3RandomInt() {
        return RandomUtil.randomInt(1, 4);
    }

    private static int connectRoad(Road currentRoad, Road nextRoad) {
        String currentDirection;
        int randomInt = get3RandomInt();
        if (randomInt == 1) {
            currentDirection = Road.left;
        } else if (randomInt == 2) {
            currentDirection = Road.right;
        } else {
            currentDirection = nextRoad.getDirection();
        }
        //List<Road> lastConnectedRoads = currentRoad.getLastConnectedRoads();
        List<Road> nextConnectedRoads = currentRoad.getNextConnectedRoads();
        if (nextConnectedRoads.size() >= 3) {
            return 3;
        }
        for (Road connectedRoad : nextConnectedRoads) {
            if (!currentDirection.equals(Road.line) && connectedRoad.getDirection().equals(currentDirection)) {
                return 0;
            }
        }
        nextRoad.setDirection(currentDirection);
        //nextRoad.setStartLocation(lastConnectedRoads.get);
        //nextRoad.setEndLocation();
        nextConnectedRoads.add(nextRoad);
        return 1;
    }
}
