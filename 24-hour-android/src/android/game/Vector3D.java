package android.game;

/**
 * 
 * @author Dylan
 * 
 *         Basic vector class implementation. Should expand to include more
 *         advanced vector manipulation, possibly interface with matrix and
 *         point classes. This class is also used to represent points, as there
 *         would be lots of redundant code otherwise. Be careful to keep a
 *         distinction between points and vectors - they are not mathematically
 *         the same!
 * 
 */

public class Vector3D {
	private float x, y, z;

	public Vector3D() { // Initialize empty vector
	}

	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3D(Matrix matrix){
		if (matrix.cols == 4 && matrix.rows == 1 || matrix.rows == 4 && matrix.cols == 1){
			x = matrix.values[0];
			y = matrix.values[1];
			z = matrix.values[2];
		}
	}

	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	/*
	 * Advanced vector operations
	 */

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float lengthSquared() {
		return x * x + y * y + z * z;
	}

	public Vector3D normalize() { // Return unit vector with same direction
		return scale(1 / length());
	}

	public float dotProduct(Vector3D vector) {
		return x * vector.x + y * vector.y + z * vector.z;
	}

	public Vector3D crossProduct(Vector3D vector) {
		return new Vector3D(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
	}

	public Vector3D projection(Vector3D vector) { // Project onto another vector
		float length = vector.length();
		return vector.scale(dotProduct(vector) / (length * length));
	}

	public Vector3D perpendicular(Vector3D vector) { // Orthogonal to projection
		// Return parallel to another vector.
		// Only use if projection not calculated.
		// Otherwise use previously calculated projection and subtract().
		return subtract(projection(vector));
	}
	
	public Matrix rotationM(float angle){
		float c = (float) Math.cos(angle);
		float s = (float) Math.sin(angle);
		float t = 1 - c;
		
//		Matrix m = new Matrix(4, 4, new float[] {t * x * x + c, t * x * y + s * z, t * x * z - s * y, 0,
//												 t * x * y - s * z, t * y * y + c, t * y * z + s * x, 0,
//												 t * x * z + s * y, t * y * z - s * x, t * z * z + c, 0,
//												 0, 0, 0, 1});
		
		Matrix m = new Matrix(4, 4, new float[] {t * x * x + c, t * x * y - s * z, t * x * z + s * y, 0,
												 t * x * y + s * z, t * y * y + c, t * y * z - s * x, 0,
												 t * x * z - s * y, t * y * z + s * x, t * z * z + c, 0,
												 0, 0, 0, 1});
		//[tx^2 + c, txy - sz, txz + sy]
		//[txy + sz, ty^2 + c, tyz - sx]
		//[txz - sy, tyz + sx, tz^2 + c]
		return m;
	}
	
	/*
	 * Matrix operations
	 */
	
	public Matrix pointMatrix(){
		Matrix matrix = new Matrix(4, 1);
		matrix.setAll(new float[] {x, y, z, 0});
		return matrix;
	}
	
	public Matrix rowMatrix(){
		Matrix matrix = new Matrix(1, 4);
		matrix.setAll(new float[] {x, y, z, 1});
		return matrix;
	}
	
	public Matrix colMatrix(){
		Matrix matrix = new Matrix(4, 1);
		matrix.setAll(new float[] {x, y, z, 1});
		return matrix;
	}
	
	public Matrix tensor(Vector3D vector){
		return colMatrix().multiply(vector.rowMatrix());
	}
	
	public Vector3D multiply(Matrix m){
		return new Vector3D(m.multiply(this.colMatrix()));
	}
	
	public Matrix multiplyToMatrix(Matrix m){
		return m.multiply(this.colMatrix());
	}

	/*
	 * Point operations
	 */

	public float distance(Vector3D point) {
		return subtract(point).length();
	}

	public float distanceSquared(Vector3D point) {
		return subtract(point).lengthSquared();
	}

	/*
	 * Basic operations
	 */

	public Vector3D add(Vector3D vector) {
		return new Vector3D(this.x + vector.x, this.y + vector.y, this.z + vector.z);
	}

	public Vector3D subtract(Vector3D vector) {
		return add(vector.scale(-1));
	}

	public Vector3D scale(float scalar) {
		return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
	}

	/*
	 * Setting and getting values
	 */

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}
}
