package br.com.studies.algorithms.fundamental;

public class Transposition {
	public static void main(String[] args) {
		// create N-by-N matrix
		int n = 5;
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = n * i + j;
			}
		}

		// print out initial matrix
		System.out.println("Before");
		System.out.println("------");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%4d", a[i][j]);
			}
			System.out.println();
		}
		
		// transpose in-place
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }

        // print out transposed matrix
        System.out.println();
        System.out.println("After");
        System.out.println("------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }


	}
}
