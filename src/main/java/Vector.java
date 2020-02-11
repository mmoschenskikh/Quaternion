public class Vector {
    private final double x, y, z;

    /** Построение вектора по компонентам. */
    Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Модуль вектора. */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /** Возвращает компоненту x (проекцию на ось Ox) вектора. */
    public double getX() {
        return x;
    }

    /** Возвращает компоненту y (проекцию на ось Oy) вектора. */
    public double getY() {
        return y;
    }

    /** Возвращает компоненту z (проекцию на ось Oz) вектора. */
    public double getZ() {
        return z;
    }

    /** Сумма двух векторов. */
    public Vector plus(Vector other) {
        return new Vector(x + other.x, y + other.y, z + other.z);
    }

    /** Противоположный вектор. */
    public Vector unaryMinus() {
        return new Vector(-x, -y, -z);
    }

    /** Разность двух векторов. */
    public Vector minus(Vector other) {
        return this.plus(other.unaryMinus());
    }

    /** Скалярное произведение векторов. */
    public double dotProduct(Vector other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /** Векторное произведение векторов. */
    public Vector crossProduct(Vector other) {
        return new Vector(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    /** Умножение вектора на число. */
    public Vector times(double number) {
        return new Vector(number * x, number * y, number * z);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object instanceof Vector) {
            Vector other = (Vector) object;
            return x == other.x && y == this.y && z == this.z;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (int) x;
        result = 31 * result + (int) y;
        result = 31 * result + (int) z;
        return result;
    }

    @Override
    public String toString() {
        return "a = " + x + "i + " + y + "j + " + z + "k";
    }
}
