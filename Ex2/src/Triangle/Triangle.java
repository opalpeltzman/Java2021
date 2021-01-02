package Triangle;

/**

 * The triangle class enable the user to create a triangle
 * according to the Triangle Inequality Theorem.

 */
public class Triangle {
    private double side1;
    private double side2;
    private double side3;

    TriangleException TException = new TriangleException("cannot compose a triangle");
    /**

     * The triangle constructor accepts 3 lenghts for the triangle sides,
     * if the lengths are suitable to the Triangle Inequality Theorem
     * the constructor creates a triangle else a TriangleException is
     * been thrown.

     */
    public Triangle(double side1, double side2, double side3) throws TriangleException{
        if (((side1 + side2) < side3) || ((side1 + side3) < side2) || ((side2 + side3) < side1)) {
            throw TException;
        } else {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
    }

    /**

     * A setter that gets one side of a triangle length.
     * If the length is suitable to the Triangle Inequality
     * Theorem the setSideX (X = 1) changes the specific side else
     * a TriangleException is been thrown.

     */
    public void setSide1(double side1) throws TriangleException {
        if (((side1 + this.side2) < this.side3) || ((side1 + this.side3) < this.side2) || ((this.side2 + this.side3) < side1)) {
            throw TException;
        } else {
            this.side1 = side1;
        }
    }
    /**

     * A setter that gets one side of a triangle length.
     * If the length is suitable to the Triangle Inequality
     * Theorem the setSideX (X = 2) changes the specific side else
     * a TriangleException is been thrown.

     */
    public void setSide2(double side2) throws TriangleException {
        if (((this.side1 + side2) < this.side3) || ((this.side1 + this.side3) < side2) || ((side2 + this.side3) < this.side1)) {
            throw TException;
        }else {
        this.side2 = side2;
        }
    }
    /**

     * A setter that gets one side of a triangle length.
     * If the length is suitable to the Triangle Inequality
     * Theorem the setSideX (X = 3) changes the specific side else
     * a TriangleException is been thrown.

     */
    public void setSide3(double side3) throws TriangleException {
        if (((this.side1 + this.side2) < side3) || ((this.side1 + side3) < this.side2) || ((this.side2 + side3) < this.side1)) {
            throw TException;
        } else {
            this.side3 = side3;
        }
    }
}
