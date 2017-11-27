package helpful;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class CookieUtils {
	public static String[] hasIt(HttpServletRequest request) throws IOException {
		// Get an array of Cookies associated with this domain...
		Cookie[] cookies = request.getCookies();
		String userName = "", userPass = "";
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userName")) {
					userName = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("userPass")) {
					userPass = cookies[i].getValue();
				}
			}
		}
		String[] array = { userName, userPass };
		return array;
	}

	public static void demoUserCookie(HttpServletRequest request, HttpServletResponse response, JspWriter out)
			throws IOException, NoSuchAlgorithmException {
		boolean found = false;

		// Get an array of Cookies associated with this domain...
		// here is small problem: if here is a lot of users on one computer, cookies are
		// incorrect in some way, for ex if i am vasya and i am here for first time, i
		// can notice "hello Masha!" because she hasa used this computer to login
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			String lastLoginDatetime = null;
			String userName = null;
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("lastLoginDatetime")) {
					lastLoginDatetime = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("userName")) {
					userName = cookies[i].getValue();
				}
			}
			if (lastLoginDatetime != null && userName != null && userName.equals(request.getParameter("name"))) {
				out.println(
						"Hello, " + userName + "! Your last login date and time is: " + lastLoginDatetime + "<br/>");
				found = true;
			}
		}

		// if no cookies or no datetime found
		if (!found) {
			out.println("Oh, it seems to me i have no your cookies<br/>");

			String userName = request.getParameter("name");
			Cookie cookieUserName = new Cookie("userName", userName);
			// Sets the maximum age in seconds for this Cookie. (24h)
			cookieUserName.setMaxAge(24 * 60 * 60);
			response.addCookie(cookieUserName);

			// it is a hash
			String userPass = request.getParameter("password1");
			PasswordHasher ph = new PasswordHasher("MD5");
			// pass might be null if i was automatically authorised using cookies, so there
			// was no password in request
			if (userPass != null)
				userPass = ph.hash(userPass);// is it ok that here is no pass?..idk
			Cookie cookieUserPass = new Cookie("userPass", userPass);
			cookieUserPass.setMaxAge(24 * 60 * 60);
			response.addCookie(cookieUserPass);
		}
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date now = new Date();
		Cookie cookieLoginDatetime = new Cookie("lastLoginDatetime", df.format(now));
		// Sets the maximum age in seconds for this Cookie. (24h)
		cookieLoginDatetime.setMaxAge(24 * 60 * 60);
		// Store in the user's computer.
		response.addCookie(cookieLoginDatetime);

	}
}