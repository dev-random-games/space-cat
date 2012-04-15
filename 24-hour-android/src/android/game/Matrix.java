package android.game;

/**
 * 
 * @author Dylan
 * 
 *         General matrix class to support any number of rows and columns. I may
 *         need to make specific matrix dimension classes later for speed.
 * 
 */

public class Matrix {
	double[] values;
	public final int rows, cols;
	private Matrix augmented = null;

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		values = new double[rows * cols];
	}

	public Matrix(int rows, int cols, double[] vals) {
		this.rows = rows;
		this.cols = cols;
		if (vals.length == rows * cols) {
			values = vals;
		}
	}

	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(get(i, j));
				System.out.print("  ");
			}
			System.out.println();
		}
	}

	public Matrix identity(int dimensions) {
		Matrix matrix = new Matrix(dimensions, dimensions);
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				if (i == j) {
					matrix.set(i, j, 1);
				} else {
					matrix.set(i, j, 0);
				}
			}
		}
		return matrix;
	}

	public void set(int row, int col, double value) {
		values[col + cols * row] = value;
	}

	public void setAll(double[] vals) {
		if (vals.length == this.values.length) {
			this.values = vals;
		}
	}

	public void setRow(int row, double[] values) {
		if (values.length == cols) {
			for (int i = 0; i < cols; i++) {
				set(row, i, values[i]);
			}
		}
	}

	public void setCol(int col, double[] values) {
		if (values.length == rows) {
			for (int i = 0; i < rows; i++) {
				set(i, col, values[i]);
			}
		}
	}

	public double get(int row, int col) {
		return values[col + cols * row];
	}

	public double[] getRow(int row) {
		double[] vals = new double[rows];
		for (int i = 0; i < cols; i++) {
			vals[i] = get(row, i);
		}
		return vals;
	}

	public Matrix add(Matrix matrix) {
		if (matrix.cols == cols && matrix.rows == rows) {
			Matrix newMatrix = new Matrix(rows, cols);
			for (int i = 0; i < cols * rows; i++) {
				newMatrix.values[i] = this.values[i] + matrix.values[i];
			}
			return newMatrix;
		} else {
			try {
				throw new Exception("Attempted to add incompatible matrices.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public Matrix multiply(Matrix matrix) { // Text if compatible, and if so
											// return the matrix product.
		if (this.cols == matrix.rows) {
			Matrix result = new Matrix(this.rows, matrix.cols);
			for (int i = 0; i < result.rows; i++) {
				for (int j = 0; j < result.cols; j++) {
					double value = 0;
					for (int index = 0; index < this.cols; index++) {
						value += this.get(i, index) * matrix.get(index, j);
					}
					result.set(i, j, value);
				}
			}
			return result;
		} else {
			try {
				throw new Exception("Attempted to multiply incompatible matrices.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public Matrix transpose() {
		Matrix matrix = new Matrix(cols, rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix.set(j, i, this.get(i, j));
			}
		}
		return matrix;
	}

	public Matrix scale(double scalar) {
		Matrix matrix = new Matrix(rows, cols);
		matrix.values = values;
		for (double value : matrix.values) {
			value *= scalar;
		}
		return matrix;
	}

	public Matrix duplicate() {
		Matrix matrix = new Matrix(rows, cols, values);
		matrix.augment(augmented);
		return matrix;
	}

	/*
	 * Basic row operations
	 */

	public void flipRows(int row1, int row2) {
		for (int i = 0; i < cols; i++) {
			double temp = get(row1, i);
			set(row1, i, get(row2, i));
			set(row2, i, temp);
		}
	}

	public void addRows(int row1, int row2) {
		for (int i = 0; i < cols; i++) {
			set(row1, i, get(row2, i) + get(row1, i));
		}
	}

	public void scaleRow(int row, double scalar) {
		for (int i = 0; i < cols; i++) {
			set(row, i, get(row, i) * scalar);
		}
	}

	/*
	 * Augmentation and augmented row operations
	 */

	public void augment(Matrix matrix) {
		if (matrix.rows == rows) {
			augmented = matrix;
		}
	}

	public Matrix deAugment() {
		Matrix m = augmented;
		augmented = null;
		return m;
	}

	public Matrix getAugmented() {
		return augmented;
	}

	public void flipRowsAug(int row1, int row2) {
		flipRows(row1, row2);
		augmented.flipRows(row1, row2);
	}

	public void addRowsAug(int row1, int row2) {
		addRows(row1, row2);
		augmented.addRows(row1, row2);
	}

	public void scaleRowAug(int row, double scalar) {
		scaleRow(row, scalar);
		augmented.scaleRow(row, scalar);
	}

	/*
	 * Advanced matrix operations
	 */

	public Matrix _inverseSide() {
		/**
		 * If the matrix has been augmented, solve so that the unaugmented
		 * portion is an identity matrix. Currently appears to be unable to
		 * handle zero entries - quite a problem if you ask me!
		 */
		if (augmented != null && rows == cols) {
			Matrix matrix = duplicate();
			for (int i = 0; i < cols; i++) {
				matrix.print();
				// Find largest value in column, then move that value to the top
				// row.
				int largest = 0;
				double largestVal = -1;
				for (int j = i; j < rows; j++) {
					double val = Math.abs(matrix.get(j, i));
					if (val > largest) {
						largestVal = val;
						largest = j;
					}
				}
				if (largestVal == 0) {
					return null; // Quit if col is all zeros.
				}
				if (largest != 0) {
					matrix.flipRowsAug(i, largest);
				}
				scaleRowAug(i, 1 / get(i, i));
				for (int j = 0; j < rows; j++) {
					if (j != i) {
						double scaleVal = matrix.get(j, i);
						// Use i because matrix is square.
						// Scale row, add to next row, then scale back. This can
						// definitely be done faster.
						matrix.scaleRowAug(i, -scaleVal);
						matrix.addRowsAug(j, i);
						matrix.scaleRowAug(i, -1 / scaleVal);
					}
				}
				// Matrix is now upper triangle. Use backwards substitution to
				// solve the rest.

			}
			return matrix;
		} else {
			return null;
		}
	}

}
