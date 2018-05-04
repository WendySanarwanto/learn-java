package me.wendysa.jspcrud;

public class Price {
  public int amount;
  public String currency;

  @Override
  public String toString() {
    return String.format("%s %s", this.amount, this.currency);
  }
};
