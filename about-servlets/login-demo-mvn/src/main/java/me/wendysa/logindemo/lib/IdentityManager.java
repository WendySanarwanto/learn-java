package me.wendysa.logindemo.lib;

import java.util.*;

public class IdentityManager {
  // NOTE - Ideally, these storage sit on Database tables , mediated by repository classes.
  private final HashMap<String, String> accounts = new HashMap<String, String>();
  
  public void createAccount(String userId, String password) {
    this.accounts.put(userId, password);
  }

  /**
   * A helper method to match entered user id and password against the stored accounts data.
   */
  public String doAuthenticate(String userId, String password) {
    String passwordInRepo = this.accounts.get(userId);
    boolean isAuthenticated = password.equals(passwordInRepo);

    return isAuthenticated ? createRandomString() : null;
  }

  /**
   * A helper method to create a random string
   */
  private String createRandomString() {
    String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwyz";
    String numerics = "1234567890";
    StringBuffer stringBuffer = new StringBuffer();

    for(int i=0; i < 15; i++) {
      // Random a number within range 1-2 to determine should pick alphabets or numeric
      Random random = new Random();
      int randomNumber = random.nextInt(2);

      // If 1st random number is 1, random a number within range alphabets.length
      if (randomNumber == 1) {
        int alphaIndex = random.nextInt(alphabets.length()) - 1;
        if (alphaIndex <= 1) {
          alphaIndex = 0;
        }
        stringBuffer.append(alphabets.charAt(alphaIndex));
      } else {
        int numericIndex = random.nextInt(numerics.length());
        if (numericIndex <= 1) {
          numericIndex = 0;
        }
        stringBuffer.append(numerics.charAt(numericIndex));
      }
    }

    return stringBuffer.toString();
  }
}