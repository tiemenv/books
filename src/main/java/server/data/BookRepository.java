package server.data;

import domain.Book;

import java.util.List;

public interface BookRepository {

    void addBook(Book book);
    List<Book> getBooks();

}
