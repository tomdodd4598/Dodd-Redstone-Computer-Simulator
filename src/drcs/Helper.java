package drcs;

public class Helper {
	
	public static short low(short value) {
		return (short) (value & 0xFF);
	}
	
	public static short high(short value) {
		return (short) (((short) (value >> 8)) & 0xFF);
	}
	
	public static short nybble(short value) {
		return (short) (value & 0xF);
	}
	
	public static short inv(short value) {
		return (short) ~value;
	}
	
	public static short mod(short a, short b) {
		return (short) ((a % b + b) % b);
	}
	
	public static short bool(boolean bool) {
		return (short) (bool ? 1 : 0);
	}
}
