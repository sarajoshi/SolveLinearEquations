import java.util.Arrays;
import java.util.Scanner;

/**
 * Name:    Sara Joshi
 * Course:  CS 3010
 * Date:    March 11, 2019
 * Project: 1
 * An implementation of iterative methods to solve linear equations.
 */


public class Solve_Linear_Equations {

	private static double[][] myArray;

	private static double[][] array(double[][] myArray) throws Exception {
		getMatrixFromFile matrixFile = new getMatrixFromFile();
		myArray = matrixFile.getMatrix();
		for(int i = 0; i < myArray.length; i++){
		       for(int j = 0; j < myArray[0].length; j++){
		       }
		   }
		return myArray;
	}
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		System.out.println("Enter one of the following methods to solve a linear equation:");
		System.out.println("1 - Jacobi Method");
		System.out.println("2 - Partial Pivoting");
		System.out.println("3 - Seidal");
		Scanner scanchoice = new Scanner(System.in);
		System.out.println();
		int choiceentry = scanchoice.nextInt();

		switch(choiceentry){
			case 1:
				new Solve_Linear_Equations().jacobi(array(myArray), 0);
			  break;
			case 2:
				new Solve_Linear_Equations().partialPivotting(array(myArray)); 
			  break;
			case 3:
				new Solve_Linear_Equations().seidel(array(myArray), 0);
			  break;
			   }
	}
	
	public void jacobi(double[][] matrix, double precision) {
        System.out.println("Jacobi");

        int n = matrix.length;
        int maxIterations = 100;

        double[][] aMatrix = initializeA(matrix);
        double[] bMatrix = initializeB(matrix);
        double[] currentXMatrix = new double[n]; //x
        double[] oldXMatrix = new double[n]; //y

        double delta = Math.pow(1, -10);
        Arrays.fill(currentXMatrix, 0);

        double diag, sum;

        k_loop:
        for (int k = 0; k < maxIterations; k++) {
            System.arraycopy(currentXMatrix, 0, oldXMatrix, 0, oldXMatrix.length);

            System.out.println();
            System.out.println("k = " + k);

            System.out.println();
            System.out.println("Previous X Matrix");
            printXArray(oldXMatrix);

            System.out.println("Current X Matrix");
            printXArray(currentXMatrix);

            for (int i = 0; i < n; i++) {
                sum = bMatrix[i];
                diag = aMatrix[i][i];

                if (Math.abs(diag) < delta) {
                    System.out.println("Diagonal element too small");
                    break k_loop;
                }
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum = sum - aMatrix[i][j] * oldXMatrix[j];
                    }
                }
                currentXMatrix[i] = sum / diag;
            }

            double[] vectorDiff = matrixSubtraction(currentXMatrix, oldXMatrix);
            double error = getNorm(vectorDiff);

            if (error < precision) {
                System.out.println("Final Answer:");
                printXArray(currentXMatrix);
                break;
            }

        }
        System.out.println("Max iterations reached!");
    }

    public void partialPivotting(double[][] matrix) {
        System.out.println("Partial Pivot");

        int n = matrix.length;

        double[][] aMatrix = initializeA(matrix);
        double[] bMatrix = initializeB(matrix);
        double[] xMatrix = new double[n];

        for (int k = 0; k < n; k++) {
            print2DArray(matrix);

            int maxCoef = k;
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(aMatrix[maxCoef][k]) < Math.abs(aMatrix[i][k])) {
                    maxCoef = i;
                }
            }
            double[] temp = aMatrix[k];
            aMatrix[k] = aMatrix[maxCoef];
            aMatrix[maxCoef] = temp;

            double t = bMatrix[k];
            bMatrix[k] = bMatrix[maxCoef];
            bMatrix[maxCoef] = t;

            for (int i = k + 1; i < n; i++) {
                double alpha = (aMatrix[i][k] / aMatrix[k][k]);
                bMatrix[i] -= alpha * bMatrix[k];
                for (int j = k; j < n; j++) {
                    aMatrix[i][j] -= alpha * aMatrix[k][j];
                }
            }

            print2DArray(matrix);
        }

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += aMatrix[i][j] * xMatrix[j];
            }
            xMatrix[i] = (bMatrix[i] - sum) / aMatrix[i][i];
        }

        System.out.println();
        printXArray(xMatrix);

    }

    public void print1DArray(double[] a) {
        for (double anA : a) {
            System.out.println(anA);
        }
    }

    public void print2DArray(double[][] a) {
        for (double[] anA : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(anA[j] + " ");
            }
            System.out.println();
        }
    }

    public void printXArray(double[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println("X" + (i + 1) + " = " + x[i]);
        }
    }

    public void seidel(double[][] matrix, double precision) {
        System.out.println("Seidel");

        int n = matrix.length;
        int maxIterations = 100;

        double[][] aMatrix = initializeA(matrix);
        double[] bMatrix = initializeB(matrix);
        double[] currentXMatrix = new double[n]; //x
        double[] oldXMatrix = new double[n]; //y

        double delta = Math.pow(1, -10);
        Arrays.fill(currentXMatrix, 0);

        double diag, sum;

        k_loop:
        for (int k = 0; k < maxIterations; k++) {
            System.arraycopy(currentXMatrix, 0, oldXMatrix, 0, oldXMatrix.length);

            System.out.println();
            System.out.println("k = " + k);

            System.out.println();
            System.out.println("Previous X Matrix");
            printXArray(oldXMatrix);

            System.out.println("Current X Matrix");
            printXArray(currentXMatrix);

            for (int i = 0; i < n; i++) {
                sum = bMatrix[i];
                diag = aMatrix[i][i];

                if (Math.abs(diag) < delta) {
                    System.out.println("Diagonal element too small");
                    break k_loop;
                }
                for (int j = 0; j < n; j++) {
                    if (j < i) {
                        sum = sum - aMatrix[i][j] * currentXMatrix[j];
                    }
                    if (j > i) {
                        sum = sum - aMatrix[i][j] * oldXMatrix[j];
                    }
                }
                currentXMatrix[i] = sum / diag;
            }

            double[] vectorDiff = matrixSubtraction(currentXMatrix, oldXMatrix);
            double error = getNorm(vectorDiff);

            if (error < precision) {
                System.out.println("Final Answer:");
                printXArray(currentXMatrix);
                break;
            }
        }
        System.out.println("Max iterations reached!");
    }

    private double getNorm(double[] a) {
        double sum = 0;

        for (double anA : a) {
            sum += Math.abs(anA);
        }

        return sum;
    }

    private double[][] initializeA(double[][] matrix) {
        double[][] temp = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, matrix.length);
        }
        return temp;
    }

    private double[] initializeB(double[][] matrix) {
        double[] temp = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            temp[i] = matrix[i][matrix.length];
        }
        return temp;
    }

    private double[] matrixSubtraction(double[] a, double[] b) {
        double[] temp = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            temp[i] = a[i] - b[i];
        }
        return temp;
    }
}
