package server.data;

import domain.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryBookRepository implements BookRepository {
    private List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println(books.size());
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }
}
