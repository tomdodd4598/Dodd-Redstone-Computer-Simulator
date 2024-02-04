package drcs;

import static drcs.Helpers.*;
import static drcs.Main.READER;
import static drcs.Mnemonics.get;
import static drcs.Opcodes.*;

import java.io.IOException;
import java.util.*;

public class Computer {
	
	private static final short MEMORY_SIZE = 256;
	private static final short HEX_LENGTH = 2;
	
	private final Map<Short, Short> memory = new HashMap<>();
	
	private final boolean debug;
	
	private short a = 0, b = 0, bp = 0, sp = 0, pc = 0;
	
	public Computer(String[] code, String[] args, boolean debug) {
		for (short i = 0; i < MEMORY_SIZE; ++i) {
			if (i < code.length) {
				memory.put(i, (short) Integer.parseInt(code[i]));
			}
			else if (i >= MEMORY_SIZE - args.length) {
				memory.put(i, (short) Integer.parseInt(args[MEMORY_SIZE - i - 1]));
			}
			else {
				memory.put(i, (short) 0);
			}
		}
		this.debug = debug;
	}
	
	private short read(int address) {
		return memory.get(mod((short) address, MEMORY_SIZE));
	}
	
	private void write(int address, short data) {
		memory.put(mod((short) address, MEMORY_SIZE), data);
	}
	
	private short next() {
		return read(pc++);
	}
	
	private void push(short data) {
		write(--sp, data);
	}
	
	private short pop() {
		return read(sp++);
	}
	
	private void jump(short address, boolean condition) {
		if (condition) {
			pc = address;
		}
	}
	
	private void out() {
		System.out.println(a);
	}
	
