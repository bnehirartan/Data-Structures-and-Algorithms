 //-----------------------------------------------------
// Title: Vehicle.java class

// Description: This class implements the properties and methods of a vehicle, including loading, unloading cargo, and managing the current load
//-----------------------------------------------------



public class Vehicle {
    // Constructor, methods for loading/unloading packages
    private String id,city; 
    private double capacity, currentLoad;
    private Queue<CargoPackage> cargoList = new Queue<>();

    public Vehicle(String id, String city, double capacity){ //constructor
        this.id=id;
        this.city=city; //initialize vehicle's destination city
        this.capacity=capacity;
        this.currentLoad=0;
        this.cargoList= new Queue<>(); //initializing queue
    }

    

    public Queue<CargoPackage> getCargoList(){
        return cargoList;
    }

    public String getId(){
        return id;
    }
    public String getCity(){
        return city;
    }

    public double getCapacitiy(){
        return capacity;
    }

    public double getCurrentLoad(){
        return currentLoad;
    }

    public boolean LoadCargo(CargoPackage cargo){ //adds a package to the vehicle if there is enough capacity 
        if(this.currentLoad + cargo.getWeight()<this.capacity){
            this.cargoList.enqueue(cargo);
            this.currentLoad+=cargo.getWeight();
            return true;
        }
        return false;// the vehicle is full, we can not add more cargo  

    }

    public void unloadCargo(){ //removes the cargos in list following FIFO
        if(!this.cargoList.isEmpty()){
            CargoPackage firstCargo= this.cargoList.poll(); //FIFO
            this.currentLoad-=firstCargo.getWeight();

        }
    }

    public CargoPackage removePackageAtIndex(int index) { //removing cargos at specific indices
        if (index >= 0 && index < cargoList.size()) {
            return cargoList.remove(index); 
        }
        return null; 
    }

}



   
    
