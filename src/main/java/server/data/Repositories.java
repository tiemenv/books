package server.data;

public class Repositories {

    private static BookRepository booksRepo = new InMemoryBookRepository();

    private Repositories() {
    }

    public static BookRepository getBookRepo() {
        return booksRepo;
    }

}
