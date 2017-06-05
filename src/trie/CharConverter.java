package trie;

public class CharConverter {

	public static int toInt(int c) {
		if (c == 32)
		{
			return 26;
		}
		if (c == 39)
		{
			return 27;
		}
		return c - 97;
//		switch (c)
//		{
//		case 'a':
//			return 0;
//		case 'b':
//			return 1;
//		case 'c':
//			return 2;
//		case 'd':
//			return 3;
//		case 'e':
//			return 4;
//		case 'f':
//			return 5;
//		case 'g':
//			return 6;
//		case 'h':
//			return 7;
//		case 'i':
//		case 'j':
//		case 'k':
//		case 'l':
//		case 'm':
//		case 'n':
//		case 'o':
//		case 'p':
//		case 'q':
//		case 'r':
//		case 's':
//		case 't':
//		case 'u':
//		case 'v':
//		case 'w':
//		case 'x':
//		case 'y':
//		case 'z':
//		case ' ':
//		}
//		return 0;
	}

	public static char toCharictor(int i) {
		if (i == 26)
		{
			return ' ';
		}
		if (i == 27)
		{
			return '\'';
		}
		
		return (char)(i + 97);
	}

}
