package messages;

import domain.Book;

public class AddBookMessage extends Message {

    private Book book;

    public AddBookMessage(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
