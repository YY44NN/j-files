import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "título='" + title + '\'' +
                ", autor='" + author + '\'' +
                ", disponible=" + (available ? "Sí" : "No") +
                '}';
    }
}

class User {
    private String name;
    private int id;
    private List<Book> borrowedBooks;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + name + '\'' +
                ", id=" + id +
                ", libros prestados=" + borrowedBooks +
                '}';
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void lendBook(String title, User user) throws Exception {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                user.borrowBook(book);
                System.out.println("Se prestó el libro: " + book.getTitle());
                return;
            }
        }
        throw new Exception("El libro no está disponible o no se encontró.");
    }

    public void returnBook(String title, User user) throws Exception {
        for (Book book : user.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setAvailable(true);
                user.returnBook(book);
                System.out.println("Se devolvió el libro: " + book.getTitle());
                return;
            }
        }
        throw new Exception("El usuario no tiene prestado ese libro.");
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("Alicia", 1);

        library.addBook(new Book("El Guardián Entre el Centeno", "J.D. Salinger"));
        library.addBook(new Book("Matar a un Ruiseñor", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nSistema de Gestión de Biblioteca:");
            System.out.println("1. Listar Libros");
            System.out.println("2. Prestar Libro");
            System.out.println("3. Devolver Libro");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume la nueva línea

            try {
                switch (choice) {
                    case 1:
                        library.listBooks();
                        break;
                    case 2:
                        System.out.print("Introduce el título del libro a prestar: ");
                        String lendTitle = scanner.nextLine();
                        library.lendBook(lendTitle, user);
                        break;
                    case 3:
                        System.out.print("Introduce el título del libro a devolver: ");
                        String returnTitle = scanner.nextLine();
                        library.returnBook(returnTitle, user);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("¡Adiós!");
    }
}
