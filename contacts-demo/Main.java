public class Main{
  public static void main(String []args){
    String name = "Diana Prince";
    String email = "diana.prince@gmail.com";
    Contact contact = new Contact(name, email);
    contact.type = Contact.Type.FRIEND;
    System.out.println("\nMy 1st Contact is:\n------------------\n " + contact.toString());
    // System.out.println("TODO: Implement logic which bootstraping contacts service/agent.");
  }
}