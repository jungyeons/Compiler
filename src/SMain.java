import java.util.Scanner;

public class SMain {
	private SLex lex;
	private SParser parser;
	
	private Scanner scanner;

	public SMain() {
		lex = new SLex();
		parser = new SParser();
		parser.associate(lex); // 진짜 파일을 읽는 애는 lex임
	}
	
	public void initialize() {
		scanner = new Scanner("source/exe1.txt");
	}
	
	public void finaliize() {
		scanner.close();
	}
	
	private void run() {
		parser.parse(scanner);
	} // 열려 있는 파일로 parsing해라

	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finaliize();
	}
}