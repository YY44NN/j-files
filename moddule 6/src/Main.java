public class Main {
    public static void main(String[] args) {
        ProgramaEstudiantes programa = new ProgramaEstudiantes();


        programa.agregarEstudiante("Ana", 20, 9.5);
        programa.agregarEstudiante("Luis", 22, 8.7);
        programa.agregarEstudiante("Maria", 19, 9.0);


        programa.mostrarEstudiantes();
    }
}
