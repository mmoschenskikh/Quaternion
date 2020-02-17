import org.junit.jupiter.api.Test;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTests {
    Vector zero = new Vector(0, 0, 0);
    Vector a = new Vector(1, -1, 0);
    Vector b = new Vector(3, 2, -2);
    Vector c = new Vector(1.5, -2.5, 3.5);

    @Test
    public void length() {
        assertEquals(0.0, zero.length(), 1e-5);
        assertEquals(sqrt(2), a.length(), 1e-5);
        assertEquals(sqrt(17), b.length(), 1e-5);
        assertEquals(sqrt(83) / 2, c.length(), 1e-5);
    }

    @Test
    public void plus() {
        assertEquals(a, zero.plus(a));
        assertEquals(new Vector(4, 1, -2), a.plus(b));
        assertEquals(new Vector(5.5, -1.5, 1.5), a.plus(b.plus(c)));
    }

    @Test
    public void minus() {
        assertEquals(b, b.minus(zero));
        assertEquals(new Vector(2, 3, -2), b.minus(a));
        assertEquals(new Vector(-0.5, -5.5, -5.5), a.minus(b.minus(c)));
    }

    @Test
    public void unaryMinus() {
        assertEquals(zero, zero.unaryMinus());
        assertEquals(new Vector(-1.5, 2.5, -3.5), c.unaryMinus());
        assertEquals(zero.minus(b), b.unaryMinus());
    }

    @Test
    public void dotProduct() {
        assertEquals(0.0, c.dotProduct(zero), 1e-5);
        assertEquals(1.0, a.dotProduct(b), 1e-5);
        assertEquals(-7.5, c.dotProduct(b), 1e-5);
    }

    @Test
    public void crossProduct() {
        final Vector test = new Vector(2, -13.5, -10.5);
        assertEquals(test, b.crossProduct(c));
        assertEquals(test.unaryMinus(), c.crossProduct(b));
        assertEquals(zero, zero.crossProduct(b));
    }

    @Test
    public void times() {
        assertEquals(zero, zero.times(123456));
        assertEquals(zero, a.times(0));
        assertEquals(new Vector(3, -5, 7), c.times(2));
    }
}