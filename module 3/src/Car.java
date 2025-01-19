 class Car {
    private String brand;
    private String model;
    private int year;

    Car(String brand, String model, int year){
        this.brand = brand;
        this.model= model;
        this.year=year;
    }


    public void startCar(){
        System.out.println("el auto esta encendido");
    }
     public void stopCar(){
         System.out.println("el auto esta apagado");
     }
}

