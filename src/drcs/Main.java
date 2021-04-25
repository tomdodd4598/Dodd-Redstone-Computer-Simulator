package drcs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			err("ERROR: input file not specified!");
		}
		try {
			String[] input = readFile(args[0]).split("\\s+");
			new Computer(input, Arrays.copyOfRange(args, 1, args.length)).run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Thanks to erickson on stackoverflow for the original! https://stackoverflow.com/a/326440 */
	private static String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
	}
	
	private static void err(String string) {
		System.err.print(string);
		System.exit(1);
	}
}
