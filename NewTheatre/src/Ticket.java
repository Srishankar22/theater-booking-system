public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void print(){
        System.out.println("Customer name : " + person.getName());
        System.out.println("Surname : " + person.getSurname());
        System.out.println("Customer Email address : " + person.getEmail());
        System.out.println("Selected row = " + row);
        System.out.println("Selected seat number = " + seat);
        System.out.println("Price suggested = " + price);
    }

    public int getRow(){

        return row;
    }
    public int getSeat(){

        return seat;
    }
    public double getPrice(){

        return price;
    }
}
