package server;

import server.data.Repositories;
import domain.Book;
import messages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {

    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    public Connection(Socket socket) {
        try {
            objOut = new ObjectOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                Message msgIn = (Message) objIn.readObject();
                Message msgOut = null;

                if (msgIn instanceof AddBookMessage) {
                    msgOut = this.addBook((AddBookMessage) msgIn);
                } else if (msgIn instanceof GetAllBooksMessage) {
                    msgOut = new BooksListMessage(Repositories.getBookRepo().getBooks());
                } else {
                    msgOut = new ResultMessage(false);
                }

                objOut.reset(); // https://docs.oracle.com/javase/7/docs/api/java/io/ObjectOutputStream.html#reset()
                                // Used to counter the 'bug' in class where a second call gave the same result as the first call

                objOut.writeObject(msgOut);
            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private ResultMessage addBook(AddBookMessage msg) {
        Book book = msg.getBook();

        Repositories.getBookRepo().addBook(book);

        return new ResultMessage(true);
    }
}
