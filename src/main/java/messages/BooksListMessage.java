package messages;

import domain.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BooksListMessage extends Message {

    private List<Book> books;

    public BooksListMessage(List<Book> books) {
        this.books = books; //books.toArray(new Book[0]); // Arrays.asList( books );
    }

    public List<Book> getBooks() {
        // return Arrays.asList(books);
        return Collections.unmodifiableList(books); //Collections.unmodifiableList(books);
    }
}
