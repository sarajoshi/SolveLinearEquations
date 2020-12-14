import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class getMatrixFromFile {
   @SuppressWarnings("resource")
   public double[][] getMatrix() throws Exception {
      Scanner sc = new Scanner(new BufferedReader(new FileReader("/Users/SaraJoshi/Documents/workspace/SolveLinearEquations/linearEquationFile")));
      int rows = 4;
      int columns = 4;
      double [][] myArray = new double[rows][columns];
      while(sc.hasNextLine()) {
         for (int i=0; i<myArray.length; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            for (int j=0; j<line.length; j++) {
               myArray[i][j] = Integer.parseInt(line[j]);
            }
         }
      }
      System.out.println(Arrays.deepToString(myArray));
	return myArray;
   }
}

//import java.io.File;
//import java.util.Scanner;
//
//public class getMatrixFromFile {
//	private double[] [] coef;
//	private Scanner sc;
//	
//	public getMatrixFromFile(int n) {
//		coef = new double[n] [n + 1];
//		sc = null;
//	}
//	
//	public getMatrixFromFile(double[] [] newCoef) {
//		coef = newCoef;
//		sc = null;
//	}
//	
//	public void copyToCoef(int[] [] newCoef) {
//		for (int i = 0; i < newCoef.length; i++) {
//			for (int j = 0; j < newCoef[0].length;j++) {
//				coef[i][j] = newCoef[i][j];
//			}
//		}
//	}
//	
//	public double[] [] getCoefficientsFromFile(String fileName) {
//		File file = new File(fileName);
//		sc = null;
//		int n = coef.length;
//		
//		int k = 0, l = 0;
//		
//		try {
//			sc = new Scanner(file);
//			
//			while (sc.hasNextLine()) {
//				String line = sc.nextLine(); 
//				String[] lineChars = line.split("");
//				for (int i = 0; i < lineChars.length; i++) {
//					try {
//						double num;
//						if (i > 0 && Character.isLetter(lineChars[i].charAt(0))) {
//							switch (lineChars[i - 1]) {
//							
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//}
