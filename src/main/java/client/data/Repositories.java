package client.data;

public class Repositories {

    private static BookRepository booksRepo = new SocketBookRepository();

    private Repositories() {
    }

    public static BookRepository getBookRepo() {
        return booksRepo;
    }

}
