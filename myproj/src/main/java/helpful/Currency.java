package helpful;

public class Currency {// Валюта
	private String name, CharCode, NumCode;

	public Currency(String Name, String CharCode, String NumCode) {
		this.CharCode = CharCode;
		this.NumCode = NumCode;
		this.name = Name;
	}
// how to read xml file in java dom parser mkyoung
	public String getName() {//jsp calls it! not private field!1!!! O.O when 
		return name;
	}
	public String getCharCode() {
		return CharCode;
	}
	public String getNumCode() {
		return NumCode;
	}
}
