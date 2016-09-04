package br.com.studies.algorithms.part1;

public class MatrixMultiplication {

	public static void main(String[] args) {
		int[][] mx1 = { { 1, 2, 3 }, { 3, 4, 3 } , { 3, 4, 3 }};
		int[][] mx2 = { { 2, 0, 3 }, { 1, 2, 2 } , { 3, 4, 3 }};

		int[][] rs = multiply(mx1, mx2);

		for (int i = 0; i < rs.length; i++) {
			for (int j = 0; j < rs[i].length; j++) {
				System.out.print(rs[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static int[][] multiply(int[][] mx1, int[][] mx2) {
		int[][] rs = new int[mx1.length][mx2[0].length];
		int col = 0;
		for (int i = 0; i < mx1.length; i++) {
			for (int j = 0; j < mx1.length; j++) {
				int sum = 0;
				for (int z = 0; z < mx1[i].length; z++) {
					sum += mx1[i][z] * mx2[z][col];
				}
				rs[i][col] = sum;
				col++;
			}
			col = 0;
		}

		return rs;
	}

}
