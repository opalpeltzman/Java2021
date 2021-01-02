package ClientServer.Server;

import ClientServer.ChatClientServer.ConnectionProxy;
import ClientServer.Server.ClientDescriptor;
import ClientServer.Server.MessageBoard;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {
    public static void main(String args[]) {

        ServerSocket server = null;
        MessageBoard mb = new MessageBoard();

        try {
            server = new ServerSocket(1300, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket socket = null;
        ClientDescriptor client = null;
        ConnectionProxy connection = null;

        while (true) {

            try {

                socket = server.accept();

                connection = new ConnectionProxy(socket);

                client = new ClientDescriptor();

                connection.addConsumer(client);

                client.addConsumer(mb);

                mb.addConsumer(connection);

                connection.start();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
