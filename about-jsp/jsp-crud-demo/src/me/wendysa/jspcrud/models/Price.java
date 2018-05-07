package me.wendysa.jspcrud.models;

public class Price {
  public int amount;
  public String currency;

  /**
   * @return the amount to get
   */
  public int getAmount() {
    return amount;
  }

  /**
   * @param the amount to set
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * @return the currency to get
   */
  public String getCurrency() {
    return currency;
  }

  /**
   * @param name the currency to set
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return String.format("%s %s", this.amount, this.currency);
  }
};
