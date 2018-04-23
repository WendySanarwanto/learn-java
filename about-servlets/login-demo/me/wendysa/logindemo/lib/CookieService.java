package me.wendysa.logindemo.lib;

import java.util.Arrays;
import javax.servlet.http.*;

public class CookieService{
  private HttpServletResponse response;

  public CookieService(HttpServletResponse response) {
    this.response = response;
  }

  /**
   * A helper for storing data into cookie.
   */
  public void keepValueKeyPair(String value, String key, int cookieMaxAge) {
    // Keep Auth token on cookie
    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(cookieMaxAge);
    this.response.addCookie(cookie);
  }

  /**
   * A helper to get cookie in cookies list by specified email.
   */
  public Cookie getCookieByKey(String key, Cookie[] cookies){
    // Return null if key or cookies are null or empty
    if ( (key == null) || (key.equals("")) || 
         (cookies == null) || (cookies.length == 0)) {
      return null;
    }

    // Get cookie by email
    return Arrays.stream(cookies)
                .filter(_cookie -> _cookie.getName().equals(key))
                .findFirst()
                .orElse(null);
  }
}