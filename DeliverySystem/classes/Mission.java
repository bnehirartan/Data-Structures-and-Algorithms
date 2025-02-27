//-----------------------------------------------------
// Title: Mission.java class
// 
// Description: This class simulates the task of moving cargo between distribution centers.
//-----------------------------------------------------


public class Mission {
    private String sourceCity;
    private String middleCity;
    private String destinationCity;
    private int loadFromSource;
    private int loadFromMiddle;
    private List<Integer> dropOffIndices;

    public Mission(String source, String middle, String dest, int loadSrc, int loadMid, List<Integer> drops) { //constructor
        this.sourceCity = source;  
        this.middleCity = middle;
        this.destinationCity = dest;
        this.loadFromSource = loadSrc;
        this.loadFromMiddle = loadMid;
        this.dropOffIndices = drops;
    }

    public void execute() {
        DistributionCenter source = getCityByName(sourceCity);
        DistributionCenter middle = getCityByName(middleCity);
        DistributionCenter destination = getCityByName(destinationCity);

        Vehicle vehicle = source.getVehicleQueue().dequeue(); //get the first available vehicle

        // load packages from source city
        for (int i = 0; i < loadFromSource; i++) {
            vehicle.loadPackage(source.getPackageStack().pop()); //from source stack
        }

        // load packages from middle city and drop specified packages
        for (int i = 0; i < loadFromMiddle; i++) {
            vehicle.loadPackage(middle.getPackageStack().pop()); //from middle stack
        }
        // drop specific packages at middle city (implement logic to drop packages based on dropOffIndices)
        for (int index : dropOffIndices) {
            CargoPackage droppedPackage = vehicle.removePackageAtIndex(index);  //remove package from vehicle queue(defined in Vehicle clas)
            middle.getPackageStack().push(droppedPackage); //add it to middle city stack 
        }   

        // move to destination city
        destination.getVehicleQueue().enqueue(vehicle);
    }

    private DistributionCenter getCityByName(String cityName) {
        for(DistributionCenter city: cities){
            if(city.getCityName(). equals(cityName)){ //comparison between city name stored in DistributionCenter (city) and cityName passed to getCityByName() 
                return city;
            }
        }
        return null; //city not found
    }
}
