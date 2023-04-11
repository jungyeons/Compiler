import java.util.Scanner;
import java.util.Vector;

public class SParser {
	private SLex lex;

	public void associate(SLex lex) {
		this.lex = lex;
	}

	public void parse(Scanner scanner) {
		Program program = new Program();
		program.parse();
	}

	interface INode {
		public abstract String parse();
	}

	private class Program implements INode {
		private Header header;
		private CodeSegment codeSegment;

		public Program() {
			this.header = new Header();
			this.codeSegment = new CodeSegment();

		}

		@Override
		public String parse() {
			String token = lex.getToken();
			if (token.compareTo(".header") == 0) {
				token = this.header.parse();

			}
			if (token.compareTo(".code") == 0) {
				token = this.codeSegment.parse();
			}
			return null;

		}
	}

	// 문법적구조로 분해해서 붙여라
	private class Header implements INode {
		private class Declaration {

			private String variableName;
			private String size;

			public String getVariableName() {
				return variableName;
			}

			public void setVariableName(String variableName) {
				this.variableName = variableName;
			}

			public String getSize() {
				return size;
			}

			public void setSize(String size) {
				this.size = size;
			}

		}

		private Vector<Declaration> declarations;

		public Header() {
			this.declarations = new Vector<Declaration>();

		}

		@Override
		public String parse() {
			String token = lex.getToken();
			while (token.compareTo(".Code") != 0) {

				Declaration declaration = new Declaration();
				declaration.setVariableName(token);
				declaration.setSize(lex.getToken());
				this.declarations.add(declaration);
				token = lex.getToken();
			}
			return token;
		}

	}

	private class CodeSegment implements INode {
		private Vector<Statement> statements;

		public CodeSegment() {
			this.statements = new Vector<Statement>();
		}

		@Override
		public String parse() {
			Statement statement = new Statement();
			String token = statement.parse();

			while (token.compareTo(".end") != 0) {

				
				this.statements.add(statement);
			statement = new Statement();
				token = statement.parse();
			}
			return token;
		}

	}

	private class Statement implements INode {
		private String operator;
		private String operand1;
		private String operand2;

		@Override
		public String parse() {
			String[] tokens = lex.getTokens();
				operator = tokens[0];

			if (tokens.length == 2) {
				operand1 = tokens[1];

			}if (tokens.length == 3) {
				operand1 = tokens[1];
				operand2 = tokens[2];

			}
			return operator;
		}

	}
private class Operand {
	boolean bAddress;
	boolean bRegister;
}

}
