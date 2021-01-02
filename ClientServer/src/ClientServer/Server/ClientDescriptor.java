package ClientServer.Server;


import ClientServer.ChatClientServer.StringConsumer;
import ClientServer.ChatClientServer.StringProducer;

// for the server side
// an object for every client -> its purpose is to add the nick name to the client
public class ClientDescriptor implements StringConsumer, StringProducer {

    private MessageBoard mBoard = null;

    @Override
    public void consume(String str) {
        if(mBoard != null){
            mBoard.consume(str);
        }
    }

    @Override
    public void addConsumer(StringConsumer sc) {
        if(sc instanceof MessageBoard && mBoard == null){
            mBoard = (MessageBoard) sc;
        }
        mBoard.addConsumer(sc);
    }

    @Override
    public void removeConsumer(StringConsumer sc) {
        if(mBoard != null){
            mBoard.removeConsumer(sc);
        }
    }
}
