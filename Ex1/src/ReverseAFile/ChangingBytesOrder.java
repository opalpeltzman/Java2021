package ReverseAFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

/**

 * The ChangingBytesOrder program implements an application
 * that copy content from source file and writes it converged
 * to a destination file.

 */
public class ChangingBytesOrder {

    /**

     * The changingBytesOrder method copy a source file content by bytes to
     * a buffer and by iterating the buffer from the end, copy every byte to
     * the destination file.

     */
    public static void changingBytesOrder(File source, File destination){

        byte[] bFile = new byte[(int) source.length()];

        try{
            FileInputStream in = new FileInputStream(source);
            in.read(bFile);
            in.close();
            FileOutputStream fo = new FileOutputStream(destination);

            for(int i = bFile.length - 1; i >= 0 ; i--){
                fo.write((char) bFile[i]);
            }
            fo.close();
        }
        /**

         * The Exception thrown is an IOException, which means that we except exception if:
         * searching for a file or a directory failed,
         * failures related to reading, writing to a file.

         */
        catch (IOException e){
            System.out.println("Exception: " + e.getCause());
        }

    }

    public static void main(String[] args){
        File inputFile = new File("src/ReverseAFile/file1.txt");
        File outputFile = new File("src/ReverseAFile/file2.txt");
        changingBytesOrder(inputFile, outputFile);

    }
}
