package dev.gourab.ocp.ch01;

import java.util.Random;
import java.util.stream.IntStream;

public class Animal {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		for (var arg : args)
			System.out.print(arg + ", ");
		System.out.println();

		var randomIns = new Random();
		IntStream.range(0, 100).forEach(__ -> System.out.print(randomIns.nextInt(45) + ","));
		System.out.println();
	}
}

class Animal2 {
}
