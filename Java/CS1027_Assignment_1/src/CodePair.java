
public class CodePair {
	private char character;
	private String compCode;
	
	public CodePair(char c, String code) {
		this.character = c;
		this.compCode = code;
	}
	
	public String getCode() {
		return compCode;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public void setCharacter(char c) {
		this.character = c;
	}
	
	public void setCode(String code) {
		this.compCode = code;
	}
	
	public boolean equals(CodePair anotherPair) { // need to get this working
		if (this.getCharacter() == anotherPair.getCharacter()){
			return true;
		}else {
			return false;
		}
	}
}
