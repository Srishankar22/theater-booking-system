public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String user_name, String last_name, String mail){
        name = user_name;
        surname = last_name;
        email = mail;
    }

    public String  getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
}
