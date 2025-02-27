//-----------------------------------------------------
// Title: CargoPackage.java class
// 
// Description: This class represents individual cargo packages with information such as ID, city, and weight
//-----------------------------------------------------


public class CargoPackage {
    private String id;
     // Constructor, getters, and setters
    private String city;
    private double weight;

    private CargoPackage next;
    private CargoPackage prev;

    public CargoPackage(String id, String city, double weight){  //constructor
        this.id=id;
        this.city=city;
        this.weight=weight;
    }

    public String getId(){
        return id;}

        public String getCity(){
            return city;
        } 

        public double getWeight(){
            return weight;
        }

        public CargoPackage getNext(){
            return next;
        }

        public void setNext(CargoPackage next){
            this.next=next;
        }

        public CargoPackage getPrev(){
            return prev;
        }

        public void setPrev(CargoPackage prev){
            this.prev=prev;
        }
    }
   

