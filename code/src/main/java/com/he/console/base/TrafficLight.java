package com.he.console.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * TrafficLight
 */
@Data
@Slf4j
public class TrafficLight {
    // 50/50 chance of changing state.
    //
    private static final double CHANGE_GREEN = 0.5;
    //绿灯
    private static final String GREEN        = "green";
    //红灯
    private static final String RED          = "red";
    //信号灯唯一标识
    private              String id;
    //信号灯状态
    private              String state;
    //信号灯位置
    private              int    position;
    //连接道路
    private              Road   roadAttachedTo;

    public TrafficLight(String id, Road roadAttachedTo) {
        this.id = "trafficLight" + id;
        this.state = RED;
        this.roadAttachedTo = roadAttachedTo;
        // always places the traffic light at the end of the roadAttachedTo.
        //道路尽头有信号灯
        this.position = this.roadAttachedTo.getLength();
        // adds this light to the road it belongs to.
        //将信号灯加入道路上
        this.roadAttachedTo.getLightsOnRoad().add(this);
    }

    /**
     *  根据随机数切换信号灯的状态
     */
    public void operate(int randomNum) {
        Random random = new Random(randomNum);
        double probability = random.nextDouble();
        if (probability > CHANGE_GREEN) {
            this.setState(GREEN);
        } else {
            this.setState(RED);
        }
    }

    public void printLightStatus() {
        log.info("at{}road，location{} trafficLight{} is {}", this.getRoadAttachedTo().getId(), this.getPosition(), this.getId(), this.getState());
        //System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getState(), this.getRoadAttachedTo().getId(), this.getPosition());
    }

    //public String getState() {
    //    return state;
    //}
    //
    //public void setState(String state) {
    //    this.state = state;
    //}
    //
    //public Road getRoadAttachedTo() {
    //    return roadAttachedTo;
    //}
    //
    //public void setRoadAttachedTo(Road roadAttachedTo) {
    //    this.roadAttachedTo = roadAttachedTo;
    //}
    //
    //public int getPosition() {
    //    return position;
    //}
    //
    //public String getId() {
    //    return id;
    //}
    //
    //public void setId(String id) {
    //    this.id = id;
    //}
    //
    //public void setPosition(int position) {
    //    this.position = position;
    //}
}
