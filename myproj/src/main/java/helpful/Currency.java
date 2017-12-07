package helpful;

public class Currency {// Валюта
	private String engName, rusName, charCode, numCode;

	public Currency(String engName, String rusName, String charCode, String numCode) {
		this.charCode = charCode;
		this.numCode = numCode;
		this.engName = engName;
		this.rusName = rusName;
	}

	// how to read xml file in java dom parser mkyoung
	public String getEngName() {// jsp calls it! not private field!1!!! O.O
		return engName;
	}

	public String getRusName() {
		return rusName;
	}

	public String getCharCode() {
		return charCode;
	}

	public String getNumCode() {
		return numCode;
	}
}
