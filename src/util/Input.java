package util;

import java.util.Scanner;

public class Input {

	public static Scanner input = new Scanner(System.in);

	public static String inputString(String string) {

		String texto = "";

		do {
			System.out.println(string);
			texto = input.nextLine().trim();
		} while (texto.length() == 0);
		return texto;
	}

	public static float inputFloat(String string) {

		boolean comprobar;
		Float f = 0f;

		do {
			comprobar = false;
			System.out.println(string);
			try {
				f = Float.parseFloat(input.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("\nError al introducir los datos");
				comprobar = true;
			}
		} while (comprobar);

		return f;
	}

	public static byte inputByte() {

		boolean comprobar;
		byte b = 0;

		do {
			comprobar = false;
			try {
				b = Byte.parseByte(input.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("\nError al introducir los datos");
				comprobar = true;
			}
		} while (comprobar);

		return b;
	}
}
