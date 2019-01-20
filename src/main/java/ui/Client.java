package ui;

import client.data.Repositories;
import domain.Book;

public class Client implements Runnable {

    @Override
    public void run() {
        Book b = new Book("123-456789-01", "Everybody loves multithreading");
        Book c = new Book("234-577912-02", "Java for Dummies");

        Repositories.getBookRepo().addBook(b);
        Repositories.getBookRepo().addBook(c);

        printBooks();

        Book d = new Book("234-577912-03", "Java for Experts");
        Repositories.getBookRepo().addBook(d);

        printBooks();
    }

    private void printBooks() {
        for (Book b : Repositories.getBookRepo().getBooks()) {
            System.out.println(b);
        }
    }
}
