package Triangle;

import javax.print.DocFlavor;
/**

 * The TriangleException class extends the Exception class
 * that is defined in the Java core API,
 * in order to pass the user a specific message that
 * compatible to the exception, if happens.

 */
public class TriangleException extends Exception {

    /**

     * The TriangleException constructor gets a message from the
     * created object and send it to the Exception class.

     */
    public TriangleException(String message){
        super(message);
    }
}
