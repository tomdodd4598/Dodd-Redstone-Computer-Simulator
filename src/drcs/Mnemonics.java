package drcs;

import static drcs.Opcodes.*;

import java.util.*;

public class Mnemonics {
	
	private static final Map<Short, String> MAP = new HashMap<>();
	
	static {
		put(HLT, "HLT");
		
		put(NOP, "NOP");
		put(LDAI, "LDAI");
		put(NOTI, "NOTI");
		put(ANDI, "ANDI");
		put(ORI, "ORI");
		put(XORI, "XORI");
		put(ADDI, "ADDI");
		put(SUBI, "SUBI");
		put(LSHI, "LSHI");
		put(RSHI, "RSHI");
		
		put(ADDBI, "ADDBI");
		put(SUBBI, "SUBBI");
		
		put(OUT, "OUT");
		put(LDALI, "LDALI");
		put(NOTLI, "NOTLI");
		put(ANDLI, "ANDLI");
		put(ORLI, "ORLI");
		put(XORLI, "XORLI");
		put(ADDLI, "ADDLI");
		put(SUBLI, "SUBLI");
		
		put(LDBP, "LDBP");
		put(LDSP, "LDSP");
		put(PSHBP, "PSHBP");
		put(POPBP, "POPBP");
		put(MSPBP, "MSPBP");
		put(ADDSP, "ADDSP");
		put(SUBSP, "SUBSP");
		
		put(DEA, "DEA");
		put(DEB, "DEB");
		put(STATB, "STATB");
		put(STBTA, "STBTA");
		
		put(CALL, "CALL");
		put(CALLF, "CALLF");
		put(RET, "RET");
		
		put(PSHA, "PSHA");
		put(POPA, "POPA");
		
		put(STA, "STA");
		put(LDA, "LDA");
		put(NOT, "NOT");
		put(AND, "AND");
		put(OR, "OR");
		put(XOR, "XOR");
		put(ADD, "ADD");
		put(SUB, "SUB");
		put(LSH, "LSH");
		put(RSH, "RSH");
		
		put(STB, "STB");
		put(LDB, "LDB");
		
		put(LDEZ, "LDEZ");
		put(LDNEZ, "LDNEZ");
		put(LDLZ, "LDLZ");
		put(LDLEZ, "LDLEZ");
		put(LDMZ, "LDMZ");
		put(LDMEZ, "LDMEZ");
		
		put(LDNOT, "LDNOT");
		put(LDNEG, "LDNEG");
		
		put(JMP, "JMP");
		put(JEZ, "JEZ");
		put(JNEZ, "JNEZ");
		put(JLZ, "JLZ");
		put(JLEZ, "JLEZ");
		put(JMZ, "JMZ");
		put(JMEZ, "JMEZ");
		
		put(JMPF, "JMPF");
		put(JEZF, "JEZF");
		put(JNEZF, "JNEZF");
		put(JLZF, "JLZF");
		put(JLEZF, "JLEZF");
		put(JMZF, "JMZF");
		put(JMEZF, "JMEZF");
		
		put(STAPB, "STAPB");
		put(LDAPB, "LDAPB");
		put(NOTPB, "NOTPB");
		put(ANDPB, "ANDPB");
		put(ORPB, "ORPB");
		put(XORPB, "XORPB");
		put(ADDPB, "ADDPB");
		put(SUBPB, "SUBPB");
		put(LSHPB, "LSHPB");
		put(RSHPB, "RSHPB");
		
		put(STBPB, "STBPB");
		put(LDBPB, "LDBPB");
		put(LDIPB, "LDIPB");
		put(ADDIPB, "ADDIPB");
		put(SUBIPB, "SUBIPB");
		
		put(STANB, "STANB");
		put(LDANB, "LDANB");
		put(NOTNB, "NOTNB");
		put(ANDNB, "ANDNB");
		put(ORNB, "ORNB");
		put(XORNB, "XORNB");
		put(ADDNB, "ADDNB");
		put(SUBNB, "SUBNB");
		put(LSHNB, "LSHNB");
		put(RSHNB, "RSHNB");
		
		put(STBNB, "STBNB");
		put(LDBNB, "LDBNB");
		put(LDINB, "LDINB");
		put(ADDINB, "ADDINB");
		put(SUBINB, "SUBINB");
		
		put(STAL, "STAL");
		put(LDAL, "LDAL");
		put(NOTL, "NOTL");
		put(ANDL, "ANDL");
		put(ORL, "ORL");
		put(XORL, "XORL");
		put(ADDL, "ADDL");
		put(SUBL, "SUBL");
		put(LSHL, "LSHL");
		put(RSHL, "RSHL");
		
		put(STBL, "STBL");
		put(LDBL, "LDBL");
		
		put(MULI, "MULI");
		put(MULLI, "MULLI");
		put(MUL, "MUL");
		put(MULPB, "MULPB");
		put(MULNB, "MULNB");
		put(MULL, "MULL");
		
		put(DIVI, "DIVI");
		put(DIVLI, "DIVLI");
		put(DIV, "DIV");
		put(DIVPB, "DIVPB");
		put(DIVNB, "DIVNB");
		put(DIVL, "DIVL");
		
		put(REMI, "REMI");
		put(REMLI, "REMLI");
		put(REM, "REM");
		put(REMPB, "REMPB");
		put(REMNB, "REMNB");
		put(REML, "REML");
	}
	
	private static void put(short opcode, String mnemonic) {
		MAP.put(opcode, mnemonic);
	}
	
	public static String get(short opcode) {
		return MAP.get(opcode);
	}
}
