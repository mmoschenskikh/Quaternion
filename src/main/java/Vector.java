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
    public String toString() {
        return "a = " + x + "i + " + y + "j + " + z + "k";
    }
}
