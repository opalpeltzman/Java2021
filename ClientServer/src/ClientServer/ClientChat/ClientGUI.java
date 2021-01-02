package ClientServer.ClientChat;

import ClientServer.ChatClientServer.ConnectionProxy;
import ClientServer.ChatClientServer.StringConsumer;
import ClientServer.ChatClientServer.StringProducer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

// for the client side
// an object for every client
public class ClientGUI implements StringConsumer, StringProducer {

    private JFrame frame;
    private static JTextArea textArea;
    private JScrollPane scrollPane;
    private JTextField inputChat;

    private JFrame nameFrame;
    private static JTextArea nameTextArea;
    private JTextField clientName;
    private JLabel l1;


//    private JButton sendM;
//    private JButton disconnect;

//    private ActionListener sendMessageListener;
//    private ActionListener sendNameListener;
//    String fontFamily = "Arial, sans-serif";
//    Font font;

    private Socket socket = null;
    private ConnectionProxy connection = null;
    private int PORT = 1300;
    private  String name;
    // when the user is writing text and send it , the text
    // is being send to proxy.consume(text)

    //when we want to send text from server side to the user we will
    // use clientGUI.consume -> happens at line 37 in ConnectionProxy class

    //Listeners:
    // user text handler, name handler
    class mListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            if(!clientName.getText().equals("")){
//                name = clientName.getText();
//                clientName.setText("");
//            }

            if(!inputChat.getText().equals("")){
                connection.consume(name + ": " + inputChat.getText());
                inputChat.setText("");
            }
        }
    }

    // get client name
    class NListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!clientName.getText().equals("")){
                name = clientName.getText();
                clientName.setText("");
                nameFrame.setVisible(false);
                initializeChat();
            }
//                removeConsumer(ClientGUI.this);
        }
    }

    public ClientGUI() {
        System.out.println("new user entering the chat");

        nameFrame = new JFrame("Client Name");
        clientName = new JTextField(15);
        clientName.addActionListener(new NListener());
        nameFrame.setLayout(new FlowLayout());
        l1 = new JLabel("Please Enter Your Name:", JLabel.CENTER);
        l1.setForeground(Color.blue);
        nameFrame.add(l1);
        nameFrame.add(clientName);
        nameFrame.setSize(300, 250);
        nameFrame.setResizable(false);
        nameFrame.setVisible(true);

    }

    public void initializeChat(){
        // initialize
        frame = new JFrame("Chat");
        textArea = new JTextArea(10, 30);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(380, 100));

        // Assuming this is the chat client's window where we read text sent out
        // and received, we don't want our Text Area to be editable!
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // client message and actionListener
        inputChat = new JTextField(30);
        inputChat.addActionListener(new mListener());

        // adding scroll and text to layout
        frame.setLayout(new FlowLayout());
        frame.add(inputChat, SwingConstants.CENTER);
        frame.add(scrollPane, SwingConstants.CENTER);

        frame.setSize(400, 200);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                connection.consume("disconnect");
                super.windowClosed(e);
                // calling method to close this client connection
                removeConsumer(ClientGUI.this);
                System.exit(0);

            }
        }) ;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getPORT() {
        return PORT;
    }

    @Override
    public void consume(String str) {
        textArea.append(str + "\n");
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        if (sc instanceof ConnectionProxy) {
            connection = (ConnectionProxy) sc;
        }
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        if(connection != null){
            try {
                socket.close();
                System.out.println("disconnected client");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        ClientGUI client = new ClientGUI();
        Socket mSocket = null;
        try {
            mSocket = new Socket("localhost", client.getPORT());
            client.setSocket(mSocket);
            ConnectionProxy mConnection = new ConnectionProxy(mSocket);
            mConnection.addConsumer(client);
            client.addConsumer(mConnection);
            mConnection.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
