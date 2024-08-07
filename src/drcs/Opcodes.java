package drcs;

public class Opcodes {
	
	public static final short HLT = 0xFF;
	
	public static final short NOP = 0x00;
	public static final short LDAI = 0x01;
	public static final short NOTI = 0x02;
	public static final short ANDI = 0x03;
	public static final short ORI = 0x04;
	public static final short XORI = 0x05;
	public static final short ADDI = 0x06;
	public static final short SUBI = 0x07;
	public static final short LSHI = 0x08;
	public static final short RSHI = 0x09;
	
	public static final short ADDBI = 0x0A;
	public static final short SUBBI = 0x0B;
	
	public static final short OUT = 0x10;
	public static final short LDALI = 0x11;
	public static final short NOTLI = 0x12;
	public static final short ANDLI = 0x13;
	public static final short ORLI = 0x14;
	public static final short XORLI = 0x15;
	public static final short ADDLI = 0x16;
	public static final short SUBLI = 0x17;
	
	public static final short LDBP = 0x20;
	public static final short LDSP = 0x21;
	public static final short PSHBP = 0x22;
	public static final short POPBP = 0x23;
	public static final short MSPBP = 0x24;
	public static final short ADDSP = 0x25;
	public static final short SUBSP = 0x26;
	
	public static final short DEA = 0x2A;
	public static final short DEB = 0x2B;
	public static final short STATB = 0x2C;
	public static final short STBTA = 0x2D;
	
	public static final short CALL = 0x30;
	public static final short CALLF = 0x31;
	public static final short RET = 0x32;
	
	public static final short PSHA = 0x3A;
	public static final short POPA = 0x3B;
	
	public static final short STA = 0x40;
	public static final short LDA = 0x41;
	public static final short NOT = 0x42;
	public static final short AND = 0x43;
	public static final short OR = 0x44;
	public static final short XOR = 0x45;
	public static final short ADD = 0x46;
	public static final short SUB = 0x47;
	public static final short LSH = 0x48;
	public static final short RSH = 0x49;
	
	public static final short STB = 0x4A;
	public static final short LDB = 0x4B;
	
	public static final short LDEZ = 0x51;
	public static final short LDNEZ = 0x52;
	public static final short LDLZ = 0x53;
	public static final short LDLEZ = 0x54;
	public static final short LDMZ = 0x55;
	public static final short LDMEZ = 0x56;
	
	public static final short LDNOT = 0x5A;
	public static final short LDNEG = 0x5B;
	
	public static final short JMP = 0x70;
	public static final short JEZ = 0x71;
	public static final short JNEZ = 0x72;
	public static final short JLZ = 0x73;
	public static final short JLEZ = 0x74;
	public static final short JMZ = 0x75;
	public static final short JMEZ = 0x76;
	
	public static final short JMPF = 0x77;
	public static final short JEZF = 0x78;
	public static final short JNEZF = 0x79;
	public static final short JLZF = 0x7A;
	public static final short JLEZF = 0x7B;
	public static final short JMZF = 0x7C;
	public static final short JMEZF = 0x7D;
	
	public static final short STAPB = 0x80;
	public static final short LDAPB = 0x81;
	public static final short NOTPB = 0x82;
	public static final short ANDPB = 0x83;
	public static final short ORPB = 0x84;
	public static final short XORPB = 0x85;
	public static final short ADDPB = 0x86;
	public static final short SUBPB = 0x87;
	public static final short LSHPB = 0x88;
	public static final short RSHPB = 0x89;
	
	public static final short STBPB = 0x8A;
	public static final short LDBPB = 0x8B;
	public static final short LDIPB = 0x8C;
	public static final short ADDIPB = 0x8D;
	public static final short SUBIPB = 0x8E;
	
	public static final short STANB = 0xA0;
	public static final short LDANB = 0xA1;
	public static final short NOTNB = 0xA2;
	public static final short ANDNB = 0xA3;
	public static final short ORNB = 0xA4;
	public static final short XORNB = 0xA5;
	public static final short ADDNB = 0xA6;
	public static final short SUBNB = 0xA7;
	public static final short LSHNB = 0xA8;
	public static final short RSHNB = 0xA9;
	
	public static final short STBNB = 0xAA;
	public static final short LDBNB = 0xAB;
	public static final short LDINB = 0xAC;
	public static final short ADDINB = 0xAD;
	public static final short SUBINB = 0xAE;
	
	public static final short STAL = 0xB0;
	public static final short LDAL = 0xB1;
	public static final short NOTL = 0xB2;
	public static final short ANDL = 0xB3;
	public static final short ORL = 0xB4;
	public static final short XORL = 0xB5;
	public static final short ADDL = 0xB6;
	public static final short SUBL = 0xB7;
	public static final short LSHL = 0xB8;
	public static final short RSHL = 0xB9;
	
	public static final short STBL = 0xBA;
	public static final short LDBL = 0xBB;
	
	public static final short MULI = 0xC0;
	public static final short MULLI = 0xC1;
	public static final short MUL = 0xC2;
	public static final short MULPB = 0xC3;
	public static final short MULNB = 0xC4;
	public static final short MULL = 0xC5;
	
	public static final short DIVI = 0xD0;
	public static final short DIVLI = 0xD1;
	public static final short DIV = 0xD2;
	public static final short DIVPB = 0xD3;
	public static final short DIVNB = 0xD4;
	public static final short DIVL = 0xD5;
	
	public static final short REMI = 0xE0;
	public static final short REMLI = 0xE1;
	public static final short REM = 0xE2;
	public static final short REMPB = 0xE3;
	public static final short REMNB = 0xE4;
	public static final short REML = 0xE5;
}
