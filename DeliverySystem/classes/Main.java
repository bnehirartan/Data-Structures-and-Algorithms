//-----------------------------------------------------
// Title: Main.java class
// Author: Basak Nehir Artan
// ID: 26051100858
// Section: 2
// Assignment: 1
// Description: This class is the main class that runs the program, initializes data, and executes the missions. It reads input files, instantiate objects, and call methods to perform the operations and write the output.
//-----------------------------------------------------


public class Main {
    
    public static void main(String[] args) throws IOException {
        List<DistributionCenter> cities = loadCities("cities.txt");
        loadVehicles("vehicles.txt", cities);
        loadPackages("packages.txt", cities);
        List<Mission> missions = loadMissions("missions.txt");

        for (Mission mission : missions) {
            mission.execute(cities);
        }


        writeResult(cities, "result.txt");
    }


    //TESTER
    // Create some cargo packages
    CargoPackage package1 = new CargoPackage("P001", "CityA", 10.0);
    CargoPackage package2 = new CargoPackage("P002", "CityB", 20.0);
    CargoPackage package3 = new CargoPackage("P003", "CityC", 15.0);

    // Create a vehicle
    Vehicle vhc = new Vehicle("vhc1", "CityA", 50.0);

    // Load cargo onto the vehicle
    System.out.println("Loading package 1: " + vhc.loadCargo(package1));  // true
    System.out.println("Loading package 2: " + vhc.loadCargo(package2));  // true
    System.out.println("Loading package 3: " + vhc.loadCargo(package3));  // false, exceeds capacity

    // Unload one cargo package
    vhc.unloadCargo();
    System.out.println("Current load after unloading: " + vhc.getCurrentLoad());

    // Try to load another package
    CargoPackage package4 = new CargoPackage("P004", "CityA", 15.0);
    System.out.println("Loading package 4: " + vhc.loadCargo(package4));  // true

}