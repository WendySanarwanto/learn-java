public class Contact{
  enum Type { BUSINESS, FAMILY, FRIEND };
  Type type;
  String name;
  String email;

  public Contact(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String toString() {
    return "\nName: "+this.name+"\n"+
           "Email: "+this.email+"\n"+
           "Type: "+this.type+"\n";
  }
}
