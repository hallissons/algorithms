package br.com.studies.algorithms.week1;

/**
 *    Java Program to Implement Karatsuba Multiplication Algorithm
 **/
import java.util.Scanner;

public class Karatsuba {

	public long multiply(long i, long j) {
		if (i < 10 || j < 10) {
			return i * j;
		}

		double n = Math.round(getCount(i));

		if (n % 2 == 1) {
			n++;
		}

		double m2 = Math.pow(10, Math.round(n / 2));

		long a = (long) (i / m2);
		long b = (long) (i % m2);
		long c = (long) (j / m2);
		long d = (long) (j % m2);

		long first = multiply(a, c);
		long second = multiply(b, d);
		long third = multiply(a + b, c + d);
		long fourth = third - first - second;

		return ((long) ((Math.pow(10, n) * first) + (fourth * m2) + second));
	}

	/** Main function **/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Karatsuba Multiplication Algorithm Test\n");
		/** Make an object of Karatsuba class **/
		Karatsuba kts = new Karatsuba();

		/** Accept two integers **/
		System.out.println("Enter two integer numbers\n");
		long n1 = scan.nextLong();
		long n2 = scan.nextLong();
		/** Call function multiply of class Karatsuba **/
		
		long product = kts.multiply(n1, n2);

		scan.close();
	}

	private static double getCount(long i) {
		String totalN = Long.toString(i);
		return totalN.length();
	}
}