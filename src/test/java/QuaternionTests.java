import org.junit.jupiter.api.Test;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuaternionTests {
    Quaternion zero = new Quaternion(0, 0, 0, 0);

    Quaternion scalar1 = new Quaternion(1, 0, 0, 0);
    Quaternion scalar2 = new Quaternion(0, new Vector(256, 128, 64));

    Quaternion nonzero = new Quaternion(1.337, 2.28, 18.98, 20.2);

    Quaternion test1 = new Quaternion(sqrt(3) / 2, 0.615, -2.2835, 1.25);
    Quaternion test2 = new Quaternion(PI / 3, new Vector(1.23, -4.567, 10 / 4.0));

    private boolean approxEquals(Vector a, Vector b, double delta) {
        return abs(a.getX() - b.getX()) <= delta &&
                abs(a.getY() - b.getY()) <= delta &&
                abs(a.getZ() - b.getZ()) <= delta;
    }

    private boolean approxEquals(Quaternion a, Quaternion b, double delta) {
        return abs(a.getScalarPart() - b.getScalarPart()) <= delta &&
                approxEquals(a.getVectorPart(), b.getVectorPart(), delta);
    }

    private void assertApproxEquals(Vector a, Vector b, double delta) {
        assertTrue(approxEquals(a, b, delta));
    }

    private void assertApproxEquals(Quaternion a, Quaternion b, double delta) {
        assertTrue(approxEquals(a, b, delta));
    }

    private void assertApproxNotEquals(Vector a, Vector b, double delta) {
        assertFalse(approxEquals(a, b, delta));
    }

    private void assertApproxNotEquals(Quaternion a, Quaternion b, double delta) {
        assertFalse(approxEquals(a, b, delta));
    }

    @Test
    public void equals() { // проверка функции equals и эквивалентности обоих конструкторов
        assertApproxNotEquals(zero, scalar1, 1e-5);
        assertApproxNotEquals(nonzero, test1, 1e-5);
        assertApproxEquals(scalar1, scalar2, 1e-5);
        assertApproxEquals(test1, test2, 1e-5);
    }

    @Test
    public void getAngle() {
        assertEquals(0, scalar1.getAngle(), 1e-5);
        assertEquals(PI / 3, test1.getAngle(), 1e-5);
        assertEquals(PI / 3, test2.getAngle(), 1e-5);
        assertThrows(IllegalStateException.class, () -> nonzero.getAngle());
    }

    @Test
    public void getAxis() {
        assertApproxEquals(scalar2.getAxis(), new Vector(0, 0, 0), 1e-5);
        assertApproxEquals(test1.getAxis(), new Vector(1.23, -4.567, 10 / 4.0), 1e-5);
        assertApproxEquals(test2.getAxis(), new Vector(1.23, -4.567, 10 / 4.0), 1e-5);
        assertThrows(IllegalStateException.class, () -> nonzero.getAxis());
    }

    @Test
    public void scalarProduct() {
        assertApproxEquals(zero, nonzero.times(0.0), 1e-5);
        assertApproxEquals(test1, test2.times(1.0), 1e-5);
        assertApproxEquals(new Quaternion(39.81586, 67.8984, 565.2244, 601.556), nonzero.times(29.78), 1e-5);
        assertApproxEquals(new Quaternion(-2.674, -4.56, -37.96, -40.4), nonzero.times(-2), 1e-5);
    }

    @Test
    public void conjugate() {
        assertApproxEquals(zero, zero.conjugate(), 1e-5);
        assertApproxEquals(test2, test2.conjugate().conjugate(), 1e-5);
        assertApproxEquals(test1.conjugate(), new Quaternion(test1.getAngle(), test1.getAxis().unaryMinus()), 1e-5);
    }

    @Test
    public void plus() {
        assertApproxEquals(nonzero, nonzero.plus(zero), 1e-5);
        assertApproxEquals(test1.plus(test2), test1.times(2), 1e-5);
        assertApproxEquals(nonzero.conjugate().plus(nonzero),
                new Quaternion(nonzero.getScalarPart() * 2, 0, 0, 0), 1e-5);
    }

    @Test
    public void minus() {
        assertApproxEquals(nonzero, nonzero.minus(zero), 1e-5);
        assertApproxEquals(test1.minus(test2), zero, 1e-5);
        assertApproxEquals(nonzero.conjugate().minus(nonzero),
                new Quaternion(0, -2 * 2.28, -2 * 18.98, -2 * 20.2), 1e-5);
    }

    @Test
    public void hamiltonProduct() {
        assertApproxEquals(zero, nonzero.times(zero), 1e-5);
        assertApproxEquals(scalar1, scalar1.times(scalar2), 1e-5);
        assertApproxEquals(new Quaternion(17.84651, 72.64849, 22.95712, 2.28588), nonzero.times(test1), 1e-5);
    }

    @Test
    public void inverse() {
        assertThrows(IllegalStateException.class, () -> zero.inverse());
        assertApproxEquals(new Quaternion(0.00172, -0.00294, -0.02448, -0.02606), nonzero.inverse(), 1e-5);
        assertApproxEquals(new Quaternion(0.10955, -0.07780, 0.28886, -0.15813), test1.inverse(), 1e-5);
    }

    @Test
    public void norm() {
        assertEquals(0, zero.norm(), 1e-5);
        assertEquals(1, scalar1.norm(), 1e-5);
        assertEquals(27.8436, nonzero.norm(), 1e-5);
        assertEquals(2.8116, test1.norm(), 1e-5);
        assertEquals(sqrt((test1.times(test1.conjugate())).getScalarPart()), test1.norm(), 1e-5);
    }

    @Test
    public void getScalarPart() {
        assertEquals(0, zero.getScalarPart(), 1e-5);
        assertEquals(1, scalar1.getScalarPart(), 1e-5);
        assertEquals(1.337, nonzero.getScalarPart(), 1e-5);
        assertEquals(sqrt(3) / 2, test2.getScalarPart(), 1e-5);
    }

    @Test
    public void getVectorPart() {
        assertApproxEquals(new Vector(0, 0, 0), zero.getVectorPart(), 1e-5);
        assertApproxEquals(new Vector(0, 0, 0), scalar1.getVectorPart(), 1e-5);
        assertApproxEquals(new Vector(2.28, 18.98, 20.2), nonzero.getVectorPart(), 1e-5);
        assertApproxEquals(test1.getVectorPart(), test2.getVectorPart(), 1e-5);
    }

    @Test
    public void getUnit() {
        assertThrows(IllegalStateException.class, () -> zero.getUnit());
        assertApproxEquals(new Quaternion(1, 0, 0, 0), scalar1, 1e-5);
        assertApproxEquals(new Quaternion(0.30802, 0.21874, -0.81217, 0.44459), test1.getUnit(), 1e-5);
        assertApproxEquals(new Quaternion(0.04802, 0.08189, 0.68166, 0.72548), nonzero.getUnit(), 1e-5);
    }
}
