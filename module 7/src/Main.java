import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Solicitar el primer número
            System.out.print("Ingrese el primer número: ");
            double numero1 = scanner.nextDouble();

            // Solicitar el segundo número
            System.out.print("Ingrese el segundo número: ");
            double numero2 = scanner.nextDouble();

            // Verificar si el divisor es cero
            if (numero2 == 0) {
                System.out.println("Error: No se puede dividir entre cero.");
            } else {
                // Realizar la división
                double resultado = numero1 / numero2;
                System.out.println("El resultado de la división es: " + resultado);
            }
        } catch (Exception e) {
            // Manejar cualquier otra excepción
            System.out.println("Error: Entrada inválida. Por favor, ingrese números válidos.");
        } finally {
            // Bloque que siempre se ejecuta
            System.out.println("Gracias por usar el programa de división.");
            scanner.close();
        }
    }
}
