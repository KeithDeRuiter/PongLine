package pongline.data.matlib;

public final class Vector2f {
    public float x, y;
    
    public Vector2f() {
	this.x = this.y = 0.0f;
    }

    public Vector2f(float x, float y) {
	this.x = x;
	this.y = y;
    }
    
    public Vector2f(float all) {
	this.x = this.y = all;
    }

    public Vector2f add(Vector2f other) {
	return new Vector2f(x + other.x, y + other.y);
    }
    
    public Vector2f add(float value) {
	return new Vector2f(x + value, y + value);
    }

    public Vector2f multiply(float scalar) {
	return new Vector2f(x * scalar, y * scalar);
    }

    public Vector2f subtract(Vector2f other) {
	return this.add(other.multiply(-1));
    }
    
    public Vector2f divide(float scalar) {
	return this.multiply(1.0f/scalar);
    }

    public Vector2f dot(Vector2f other) {
	return new Vector2f(x * other.x, y * other.y);
    }

    public Vector2f normalize() {
	float dist = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	return new Vector2f(x / dist, y / dist);
    }

    public Vector2f vectorTo(Vector2f other) {
	return other.subtract(this);
    }

    public float distanceTo(Vector2f other) {
	return (float) Math.sqrt(Math.pow(other.x - x, 2)
		+ Math.pow(other.y - y, 2));
    }
    
    public boolean equals(Vector2f other) {
	return (this.x == other.x && this.y == other.y);
    }
    
    public float magnitude() {
	return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
    
    public float[] toArray() {
	float[] out = new float[2];
	out[0] = x;
	out[1] = y;
	
	return out;
    }
    
    public Vector3f homogenize() {
        return new Vector3f(this.x, this.y, 1);
    }
    
    public String toString() {
	return "(" + this.x + ", " + this.y + ")";
    }
}
