package Triangle;

public class Main {
    public static void main(String[] args) {
        try{
            Triangle triangle = new Triangle(5, 6, 7);      // correct v
            triangle.setSide2(1);                           // Exception thrown
        }
        catch (TriangleException t){
            System.out.println(t.getMessage());
        }
    }
}
