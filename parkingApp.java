import java.util.*;

class Scratch {
    public static void main(String[] args) {
        var carPark = new CarPark();

        System.out.println(carPark.addCar("XXXX XXX"));
        System.out.println(carPark.addCar("XX12 XX5"));

        for (int i = 0; i < 100; i++) {
            System.out.println(carPark.addCar("XXXX XXX" + i));
        }

        String regToRemove = "XXXX XXX96";
        int amount = carPark.removeCar(regToRemove);

        System.out.printf("%nCar with reg [%s] has left and is due to pay £%s%n", regToRemove, amount);


        System.out.println(carPark.addCar("XXXX XXZ"));
    }
}


class CarPark {
    final int CAR_PARK_SIZE = 100;
    final int HOURLY_RATE = 2;

    // Depending on the requirements it may be better to use a ArrayList here. I think a HashMap is better in this
    // circumstance because it makes removal more straightforward
    private final Map<String, Car> carMap = new HashMap<>();

    String addCar(String regNumber) {
        if (carMap.size() < CAR_PARK_SIZE) {
            Car carToAdd = new Car(regNumber);
            carMap.put(regNumber, carToAdd);
            return String.format("Added car with Registration [%s]", regNumber);
        } else {
            return "Sorry, the car park is full...";
        }
    }

    int removeCar(String carRegToRemove) {
        // Making use of a Map makes this comparison a lot easier, if we used a more complex comparison would be required
        if (carMap.containsKey(carRegToRemove)) {
            int currentTime = 15; // This would be current timestamp in the real world
            int timeParked = currentTime - carMap.get(carRegToRemove).getArrivalTime();
            carMap.remove(carRegToRemove);
            return timeParked * HOURLY_RATE;
        } else {
            throw new RuntimeException(String.format("CarPark does not contain car with reg:[%s]", carRegToRemove));
        }
    }
}

class Car {
    private String regNumber;
    private int arrivalTime = 12; // This would be current timestamp set in the constructor in the real world

    public Car(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getRegNumber() {
        return regNumber;
    }

}
