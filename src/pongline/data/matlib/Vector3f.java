package pongline.data.matlib;

public final class Vector3f {
    public float x, y, z;

    public Vector3f() {
        this.x = this.y = this.z = 0.0f;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float all) {
        this.x = this.y = this.z = all;
    }

    public Vector3f add(Vector3f other) {
        return new Vector3f(x + other.x, y + other.y, z + other.z);
    }

    public Vector3f multiply(float scalar) {
        return new Vector3f(x * scalar, y * scalar, z * scalar);
    }

    public Vector3f multiply(Vector3f other) {
        return new Vector3f(x * other.x, y * other.y, z * other.z);
    }

    public Vector3f subtract(Vector3f other) {
        return this.add(other.multiply(-1));
    }

    public Vector3f subtract(float value) {
        return new Vector3f(x - value, y - value, z - value);
    }

    public Vector3f divide(float scalar) {
        return this.multiply(1.0f / scalar);
    }

    public Vector3f divide(Vector3f other) {
        return new Vector3f(x / other.x, y / other.y, z / other.z);
    }

    public float dot(Vector3f other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3f cross(Vector3f other) {
        return new Vector3f(y * other.z - z * other.y, z * other.x - x
                * other.z, x * other.y - y * other.x);
    }

    public Vector3f normalize() {
        float dist = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
                + Math.pow(z, 2));
        return new Vector3f(x / dist, y / dist, z / dist);
    }

    public Vector3f vectorTo(Vector3f other) {
        return other.subtract(this);
    }

    public float distanceTo(Vector3f other) {
        return (float) Math.sqrt(Math.pow(other.x - x, 2)
                + Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2));
    }

    public boolean equals(Vector3f other) {
        return (this.x == other.x && this.y == other.y && this.z == other.z);
    }

    public float magnitude() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)
                + Math.pow(this.z, 2));
    }

    public Vector2f dehomogenize() {
        return new Vector2f(this.x / this.z, this.y / this.z);
    }

    public float[] toArray() {
        float[] out = new float[3];
        out[0] = x;
        out[1] = y;
        out[2] = z;

        return out;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}