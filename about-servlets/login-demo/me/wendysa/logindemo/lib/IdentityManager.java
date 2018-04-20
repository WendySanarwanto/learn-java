package me.wendysa.logindemo.lib;

import java.util.*;

public class IdentityManager {
  // NOTE - Ideally, these storage sit on Database tables , mediated by repository classes.
  private final HashMap<String, String> accounts = new HashMap<String, String>();
  private final HashMap<String, String> authTokens = new HashMap<String, String>();
  
  public void createAccount(String userId, String password) {
    this.accounts.put(userId, password);
  }

  /**
   * A helper method to match entered user id and password against the stored accounts data.
   */
  public boolean doAuthenticate(String userId, String password) {
    String passwordInRepo = this.accounts.get(userId);
    boolean isAuthenticated = password.equals(passwordInRepo);

    if (isAuthenticated) {
      String authToken = createRandomString();
      // Keep auth token on server side's data store
      this.authTokens.put(userId, authToken); 
    }

    return isAuthenticated;
  }

  /**
   * Get auth token by user ID.
   */
  public String getAuthToken(String userId) {
    return this.authTokens.get(userId);
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
        stringBuffer.append(alphabets.charAt(alphaIndex));
      } else {
        int numericIndex = random.nextInt(numerics.length()) - 1;
        System.out.println(String.format("numericIndex = %s", numericIndex));
        stringBuffer.append(numerics.charAt(numericIndex));
      }
    }

    return stringBuffer.toString();
  }
}