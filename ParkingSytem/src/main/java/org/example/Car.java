package org.example;

import java.util.ArrayList;
import java.util.List;

class Car {
    private String licenseplate;
    // make constructor so that whenever car object is created it gives license no

    public Car(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getLicenseplate() {
        return licenseplate;
    }
}

class ParkingSpot{
    private int spotNumber;
    private boolean available;

    private Car car;

    public ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.available = true;
        this.car = null;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public Car getCar() {
        return car;
    }

    public void occupy(Car car){
        this.car=car;
        this.available=false;
    }

    public void vacat(){
        this.car=null;
        this.available=true;
    }
}

class ParkingLot{
    private List<ParkingSpot> spots;

    public ParkingLot(int capacity) {
        this.spots = new ArrayList<>();
        for (int i = 0;i<capacity;i++){
            spots.add(new ParkingSpot(i));
        }
    }
    public boolean parkCar(Car car){
        for (ParkingSpot spot:spots){
            if(spot.isAvailable()){
                spot.occupy(car);
                System.out.println("Car with Number : "+car.getLicenseplate() + "  Parked at spot number :"+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("Sorry parking is full");
        return false;
    }

    public boolean removeCar(String licensePlate){
        for (ParkingSpot spot:spots){
            if (!spot.isAvailable() && spot.getCar().getLicenseplate().equalsIgnoreCase(licensePlate)){
                spot.vacat();
                System.out.println("Car with number :"+licensePlate+"  Removed from parking and spot number : "+spot.getSpotNumber());
                return true;
            }
        }
        System.out.println("Car with number :"+licensePlate+" Not found ");
        return false;
    }
}


class Test{
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car1 = new Car("UP1234");
        Car car2 = new Car("DL5678");
        Car car3 = new Car("MP91011");
        Car car4 = new Car("MP9sfd011");
        Car car5 = new Car("MP910sdf1");
        Car car6 = new Car("MP91011sfd");
        Car car7 = new Car("MP9101ert1");
        Car car8 = new Car("MP910ertre11");

        parkingLot.parkCar((car1));
        parkingLot.parkCar((car2));
        parkingLot.parkCar((car3));
        parkingLot.parkCar((car4));
        parkingLot.parkCar((car5));
//        parkingLot.parkCar((car6));


        parkingLot.removeCar("UP1234");
    }
}