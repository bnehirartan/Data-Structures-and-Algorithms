//-----------------------------------------------------
// Title: Distribution.java class
// Author: Basak Nehir Artan
// ID: 26051100858
// Section: 2
// Assignment: 1
// Description: This class represents distribution centers where vehicles can pick up and drop off cargo. It contains cargo stacks and vehicle queues
//-----------------------------------------------------


public class DistributionCenter {
    private String city;
    private Stack<CargoPackage> packageStack= new Stack<>();
    private Queue<Vehicle> vehicleQueue= new Queue<>();
    // Constructor, methods for managing packages and vehicles

    public DistributionCenter(String city){
        this.city=city;
    }

    public void addPackage(CargoPackage cargo){
        packageStack.push(cargo);
    }

    public void removePackage(CargoPackage cargo){
        packageStack.pop(cargo);
    }

    public void addVehicle(Vehicle vehicle){
        vehicleQueue.enqueue(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicleQueue.dequeue(vehicle);
    }

    public Stack<CargoPackage> getPackageStack(){
        return packageStack;
    }

    public Queue<Vehicle> getVehicleQueue(){
        return vehicleQueue;
    }
    
}