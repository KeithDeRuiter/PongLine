package pongline.data.matlib;

import java.util.List;

public final class Matrix3f {
	private float[] data = new float[9];

	public Matrix3f() {
		for (int i = 0; i < data.length; ++i)
			data[i] = 0.0f;
	}

	public Matrix3f(float[] data) {
		if (data.length != this.data.length)
			throw new RuntimeException(
					"Invalid initial data for Mat3f constructor");
		for (int i = 0; i < this.data.length; ++i)
			this.data[i] = data[i];
	}

	public Matrix3f(List<Float> data) {
		if (data.size() != this.data.length)
			throw new RuntimeException(
					"Invalid initial data for Mat3f constructor");
		for (int i = 0; i < this.data.length; ++i)
			this.data[i] = data.get(i);
	}

	public Matrix3f(Vector3f a, Vector3f b, Vector3f c) {
		this.data[0] = a.x;
		this.data[1] = a.y;
		this.data[2] = a.z;

		this.data[3] = b.x;
		this.data[4] = b.y;
		this.data[5] = b.z;

		this.data[6] = c.x;
		this.data[7] = c.y;
		this.data[8] = c.z;
	}

	public Vector3f multiply(Vector3f other) {
		float x, y, z;

		x = data[0] * other.x + data[3] * other.y + data[6] * other.z;
		y = data[1] * other.x + data[4] * other.y + data[7] * other.z;
		z = data[2] * other.z + data[5] * other.y + data[8] * other.z;

		return new Vector3f(x, y, z);
	}

	public Matrix3f multiply(float scale) {
		Matrix3f out = new Matrix3f();

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				out.data[3 * i + j] = this.accessRowColum(i, j) * scale;
			}
		}

		return out;
	}

	private float accessRowColum(int i, int j) {
		return data[i + 3 * j];
	}

	private void putRowColumn(int i, int j, float value) {
		data[i + 3 * j] = value;
	}

	public Matrix3f multiply(Matrix3f other) {
		Matrix3f out = new Matrix3f();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				float value = 0;
				for (int k = 0; k < 3; ++k) {
					value += this.accessRowColum(i, k)
							* other.accessRowColum(k, j);
				}
				out.putRowColumn(i, j, value);
			}
		}
		return out;
	}

	public float determinant() {
		float out = 0.0f;

		out = data[0] * data[4] * data[8] + data[3] * data[7] * data[2]
				+ data[6] * data[1] * data[5] - data[2] * data[4] * data[6]
				- data[5] * data[7] * data[0] - data[8] * data[1] * data[3];

		return out;
	}

	public Matrix3f transpose() {
		return new Matrix3f(new Vector3f(data[0], data[3], data[6]),
				new Vector3f(data[1], data[4], data[7]), new Vector3f(data[2],
						data[5], data[8]));
	}

	public boolean equals(Matrix3f other) {
		for (int i = 0; i < this.data.length; ++i) {
			if (this.data[i] != other.data[i])
				return false;
		}
		return true;
	}

	/**
	 * Returns an array representing the matrix in column major form.
	 * 
	 * @return
	 */
	public float[] toArray() {
		float[] out = new float[this.data.length];
		for (int i = 0; i < this.data.length; ++i)
			out[i] = data[i];
		return out;
	}

	public float[] toArrayRowMajor() {
		float[] out = new float[this.data.length];
		for (int i = 0; i < 3; ++i) {
			out[3 * i] = this.accessRowColum(i, 0);
			out[3 * i + 1] = this.accessRowColum(i, 1);
			out[3 * i + 2] = this.accessRowColum(i, 2);
		}

		return out;
	}

	public Vector3f[] toVectorArray() {
		Vector3f[] out = new Vector3f[3];
		out[0] = new Vector3f(this.data[0], this.data[1], this.data[2]);
		out[1] = new Vector3f(this.data[3], this.data[4], this.data[5]);
		out[2] = new Vector3f(this.data[6], this.data[7], this.data[8]);

		return out;
	}

	public String toString() {
		String out;
		out = "[ " + data[0] + ", " + data[3] + ", " + data[6] + " ]\n";
		out += "[ " + data[1] + ", " + data[4] + ", " + data[7] + " ]\n";
		out += "[ " + data[2] + ", " + data[5] + ", " + data[8] + " ]";

		return out;
	}

	public static Matrix3f zero() {
		return new Matrix3f();
	}

	public float cofactor(int i, int j) {
		float[] smallMatrix = new float[4];
		int smallMatrixIndex = 0;

		for (int indexI = 0; indexI < 3; ++indexI) {
			for (int indexJ = 0; indexJ < 3; ++indexJ) {
				if (indexI != i && indexJ != j) {
					smallMatrix[smallMatrixIndex] = this.accessRowColum(indexI,
							indexJ);
					++smallMatrixIndex;
				}
			}
		}

		float cofactor = smallMatrix[0] * smallMatrix[3] - smallMatrix[1]
				* smallMatrix[2];
		
		if ((i + j) % 2 == 1) {
			return -cofactor;
		}
		return cofactor;
	}

	public Matrix3f cofactorMatrix() {
		Matrix3f out = new Matrix3f();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				float cofactor = this.cofactor(i, j);
				out.data[i + 3 * j] = cofactor;
			}
		}

		return out;
	}

	public Matrix3f invert() {
		Matrix3f adjunct = this.cofactorMatrix().transpose();
		float det = this.determinant();
		if (det == 0.0f) {
			throw new RuntimeException(
					"Tried to invert a non-invertable matrix");
		}
		return adjunct.multiply(1 / det);
	}

	public static Matrix3f identity() {
		Matrix3f out = new Matrix3f();
		for (int i = 0; i < out.data.length; ++i) {
			if (i == 0 || i == 4 || i == 8)
				out.data[i] = 1.0f;
			else
				out.data[i] = 0.0f;
		}
		return out;
	}

	public static Matrix3f getRotationMatrix(float radians) {
		Matrix3f out = new Matrix3f();
		float cos = (float) Math.cos(radians);
		float sin = (float) Math.sin(radians);
		out.data[0] = cos;
		out.data[1] = sin;
		out.data[2] = 0;

		out.data[3] = -sin;
		out.data[4] = cos;
		out.data[5] = 0;

		out.data[6] = 0;
		out.data[7] = 0;
		out.data[8] = 1;

		return out;
	}

	public static Matrix3f getScalingMatrix(float scalex, float scaley) {
		Matrix3f out = new Matrix3f();

		out.data[0] = scalex;
		out.data[1] = 0;
		out.data[2] = 0;

		out.data[3] = 0;
		out.data[4] = scaley;
		out.data[5] = 0;

		out.data[6] = 0;
		out.data[7] = 0;
		out.data[8] = 1;

		return out;
	}

	public static Matrix3f getTranslationMatrix(float tx, float ty) {
		Matrix3f out = Matrix3f.identity();

		out.data[6] = tx;
		out.data[7] = ty;

		return out;
	}

	public static void main(String args[]) {
		float[] arr = { 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f };
		Matrix3f mat1 = Matrix3f.identity();
		Matrix3f mat2 = new Matrix3f(arr);

		System.out.println(mat1 + "\n");
		System.out.println(mat2);

		System.out.println(mat1.accessRowColum(0, 0));
		System.out.println(mat1.multiply(mat2));
	}

}