	public void run() {
		short prevpc, data, instruction, argument;
		while (true) {
			prevpc = pc;
			data = next();
			instruction = high(data);
			argument = low(data);
			
			switch (instruction) {
				case HLT:
					System.out.println("HALT " + a);
					return;
				
				case NOP:
					break;
				case LDAI:
					a = argument;
					break;
				case NOTI:
					a = inv(argument);
					break;
				case ANDI:
					a &= argument;
					break;
				case ORI:
					a |= argument;
					break;
				case XORI:
					a ^= argument;
					break;
				case ADDI:
					a += argument;
					break;
				case SUBI:
					a -= argument;
					break;
				case LSHI:
					a <<= nybble(argument);
					break;
				case RSHI:
					a >>= nybble(argument);
					break;
				
				case ADDBI:
					b += argument;
					break;
				case SUBBI:
					b -= argument;
					break;
				
				case OUT:
					out();
					break;
				case LDALI:
					a = next();
					break;
				case NOTLI:
					a = inv(next());
					break;
				case ANDLI:
					a &= next();
					break;
				case ORLI:
					a |= next();
					break;
				case XORLI:
					a ^= next();
					break;
				case ADDLI:
					a += next();
					break;
				case SUBLI:
					a -= next();
					break;
				
				case LDBP:
					bp = argument;
					break;
				case LDSP:
					sp = argument;
					break;
				case PSHBP:
					push(bp);
					break;
				case POPBP:
					bp = pop();
					break;
				case MSPBP:
					bp = sp;
					break;
				case ADDSP:
					sp += argument;
					break;
				case SUBSP:
					sp -= argument;
					break;
				
				case DEA:
					a = read(a);
					break;
				case DEB:
					b = read(b);
					break;
				case STATB:
					write(b, a);
					break;
				case STBTA:
					write(a, b);
					break;
				
				case CALL:
					push(argument);
					jump(a, true);
					break;
				case RET:
					jump(pop(), true);
					break;
				
				case PSHA:
					push(a);
					break;
				case POPA:
					a = pop();
					break;
				
				case STA:
					write(argument, a);
					break;
				case LDA:
					a = read(argument);
					break;
				case NOT:
					a = inv(read(argument));
					break;
				case AND:
					a &= read(argument);
					break;
				case OR:
					a |= read(argument);
					break;
				case XOR:
					a ^= read(argument);
					break;
				case ADD:
					a += read(argument);
					break;
				case SUB:
					a -= read(argument);
					break;
				case LSH:
					a <<= nybble(read(argument));
					break;
				case RSH:
					a >>= nybble(read(argument));
					break;
				
				case STB:
					write(argument, b);
					break;
				case LDB:
					b = read(argument);
					break;
				
				case LDEZ:
					a = bool(a == 0);
					break;
				case LDNEZ:
					a = bool(a != 0);
					break;
				case LDLZ:
					a = bool(a < 0);
					break;
				case LDLEZ:
					a = bool(a <= 0);
					break;
				case LDMZ:
					a = bool(a > 0);
					break;
				case LDMEZ:
					a = bool(a >= 0);
					break;
				
				case LDNOT:
					a = inv(a);
					break;
				case LDNEG:
					a = (short) -a;
					break;
				
				case JMP:
					jump(argument, true);
					break;
				case JEZ:
					jump(argument, a == 0);
					break;
				case JNEZ:
					jump(argument, a != 0);
					break;
				case JLZ:
					jump(argument, a < 0);
					break;
				case JLEZ:
					jump(argument, a <= 0);
					break;
				case JMZ:
					jump(argument, a > 0);
					break;
				case JMEZ:
					jump(argument, a >= 0);
					break;
				
				case STAPB:
					write(bp + argument, a);
					break;
				case LDAPB:
					a = read(bp + argument);
					break;
				case NOTPB:
					a = inv(read(bp + argument));
					break;
				case ANDPB:
					a &= read(bp + argument);
					break;
				case ORPB:
					a |= read(bp + argument);
					break;
				case XORPB:
					a ^= read(bp + argument);
					break;
				case ADDPB:
					a += read(bp + argument);
					break;
				case SUBPB:
					a -= read(bp + argument);
					break;
				case LSHPB:
					a <<= nybble(read(bp + argument));
					break;
				case RSHPB:
					a >>= nybble(read(bp + argument));
					break;
				
				case STBPB:
					write(bp + argument, b);
					break;
				case LDBPB:
					b = read(bp + argument);
					break;
				case LDIPB:
					a = (short) (bp + argument);
					break;
				
				case STANB:
					write(bp - argument, a);
					break;
				case LDANB:
					a = read(bp - argument);
					break;
				case NOTNB:
					a = inv(read(bp - argument));
					break;
				case ANDNB:
					a &= read(bp - argument);
					break;
				case ORNB:
					a |= read(bp - argument);
					break;
				case XORNB:
					a ^= read(bp - argument);
					break;
				case ADDNB:
					a += read(bp - argument);
					break;
				case SUBNB:
					a -= read(bp - argument);
					break;
				case LSHNB:
					a <<= nybble(read(bp - argument));
					break;
				case RSHNB:
					a >>= nybble(read(bp - argument));
					break;
				
				case STBNB:
					write(bp - argument, b);
					break;
				case LDBNB:
					b = read(bp - argument);
					break;
				case LDINB:
					a = (short) (bp - argument);
					break;
				
				case MULI:
					a *= argument;
					break;
				case MULLI:
					a *= next();
					break;
				case MUL:
					a *= read(argument);
					break;
				case MULPB:
					a *= read(bp + argument);
					break;
				case MULNB:
					a *= read(bp - argument);
					break;
				
				case DIVI:
					a /= argument;
					break;
				case DIVLI:
					a /= next();
					break;
				case DIV:
					a /= read(argument);
					break;
				case DIVPB:
					a /= read(bp + argument);
					break;
				case DIVNB:
					a /= read(bp - argument);
					break;
				
				case REMI:
					a %= argument;
					break;
				case REMLI:
					a %= next();
					break;
				case REM:
					a %= read(argument);
					break;
				case REMPB:
					a %= read(bp + argument);
					break;
				case REMNB:
					a %= read(bp - argument);
					break;
				
				default:
					break;
			}
			
			if (debug) {
				try {
					if (READER.readLine() != null) {
						System.out.println(hex(prevpc) + "\t" + get(instruction) + "\t" + hex(argument) + "\nA:\tB:\tBP:\tSP:\tPC:\n" + a + "\t" + b + "\t" + bp + "\t" + sp + "\t" + pc);
					}
				}
				catch (IOException e) {}
			}
		}
	}
	
	private static String hex(short value) {
		return Helpers.hex(value, HEX_LENGTH);
	}
}
