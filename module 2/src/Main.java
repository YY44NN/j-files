//  se imprta el scanner
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//  hice un nuevo scanner para poder dar entrada a los datos del usuario
      Scanner scanner = new Scanner(System.in);
      System.out.println("ingresa tu nombre");o
// hago que se guarde el primer dato que el usuario me dio , en este caso un string por ser su nombre
      String nombre = scanner.nextLine();
//  hice un variable en la que se van a sumar los numeros que el usuario va a iuntroducir en el bucle
      int suma = 0;
// el bucle se va a repetir un total de 3 veces, y en cada iteracion va a preguntar por un numero
        for (int i=0 ; i < 3 ; i++){
            System.out.println("ingresa un numero");
// compara si el numero introducido es un entero y si lo es lo guarda en la variable numero para despues sumarlo a la variable "suma"
            if(scanner.hasNextInt()){
                int numero = scanner.nextInt();
                suma += numero;
// en caso de que el numero que se dio no sea entero arroja un mensaje que indica que elnumero no es entero y finaliza el bucle
            }else{
                System.out.println("el numero que diste no es entero");
                break;
            }
        }
// declaro uan variable para calcular el promedio y la imprimo
        double promedio = (double) suma /3;
        System.out.println("tu nombre es: "+ nombre + " y el prodemio de tus numeros es: "+ promedio);
    }

}
