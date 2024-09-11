package drcs;

import static drcs.Helpers.*;
import static drcs.Main.READER;
import static drcs.Mnemonics.get;
import static drcs.Opcodes.*;

import java.io.IOException;

public class Computer {
	
	private static final int MEMORY_SIZE = 65536;
	private static final int HEX_LENGTH = 4;
	
	private final short[] memory = new short[MEMORY_SIZE];
	
	private final boolean debug;
	
	private short a = 0, b = 0, bp = 0, sp = 0, pc = 0;
	
	private boolean longInstruction = false;
	
	public Computer(String[] code, String[] args, boolean debug) {
		for (int i = 0; i < code.length; ++i) {
			memory[i] = (short) Integer.parseInt(code[i]);
		}
		
		for (int i = 0; i < args.length; ++i) {
			memory[MEMORY_SIZE - i - 1] = (short) Integer.parseInt(args[i]);
		}
		
		this.debug = debug;
	}
	
	private short read(int address) {
		return memory[mod((short) address, MEMORY_SIZE)];
	}
	
	private void write(int address, short data) {
		memory[mod((short) address, MEMORY_SIZE)] = data;
	}
	
	private short next() {
		longInstruction = true;
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
		System.out.print((char) a);
	}
	
	public void run() {
		short prevpc, data, opcode, argument;
		while (true) {
			prevpc = pc;
			data = next();
			opcode = high(data);
			argument = low(data);
			
			longInstruction = false;
			
			switch (opcode) {
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
				case CALLF:
					push(next());
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
				
				case JMPL:
					jump(next(), true);
					break;
				case JEZL:
					jump(next(), a == 0);
					break;
				case JNEZL:
					jump(next(), a != 0);
					break;
				case JLZL:
					jump(next(), a < 0);
					break;
				case JLEZL:
					jump(next(), a <= 0);
					break;
				case JMZL:
					jump(next(), a > 0);
					break;
				case JMEZL:
					jump(next(), a >= 0);
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
				case ADDIPB:
					a += (short) (bp + argument);
					break;
				case SUBIPB:
					a -= (short) (bp + argument);
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
				case ADDINB:
					a += (short) (bp - argument);
					break;
				case SUBINB:
					a -= (short) (bp - argument);
					break;
				
				case STAL:
					write(next(), a);
					break;
				case LDAL:
					a = read(next());
					break;
				case NOTL:
					a = inv(read(next()));
					break;
				case ANDL:
					a &= read(next());
					break;
				case ORL:
					a |= read(next());
					break;
				case XORL:
					a ^= read(next());
					break;
				case ADDL:
					a += read(next());
					break;
				case SUBL:
					a -= read(next());
					break;
				case LSHL:
					a <<= nybble(read(next()));
					break;
				case RSHL:
					a >>= nybble(read(next()));
					break;
				
				case STBL:
					write(next(), b);
					break;
				case LDBL:
					b = read(next());
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
				case MULL:
					a *= read(next());
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
				case DIVL:
					a /= read(next());
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
				case REML:
					a %= read(next());
					break;
				
				default:
					break;
			}
			
			if (debug) {
				try {
					if (READER.readLine() != null) {
						System.out.println(hex(prevpc, HEX_LENGTH) + "\t" + get(opcode) + "\t" + hex(longInstruction ? read(prevpc + 1) : argument, HEX_LENGTH) + "\na:\tb:\tbp:\tsp:\tpc:\n" + a + "\t" + b + "\t" + bp + "\t" + sp + "\t" + pc);
					}
				}
				catch (IOException e) {}
			}
		}
	}
}
