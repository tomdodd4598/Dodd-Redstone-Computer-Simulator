package drcs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			StringBuilder builder = new StringBuilder();
			builder.append("ERROR: input file not specified!\n");
			builder.append("PARAMETERS: input [params...]\n");
			builder.append("EXAMPLE: code.droc1 45 98\n");
			err(builder.toString());
		}
		try {
			String[] input = readFile(args[0]).trim().split("\\s+");
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
