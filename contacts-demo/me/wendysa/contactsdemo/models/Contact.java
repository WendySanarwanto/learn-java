package me.wendysa.contactsdemo.models;

/**
 * Contact entity class.
 */
public class Contact{

  public enum Type {
    BUSINESS("Business"), FAMILY("Family"), FRIEND("Friend");

    private String value;
    private Type(String value){
      this.value = value;
    }
  };

  Type type;
  private String name;
  private String email;

  public Contact(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return this.type;
  }

  public String toString() {
    return "\nName: "+this.name+"\n"+
           "Email: "+this.email+"\n"+
           "Type: "+this.type.value+"\n";
  }
}
