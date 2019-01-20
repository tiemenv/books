package client.data;

import domain.Book;
import messages.AddBookMessage;
import messages.BooksListMessage;
import messages.GetAllBooksMessage;
import messages.ResultMessage;
import util.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketBookRepository implements BookRepository {

    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    public SocketBookRepository() {
        try {
            Socket socket = new Socket(Config.ADDRESS, Config.PORT);

            objOut = new ObjectOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addBook(Book book) {
        try {
            objOut.writeObject(new AddBookMessage(book));
            ResultMessage res = (ResultMessage) objIn.readObject();
            Logger.getLogger("SocketBookRepository").log(Level.INFO, Boolean.toString(res.isSuccess()));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Book> getBooks() {
        try {
            objOut.writeObject(new GetAllBooksMessage());
            BooksListMessage msg = (BooksListMessage) objIn.readObject();
            return msg.getBooks();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
