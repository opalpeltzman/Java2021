package ClientServer.ChatClientServer;

import ClientServer.ClientChat.ClientGUI;
import ClientServer.Server.ClientDescriptor;

import java.io.*;
import java.net.Socket;

public class ConnectionProxy extends Thread implements StringConsumer, StringProducer {

    private Socket socket = null;
    private InputStream is = null;
    private OutputStream os = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private String received = null;
    private StringConsumer consumer = null;

    // create new ConnectionProxy thread
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
        // the client and server needs to connect with the same port
        System.out.println("The client server connection port " + socket.getPort());
    }

    // getting a string to the thread connectionProxy(client/server)
    // from a specific server/client the server is listening to
    @Override
    public void run() {
        super.run();
        while(true){
            try {
                is = socket.getInputStream();
                dis = new DataInputStream(is);
                // if there is no data (readUTF) the thread stops
                received = dis.readUTF(); // waiting for the client/server to write something

                if(received == "quit"){
                    removeConsumer(consumer);
                    System.exit(0);
                }
                System.out.println(received + " was received");
                consumer.consume(received);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // initialize the consumer socket
    public ConnectionProxy(Socket socket) throws IOException {
        this.socket = socket;
    }

    //the consumer output to the specific server/client
    @Override
    public void consume(String str) {
        if(str.equals("disconnect")){
            removeConsumer(this);
            System.exit(0);
        }
        try {
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            dos.writeUTF(str);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // adding a client to the connection with the server
    @Override
    public void addConsumer(StringConsumer sc) {
        // if sc is an instance of StringConsumer
        if(sc instanceof ClientDescriptor || sc instanceof ClientGUI){
            consumer = sc;
        }
    }

    @Override
    public void removeConsumer(StringConsumer sc) {

        if(socket != null){
            try {
                System.out.println("disconnected client in proxy");
                socket.close();
                dis.close();
                dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
