package ui;
import client.data.Repositories;
import domain.Book;
import server.Server;

public class Program {

    public static void main(String[] args) {

        new Thread(new Server()).start();


        new Client().run();

    }


}
