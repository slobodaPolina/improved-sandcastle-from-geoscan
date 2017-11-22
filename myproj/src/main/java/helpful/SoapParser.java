package helpful;

import java.util.ArrayList;

public class SoapParser {

	private ArrayList<Currency> result = new ArrayList<Currency>();

	SoapParser(String request) {
		String arr[] = request.split("<EnumValutes>");
		// the first line is empty, so lets start from the second one
		for (int i = 1; i < arr.length; i++) {
			String tmpName = "", tmpCharCode = "", tmpNumCode = "";
			tmpName = findValue(arr[i], "VEngname");
			tmpCharCode = findValue(arr[i], "VcharCode");
			tmpNumCode = findValue(arr[i], "VnumCode");
			result.add(new Currency(tmpName, tmpCharCode, tmpNumCode));
		}
		for (int i = 0; i < result.size() - 1; i++) {
			if (result.get(i).getName().equals(result.get(i + 1).getName())) {
				result.remove(i);
				i--;
			}
		}
	}

	public ArrayList<Currency> getresult() {
		return result;
	}

	private String findValue(String line, String border) {
		try {
			String result = "";
			int j = 0;
			while (!line.substring(j, j + 2 + border.length()).equals("<" + border + ">")) {
				j++;
			}
			j = j + 2 + border.length();
			while (line.charAt(j) != '<') {
				result += line.charAt(j);
				j++;
			}
			return delspaces(result);
		} catch (Exception e) {
			return ""; // seems there is no such tag))
		}
	}

	private String delspaces(String s) {
		int i = s.length() - 1;
		while (!alnum(s.charAt(i))) {
			s = s.substring(0, i);
			i--;
		}
		return s;
	}

	private boolean alnum(char i) {
		return (i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i <= '9' && i >= '0');
	}
}
