package me.wendysa.jspcrud.models;

public class Product {
  private int id;
  private Price price;
  private String description;
  private String name;

  /**
   * @return the Id
   */
  public int getId() {
    return id;
  }

  /**
   * @param name the Id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the price
   */
  public Price getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(Price price) {
    this.price = price;
  }
};
