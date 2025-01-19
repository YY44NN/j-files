interface Vehiculo{
    void start();
    void stop();
}

class Carro implements Vehiculo {
        String modelo;

    public Carro(String modelo) {
        this.modelo = modelo;
    }


    public void start(){
        System.out.println("el carro " + modelo +" esta encendido");
    }

    public void  stop() {
        System.out.println("el carro " + modelo + " esta apagado");
    }

}


