package ClientServer.ChatClientServer;

// an object that can be connected to consumer
public interface StringProducer {

    public void addConsumer(StringConsumer sc);
    public void removeConsumer(StringConsumer sc);
}
