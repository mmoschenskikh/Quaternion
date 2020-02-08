import static java.lang.Math.*;

public class Quaternion {
    private final double a, b, c, d; // q = a + bi + cj + dk

    /** Построение кватерниона по коэффициентам. */
    Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /** Построение кватерниона заданием угла поворота и оси вращения. */
    Quaternion(double angle, Vector axis) {
        double halfSin = sin(angle / 2);
        a = cos(angle / 2);
        b = axis.getX() * halfSin;
        c = axis.getY() * halfSin;
        d = axis.getZ() * halfSin;
    }

    /** Модуль кватерниона. */
    public double norm() {
        return sqrt(a * a + b * b + c * c + d * d);
    }

    /** Сопряжение кватерниона. */
    public Quaternion conjugate() {
        return new Quaternion(a, -b, -c, -d);
    }

    /** Возвращает скалярную часть кватерниона. */
    public double getScalarPart() {
        return a;
    }

    /** Возвращает векторную часть кватерниона. */
    public Vector getVectorPart() {
        return new Vector(b, c, d);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object instanceof Quaternion) {
            Quaternion other = (Quaternion) object;
            return a == other.a && b == this.b && c == this.c && d == this.d;
        }
        return false;
    }

    @Override
    public String toString() {
        return "q = " + a + " + " + b + "i + " + c + "j + " + d + "k";
    }
}
