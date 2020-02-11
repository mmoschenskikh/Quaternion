import static java.lang.Math.*;

public class Quaternion {
    private final double a, b, c, d; // q = a + bi + cj + dk

    /**
     * Построение кватерниона по коэффициентам.
     */
    Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Построение кватерниона заданием угла поворота и оси вращения.
     */
    Quaternion(double angle, Vector axis) {
        double halfSin = sin(angle / 2);
        a = cos(angle / 2);
        b = axis.getX() * halfSin;
        c = axis.getY() * halfSin;
        d = axis.getZ() * halfSin;
    }

    /**
     * Получение угла поворота.
     */
    public double getAngle() {
        final double angle = 2 * acos(a);
        if (Double.isNaN(angle)) {
            throw new IllegalStateException("Данный кватернион не задаёт угол и ось вращения.");
        }
        return angle;
    }

    /**
     * Получение оси вращения.
     */
    public Vector getAxis() {
        final double halfSin = sin(getAngle() / 2);
        if (Double.isNaN(halfSin)) {
            throw new IllegalStateException("Данный кватернион не задаёт угол и ось вращения.");
        }
        if (halfSin == 0.0) {
            return new Vector(0, 0, 0);
        }
        return new Vector(b / halfSin, c / halfSin, d / halfSin);
    }

    /**
     * Умножение кватерниона на число.
     */
    public Quaternion times(double number) {
        return new Quaternion(number * a, number * b, number * c, number * d);
    }

    /**
     * Сопряжение кватерниона.
     */
    public Quaternion conjugate() {
        return new Quaternion(a, -b, -c, -d);
    }

    /**
     * Сумма двух кватернионов
     */
    public Quaternion plus(Quaternion other) {
        return new Quaternion(a + other.a, b + other.b, c + other.c, d + other.d);
    }

    /**
     * Разность двух кватернионов
     */
    public Quaternion minus(Quaternion other) {
        return new Quaternion(a - other.a, b - other.b, c - other.c, d - other.d);
    }

    /**
     * Произведение двух кватернионов.
     */
    public Quaternion times(Quaternion other) {
        return new Quaternion(
                a * other.a - b * other.b - c * other.c - d * other.d,
                a * other.b + b * other.a + c * other.d - d * other.c,
                a * other.c - b * other.d + c * other.a + d * other.b,
                a * other.d + b * other.c - c * other.b + d * other.a);
    }

    /**
     * Обращение кватерниона
     */
    public Quaternion inverse() {
        return conjugate().times(1 / (norm() * norm()));
    }

    /**
     * Модуль кватерниона.
     */
    public double norm() {
        return sqrt(a * a + b * b + c * c + d * d);
    }

    /**
     * Возвращает скалярную часть кватерниона.
     */
    public double getScalarPart() {
        return a;
    }

    /**
     * Возвращает векторную часть кватерниона.
     */
    public Vector getVectorPart() {
        return new Vector(b, c, d);
    }

    /**
     * Нормирование (получение единичного кватеринона).
     */
    public Quaternion getUnit() {
        return times(norm());
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
    public int hashCode() {
        int result = (int) a;
        result = 31 * result + getVectorPart().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "q = " + a + " + " + b + "i + " + c + "j + " + d + "k";
    }
}
