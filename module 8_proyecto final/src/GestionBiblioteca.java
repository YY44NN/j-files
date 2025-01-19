import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Libro {
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return "Libro: " + titulo + ", Autor: " + autor + ", Disponible: " + (disponible ? "Sí" : "No");
    }
}

class Usuario {
    private String nombre;
    private int id;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void prestarLibro(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public String toString() {
        return "Usuario: " + nombre + ", ID: " + id + ", Libros Prestados: " + librosPrestados.size();
    }
}

class Biblioteca {
    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void prestarLibro(String titulo, Usuario usuario) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.estaDisponible()) {
                libro.setDisponible(false);
                usuario.prestarLibro(libro);
                System.out.println("Se prestó el libro: " + titulo);
                return;
            }
        }
        System.out.println("El libro no está disponible o no existe.");
    }

    public void devolverLibro(String titulo, Usuario usuario) {
        for (Libro libro : usuario.getLibrosPrestados()) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.setDisponible(true);
                usuario.devolverLibro(libro);
                System.out.println("Se devolvió el libro: " + titulo);
                return;
            }
        }
        System.out.println("El usuario no tiene ese libro prestado.");
    }

    public void listarLibros() {
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}

public class GestionBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario = new Usuario("Juan", 1);

        biblioteca.agregarLibro(new Libro("El Principito", "Antoine de Saint-Exupéry"));
        biblioteca.agregarLibro(new Libro("Cien Años de Soledad", "Gabriel García Márquez"));
        biblioteca.agregarLibro(new Libro("Don Quijote", "Miguel de Cervantes"));

        Scanner scanner = new Scanner(System.in);
        boolean corriendo = true;

        while (corriendo) {
            System.out.println("\nSistema de Gestión de Biblioteca:");
            System.out.println("1. Listar Libros");
            System.out.println("2. Prestar Libro");
            System.out.println("3. Devolver Libro");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    biblioteca.listarLibros();
                    break;
                case 2:
                    System.out.print("Título del libro a prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    biblioteca.prestarLibro(tituloPrestar, usuario);
                    break;
                case 3:
                    System.out.print("Título del libro a devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    biblioteca.devolverLibro(tituloDevolver, usuario);
                    break;
                case 4:
                    corriendo = false;
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }

        scanner.close();
        System.out.println("¡Adiós!");
    }
}
