//import java.util.*;
//
//class Car {
//    private String licensePlate;
//    private long entryTime;
//
//    public Car(String licensePlate) {
//        this.licensePlate = licensePlate;
//        this.entryTime = System.currentTimeMillis();
//    }
//
//    public String getLicensePlate() {
//        return licensePlate;
//    }
//
//    public long getEntryTime() {
//        return entryTime;
//    }
//}
//
//class ParkingSpot {
//    private int spotNumber;
//    private Car parkedCar;
//
//    public ParkingSpot(int spotNumber) {
//        this.spotNumber = spotNumber;
//        this.parkedCar = null;
//    }
//
//    public boolean isAvailable() {
//        return parkedCar == null;
//    }
//
//    public void parkCar(Car car) {
//        this.parkedCar = car;
//    }
//
//    public Car removeCar() {
//        Car car = parkedCar;
//        parkedCar = null;
//        return car;
//    }
//
//    public Car getParkedCar() {
//        return parkedCar;
//    }
//
//    public int getSpotNumber() {
//        return spotNumber;
//    }
//}
//
//public class SmartParkingSystem {
//    private static final int TOTAL_SPOTS = 5;
//    private List<ParkingSpot> spots;
//
//    public SmartParkingSystem() {
//        spots = new ArrayList<>();
//        for (int i = 1; i <= TOTAL_SPOTS; i++) {
//            spots.add(new ParkingSpot(i));
//        }
//    }
//
//    public void showAvailableSpots() {
//        System.out.println("Available spots:");
//        for (ParkingSpot spot : spots) {
//            if (spot.isAvailable()) {
//                System.out.println("Spot " + spot.getSpotNumber() + " is free.");
//            }
//        }
//    }
//
//    public void showOccupiedSpots() {
//        System.out.println("Occupied spots:");
//        for (ParkingSpot spot : spots) {
//            if (!spot.isAvailable()) {
//                System.out.println("Spot " + spot.getSpotNumber() + " is occupied by " +
//                        spot.getParkedCar().getLicensePlate());
//            }
//        }
//    }
//
//    public void parkCar(String licensePlate) {
//        for (ParkingSpot spot : spots) {
//            if (spot.isAvailable()) {
//                Car car = new Car(licensePlate);
//                spot.parkCar(car);
//                System.out.println("Car parked at spot " + spot.getSpotNumber());
//                return;
//            }
//        }
//        System.out.println("No available spots.");
//    }
//
//    public void removeCar(String licensePlate) {
//        for (ParkingSpot spot : spots) {
//            Car car = spot.getParkedCar();
//            if (car != null && car.getLicensePlate().equals(licensePlate)) {
//                long duration = (System.currentTimeMillis() - car.getEntryTime()) / 1000;
//                double fee = calculateFee(duration);
//                spot.removeCar();
//                System.out.println("Car removed. Duration: " + duration + " seconds. Fee: $" + fee);
//                return;
//            }
//        }
//        System.out.println("Car not found.");
//    }
//
//    private double calculateFee(long durationInSeconds) {
//        double ratePerSecond = 0.05;
//        return Math.round(durationInSeconds * ratePerSecond * 100.0) / 100.0;
//    }
//
//    public static void main(String[] args) {
//        SmartParkingSystem system = new SmartParkingSystem();
//        Scanner scanner = new Scanner(System.in);
//        int choice = -1;
//
//        while (choice != 0) {
//            System.out.println("\n=== Smart Parking System ===");
//            System.out.println("1. Show Available Spots");
//            System.out.println("2. Show Occupied Spots");
//            System.out.println("3. Park Car");
//            System.out.println("4. Remove Car");
//            System.out.println("0. Exit");
//            System.out.print("Enter choice: ");
//
//            try {
//                choice = Integer.parseInt(scanner.nextLine());
//
//                if (choice == 1) {
//                    system.showAvailableSpots();
//                } else if (choice == 2) {
//                    system.showOccupiedSpots();
//                } else if (choice == 3) {
//                    System.out.print("Enter License Plate: ");
//                    String plate = scanner.nextLine();
//                    system.parkCar(plate);
//                } else if (choice == 4) {
//                    System.out.print("Enter License Plate to Remove: ");
//                    String plate = scanner.nextLine();
//                    system.removeCar(plate);
//                } else if (choice == 0) {
//                    System.out.println("Exiting...");
//                } else {
//                    System.out.println("Invalid choice. Try again.");
//                }
//
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a valid number.");
//            }
//        }
//
//        scanner.close();
//    }
//}