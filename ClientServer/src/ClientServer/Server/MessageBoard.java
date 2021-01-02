package ClientServer.Server;

import ClientServer.ChatClientServer.ConnectionProxy;
import ClientServer.ChatClientServer.StringConsumer;
import ClientServer.ChatClientServer.StringProducer;

import java.util.ArrayList;

//related to server
// only one object exist in the project
// his purpose is to enable the ability to send messages to all the clients
public class MessageBoard implements StringConsumer, StringProducer {

    // holds clients
    private ArrayList<ConnectionProxy> consumers;

    public MessageBoard() {
        consumers = new ArrayList<ConnectionProxy>();
    }

    // send the string to every client on the list
    @Override
    public void consume(String str) {
        for(StringConsumer consumer : consumers){
            consumer.consume(str);
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        // if sc is ConnectionProxy object only
        if(sc instanceof ConnectionProxy){
            consumers.add((ConnectionProxy) sc);
        }
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        if(sc != null){
            consumers.remove(sc);
        }
    }
}
