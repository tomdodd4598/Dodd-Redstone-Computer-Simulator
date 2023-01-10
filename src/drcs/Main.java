package drcs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;

public class Main {
	
	public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		if (args.length == 0) {
			StringBuilder builder = new StringBuilder();
			builder.append("ERROR: input file not specified!\n");
			builder.append("PARAMETERS: [\"-s\"] input [params...]\n");
			builder.append("INFO: optionally run in single-step debug mode with \"-s\"\n");
			builder.append("INFO: optionally specify parameters after the input file name\n");
			builder.append("EXAMPLE: program.droc1 45 98\n");
			err(builder.toString());
		}
		try {
			boolean debug = trim(args[0]).equals("s");
			if (debug) {
				System.out.println("INFO: Single-step debug mode was enabled!");
			}
			String[] input = readFile(args[debug ? 1 : 0]).trim().split("\\s+");
			new Computer(input, Arrays.copyOfRange(args, debug ? 2 : 1, args.length), debug).run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Thanks to erickson on stackoverflow for the original! https://stackoverflow.com/a/326440 */
	private static String readFile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
	}
	
	private static String trim(String arg) {
		return Helpers.lowerCase(arg.replaceAll("-|_", ""));
	}
	
	private static void err(String string) {
		System.err.print(string);
		System.exit(1);
	}
}
