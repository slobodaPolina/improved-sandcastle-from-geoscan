package service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieUtils {
	//UNUSED CLASS
	@Autowired
	private PasswordHasher ph;
	public String[] getUserCookies(HttpServletRequest request) throws IOException {
		// Get an array of Cookies associated with this domain...
		Cookie[] cookies = request.getCookies();
		String userName = "", userPass = "", lastLoginDatetime = "";
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userName")) {
					userName = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("userPass")) {
					userPass = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("lastLoginDatetime")) {
					lastLoginDatetime = cookies[i].getValue();
				}
			}
		}
		String[] array = { userName, userPass, lastLoginDatetime };
		return array;
	}

	public void setCookies(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
		int cookieTime = 24 * 60 * 60;// it is the time which cookies are storing in user`s browser
		String userName = request.getParameter("name");
		Cookie cookieUserName = new Cookie("userName", userName);
		// Sets the maximum age in seconds for this Cookie. (24h)
		cookieUserName.setMaxAge(cookieTime);
		response.addCookie(cookieUserName);

		String userPass = request.getParameter("password");
		// pass might be null if i was automatically authorised using cookies, so there
		// was no password in request
		if (userPass != null) {
			userPass = ph.hash(userPass, "MD5");// is it ok that here is no pass?..idk
		}
		Cookie cookieUserPass = new Cookie("userPass", userPass);
		cookieUserPass.setMaxAge(cookieTime);
		response.addCookie(cookieUserPass);

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
		Date now = new Date();
		Cookie cookieLoginDatetime = new Cookie("lastLoginDatetime", df.format(now));
		cookieLoginDatetime.setMaxAge(cookieTime);
		response.addCookie(cookieLoginDatetime);

	}
}