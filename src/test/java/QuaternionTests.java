import org.junit.Test;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

public class QuaternionTests {
    Quaternion scalar = new Quaternion(1, 0, 0, 0);
    Quaternion vector = new Quaternion(0, 0, 1, 0);
    Quaternion random = new Quaternion(1.337, 2.28, 18.98, 20.20);
    Quaternion nice = new Quaternion(0.98006, 0.19866, 0.43707, 0.65561); // angle == 0.4; x == 1, y == 2.2, z == 3.3
    Quaternion test = new Quaternion(0.4, new Vector(1, 2.2, 3.3));

    @Test
    public void equals() {
        assertNotEquals(scalar, vector);
        assertEquals(scalar, new Quaternion(1.0, 0.0, 0, 0));
    }

    @Test
    public void quaternionToString() {
        assertEquals("q = 0.98006 + 0.19866i + 0.43707j + 0.65561k", nice.toString());
    }

    @Test
    public void getScalarPart() {
        assertEquals(random.getScalarPart(), 1.337, 1e-5);
        assertEquals(test.getScalarPart(), nice.getScalarPart(), 1e-5);
    }


    private boolean approxEquals(Vector a, Vector b, double delta) {
        return abs(a.getX() - b.getX()) <= delta && abs(a.getY() - b.getY()) <= delta && abs(a.getZ() - b.getZ()) <= delta;
    }

    private void assertApproxEquals(Vector a, Vector b, double delta) {
        assertTrue(approxEquals(a, b, delta));
    }

    @Test
    public void getVectorPart() {
        assertEquals(scalar.getVectorPart(), new Vector(0, 0, 0));
        assertApproxEquals(nice.getVectorPart(), test.getVectorPart(), 1e-5);
    }

    @Test
    public void quaternionByAngleAndAxis() {
        assertEquals(scalar, new Quaternion(0, new Vector(0, 0, 0)));
    }

    @Test
    public void norm() {
        assertEquals(1.0, vector.norm(), 1e-5);
        assertEquals(1.27312, nice.norm(), 1e-5);
        assertEquals(test.norm(), nice.norm(), 1e-5);
    }
}
