
import java.util.ArrayList;


class Estudiante {
    private String name;
    private int age;
    private double grade;

    public Estudiante(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + ", Edad: " + age + ", Nota: " + grade;
    }
}


public class ProgramaEstudiantes {
    private ArrayList<Estudiante> listaEstudiantes;

    public ProgramaEstudiantes() {
        listaEstudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(String name, int age, double grade) {
        Estudiante estudiante = new Estudiante(name, age, grade);
        listaEstudiantes.add(estudiante);
        System.out.println("Estudiante agregado: " + estudiante.getName());
    }

    public void mostrarEstudiantes() {
        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes en la lista.");
        } else {
            System.out.println("Lista de estudiantes:");
            for (Estudiante estudiante : listaEstudiantes) {
                System.out.println(estudiante);
            }
        }
    }
    }