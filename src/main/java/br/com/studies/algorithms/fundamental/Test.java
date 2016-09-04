package br.com.studies.algorithms.fundamental;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		LocalDateTime l = LocalDateTime.of(2015, 5, 10, 11, 22, 33);
		Period p = Period.of(1, 2, 3);
		l.minus(p);
		//DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:MM");
		System.out.println(f.format(l));
		
		String s = "s";
		s += 1;

	}

	interface A {
		default void a() {
		}
	}

	interface B {
		default void a() {
		}
	}

	public void t(int... x) {
		System.out.println("int...");
	}

	public void t(Integer x) {
		System.out.println("integer");
	}

	public static void main3(String[] args) {
		Test t = new Test();
		t.t(1);
	}

	public static void main2(String[] args) {
		/*
		 * LocalDateTime l = LocalDateTime.of(2015, 5, 10, 11, 22,33); Period p
		 * = Period.of(1, 2, 3); l.minus(p); DateTimeFormatter f =
		 * DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		 * System.out.println(f.format(l));
		 */

		List numbersList = Arrays.asList(5, 10, -5, -10);
		Collections.sort(numbersList);
		System.out.println(numbersList);
		int five = Collections.binarySearch(numbersList, 5);
		int four = Collections.binarySearch(numbersList, 4);
		System.out.println(five + four);
		String letters = " Java ";
		System.out.println(letters.replace(" ", ""));

		try {

		} catch (NumberFormatException e) {

		} catch (RuntimeException e) {

		} catch (Exception e) {
		}

		List one = new ArrayList<>();
		one.add(0.5);
		one.add(3.5);
		List two = new ArrayList<>();
		two.add(3.5);
		two.add(0.5);

		System.out.println(one.equals(two));

		int y = 4;
		int x = 10 - ++y / 5;
		System.out.println(x % y);
	}
}