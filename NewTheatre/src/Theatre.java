import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;
public class Theatre {
    public static void main(String[] args) {

        // Declaring arrays needed for the program
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        int [][] total_seats = {row1,row2,row3};
        ArrayList <Ticket> ticket_list = new ArrayList <>();

        Scanner option = new Scanner(System.in);
        int selected;
        boolean running = true;

        System.out.print("\nWelcome to the New Theatre");
        while (running) {

            System.out.println("\n-----------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("-----------------------------------");


            while (true) {
                try{
                    System.out.print("\nSelect an option from the above menu :  ");
                    selected = option.nextInt();
                    break;

                } catch (InputMismatchException e){
                    System.out.println("\n>>> Select a valid option from the menu");
                    option.next();
                }
            }

            switch (selected){

                case 0:
                    System.out.println(">>> Exited.");
                    System.out.println("\n~~~~~~~~~~~~~~~~  THANK YOU ! ~~~~~~~~~~~~~~~~");
                    running = false;
                    break;

                case 1:
                    buy_ticket(ticket_list, row1, row2, row3);
                    break;

                case 2:
                    print_seating_area(total_seats);
                    break;

                case 3:
                    cancel_ticket(ticket_list, row1, row2, row3);
                    break;

                case 4:
                    show_available("row 1", row1);
                    show_available("row 2", row2);
                    show_available("row 3", row3);
                    break;

                case 5:
                    save(row1, row2, row3);
                    break;

                case 6:
                    load(row1, row2, row3);
                    break;

                case 7:
                    show_tickets_info(ticket_list);
                    break;

                case 8:
                    sort_tickets(ticket_list);
                    break;

                default:
                    System.out.println("\n>>> Select option only from the menu.");
            }
        }
    }


    /*
    * Facilitates customer's purchase of theater tickets based on their preference of row 1, 2, or 3.

    * @param ticket_list :- This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    * @param target_row1 :- This parameter is used to access the row1 array inside the main method. This contains the information of seats in row one of the theater.
    * @param target_row2 :- This parameter is used to access the row2 array inside the main method. This contains the information of seats in row two of the theater.
    * @param target_row3 :- This parameter is used to access the row3 array inside the main method. This contains the information of seats in row three of the theater.
    * */
    private static void buy_ticket(ArrayList <Ticket> ticket_list, int[] target_row1, int[] target_row2, int[] target_row3) {

        Scanner input = new Scanner(System.in);
        int row_num;

        while (true) {    //this while loop is used validate the user entered row number.
            try {
                System.out.print("\nEnter your preferred row number: ");
                row_num = input.nextInt();

                if (row_num <= 0 || row_num > 3) {
                    System.out.println("The row you have selected does not exist. Rows available: 1 , 2 and 3");
                }
                else{
                    break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please only enter Integer!");
                input.next();     //The buffer is cleared, and the scanner is prepared for a new input.
            }
        }

        switch (row_num) {
            case 1:
                reservation(target_row1, "Enter the seat number you want to book: ", 1 /* This value is passed to check if the seat is available and to book that seat */, ticket_list, "\n>>> Ticket has been booked successfully.", row_num, "\nThe seat you have selected is not available.", input);
                break;
            case 2:
                reservation(target_row2, "Enter the seat number you want to book: ",  1, ticket_list, "\n>>> Ticket has been booked successfully.", row_num, "\nThe seat you have selected is not available.", input);
                break;
            case 3:
                reservation(target_row3, "Enter the seat number you want to book: ",  1, ticket_list, "\n>>> Ticket has been booked successfully.", row_num, "\nThe seat you have selected is not available.",input);
                break;
        }
    }

    /* This reservation method is called inside the buy_ticket method and cancel_ticket method. This method is
       used to validate the seat number user enters, and reserve or cancel the reserved seat.

    *  @param targetRow :- This parameter is used to access the required row Array (row1, row2 or row3) from the main method. It contains the seating information.
    *  @param prompt    :- This parameter is used to display a message to the user to do a required task (seat number to book or seat number to cancel)
    *  @param availability :- This parameter is used inside a condition. if the parameter value is passed as 1, it will make the condition check if the seat is available and
                              in the next line it will assign that seat number to 1 (to book).
                              if the parameter value is passed as 0, it will make the condition check if the seat is booked and in the next line it will assign that seat number to 0 (to cancel).
    *  @param ticket_list :- This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    *  @param alert_user  :- This parameter is used to alert the user a required information.
    *  @param rowNum      :- This parameter is used to pass the row number user entered, into this method.
    *  @param message     :- This parameter is used to display the user the required task is done.
    *  @param input       :- The Scanner object.
    * */
    private static void reservation(int[] targetRow, String prompt, int availability , ArrayList <Ticket> ticket_list, String alert_user, int rowNum, String message ,Scanner input){
        int seat_num;

        while(true) {
            System.out.print(prompt);

            if (input.hasNextInt()) {
                seat_num = input.nextInt();

                if (1 <= seat_num && seat_num <= targetRow.length) {  //Checking if the user have entered a correct seat number , from the row they selected.

                    if (targetRow[seat_num - 1] != availability) {
                        targetRow[seat_num - 1] = availability;

                        if (availability==1){
                            ticket_list.add(objectProducing(input, seat_num, rowNum));

                        }
                        else{
                            deleteObject(ticket_list, rowNum, seat_num);

                        }
                        System.out.println(alert_user);
                    }
                    else {
                        System.out.println(message);
                    }
                    break;
                }
                else {
                    System.out.println("The seat you have selected does not exist.\n");
                }
            }
            else{
                System.out.println("Please enter an Integer!");
                input.next();
            }
        }
    }

    /* Creates the ticket object and return it

    * @param input :- Scanner object
    * @param seatingNum :- This parameter is used to pass the seat number user entered, into this method.
    * @param rowNum :-  This parameter is used to pass the row number user entered, into this method.
    * */
    private static Ticket objectProducing(Scanner input, int seatingNum, int  rowNum){

        System.out.println();
        System.out.print("Enter your First name : ");
        String buyer = input.next();
        System.out.print("Enter your Surname name : ");
        String family_name = input.next();

        String  E_address;

        while(true){  // Validating the customer Email address
            System.out.print("Enter your Email address : ");
            E_address = input.next();
            if(E_address.contains("@") && E_address.contains(".")){
                break;
            }
            else {
                System.out.println("\nEnter a valid email address\n");
            }
        }

        double cost;

        while(true) { //To validate user input of price.
            try {
                System.out.print("Enter a price for the ticket : ");
                cost = input.nextDouble();
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Enter only integer value.");
                input.next();
            }
        }

        Person Customer = new Person(buyer, family_name, E_address);        //creating person object and passing it when creating ticket object.
        Ticket ticket = new Ticket(rowNum, seatingNum, cost, Customer);     //creating ticket object.

        return ticket;
    }

    /* This method is used to remove the ticket object inside the ticket_list (ArrayList), when user cancels the ticket.

    * @param ticket_list :-  This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    * @param rowNum :-  Row number is passed as the parameter value. This is used to check if the ticket object contain the required row number user selected to delete.
    * @param seatNum :-  Seat number is passed as the parameter value. This is used to check if the ticket object contain the required seat number user selected to delete.
    * */
    private static void deleteObject( ArrayList<Ticket> ticket_list, int rowNum, int seatNum ){
        Iterator <Ticket> iterator = ticket_list.iterator();
        while(iterator.hasNext()){
            Ticket object = iterator.next();
            if (object.getRow() == rowNum && object.getSeat() == seatNum ){
                iterator.remove();
            }
        }
    }

    /* prints the theater's seating arrangement, printing booked seats as "X" and available seats as "O".

    * @param total_seats :- To access the 2D array total_seats, which contains seat information of all rows.
    * */
    private static void print_seating_area(int[][] total_seats) {

        System.out.println();
        System.out.println(" ".repeat(5) + "***********");
        System.out.println(" ".repeat(5) + "*  STAGE  *");
        System.out.println(" ".repeat(5) + "***********");

        int space = 4;  //space to leave when starting to print

        for (int[] row:total_seats){

            int count = 0;                                   //To keep count how many seats are printed.
            System.out.print(" ".repeat(space));            // repeat() method is referenced from https://www.geeksforgeeks.org/string-class-repeat-method-in-java-with-examples/

            for(int seats:row){
                if(seats==1){
                    System.out.print("X");
                }
                else {
                    System.out.print("O");
                }
                count++;
                if (count == row.length/2){  //To check if half amount of seats in the row is printed
                    System.out.print(" ");   // Leaves a space when half amount of seats are printed in that row.
                }
            }
            System.out.println();
            space -= 2;  // To maintain the pyramid alignment when printing the seats.
        }
    }

    /* This method is used to cancel the seat or seats user booked.

    * @param ticket_list :- This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    * @param target_row1 :- This parameter is used to access the row1 array inside the main method. This contains the information of seats in row one of the theater.
    * @param target_row2 :- This parameter is used to access the row2 array inside the main method. This contains the information of seats in row two of the theater.
    * @param target_row3 :- This parameter is used to access the row3 array inside the main method. This contains the information of seats in row three of the theater.
    * */
    private static void cancel_ticket(ArrayList <Ticket> ticket_list, int[] target_row1, int[] target_row2, int[] target_row3){
        int row_num;
        Scanner input =new Scanner(System.in);

        while (true) {
            try {
                System.out.print("\nEnter your row number: ");
                row_num = input.nextInt();

                if (row_num <= 0 || row_num > 3) {
                    System.out.println("The row you have selected does not exist. Rows available: 1 , 2 and 3");
                }
                else{
                    break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Please only enter Integer!");
                input.next();
            }
        }

        switch (row_num) {
            case 1:
                reservation( target_row1, "Enter the seat number you want to cancel: ", 0 /* This value is passed inside to check if the seat is booked to cancel it*/, ticket_list, "\n>>> Seat is cancelled", row_num, "\nThe seat you wanted to cancel is already available", input);
                break;
            case 2:
                reservation( target_row2, "Enter the seat number you want to cancel: ", 0, ticket_list, "\n>>> Seat is cancelled", row_num, "\nThe seat you wanted to cancel is already available", input);
                break;
            case 3:
                reservation( target_row3, "Enter the seat number you want to cancel : ", 0, ticket_list, "\n>>> Seat is cancelled", row_num, "\nThe seat you wanted to cancel is already available", input);
                break;
        }

    }

    /* To display the seats that are available.

    * @param row_name :- To display the user which row information is showed, row name (row 1, row 2, or row 3) is passed as parameter value
    * @param target_row :- This parameter is used to access the required row Array (row1, row2 or row3) from the main method.
    * */
    private static void show_available(String row_name, int[] target_row){

        String symbol = " ";
        System.out.print("\nSeats available seats in " + row_name + " : ");
        for(int i=1; i <= target_row.length ; ++i){
            if(target_row[i-1] == 0){
                System.out.print(symbol + i);
                symbol = ",";
            }

        }
        System.out.print(".");
        System.out.println();
    }

    /* To save the booking information to a file

    * @param target_row1 :- This parameter is used to access the row1 array inside the main method. This contains the information of seats in row one of the theater.
    * @param target_row2 :- This parameter is used to access the row2 array inside the main method. This contains the information of seats in row two of the theater.
    * @param target_row3 :- This parameter is used to access the row3 array inside the main method. This contains the information of seats in row three of the theater.
    * */
    private static void save(int[] target_row1, int[] target_row2, int[] target_row3) {
        File file = new File("Booking_info.txt");
        try{
            FileWriter writer = new FileWriter("Booking_info.txt", false);    // passing second boolean argument as false is referenced from https://www.geeksforgeeks.org/filewriter-class-in-java/.
            writingFile(writer, target_row1);
            writingFile(writer, target_row2);
            writingFile(writer, target_row3);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("The file could not be saved because of an error.");
        }
        System.out.println("\n>>> Information has been saved in the file.");
    }

    /* This method is created to write the row information into the text file.

    * @param writer :- This parameter of FileWriter class is used to write it to the text file.
    * @param target_row :- This parameter is used to access the required row Array (row1, row2 or row3) from the main method.
    * */
    private static void writingFile(FileWriter writer, int[] targetRow){

        try {
            for (int j : targetRow) {
                writer.write(Integer.toString(j));  // Here every integer character taken is being converted to String before written.
            }
            writer.write("\n");
        }
        catch (IOException e){
            System.out.println("Unable to write the date into the file");
        }
    }

    /* This method is used to load the seat reservation information from the text file to the row arrays in the main method

    * @param target_row1 :- This parameter is used to access the row1 array inside the main method. This contains the information of seats in row one of the theater.
    * @param target_row2 :- This parameter is used to access the row2 array inside the main method. This contains the information of seats in row two of the theater.
    * @param target_row3 :- This parameter is used to access the row3 array inside the main method. This contains the information of seats in row three of the theater.
    * */
    private static void load(int[] target_row1, int[] target_row2, int[] target_row3){
        try {
            File newFile = new File("Booking_info.txt");
            Scanner reader = new Scanner(newFile);
            String FileLine;

            while (reader.hasNext()) {
                FileLine = reader.nextLine();

                    switch (FileLine.length()){

                        case 12:
                            infoAssigning(FileLine, target_row1);
                            break;

                        case 16:
                            infoAssigning(FileLine, target_row2);
                            break;

                        case 20:
                            infoAssigning(FileLine, target_row3);
                            break;
                    }
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Failure loading booking information from file.");
        }
        System.out.println("\n>>> File information loaded.");
    }

    /* To access character one by one and write that to the row arrays in the main method.

    * @param line :- the line taken from the text file is passed as the parameter value.
    * @param targetRow :- This parameter is used to access the required row Array (row1, row2 or row3) from the main method.
    * */
    private static void infoAssigning(String line, int[] targetRow){
        for(int t=0 ; t < line.length() ; ++t){
            char character = line.charAt(t);
            if (t< targetRow.length){
                targetRow[t] = Character.getNumericValue(character);  //character accessed is converted to integer before loading it to the row array. Referenced from https://www.tutorialspoint.com/java/lang/character_getnumericvalue.htm#:~:text=The%20Java%20Character%20getNumericValue(),with%20a%20value%20of%2050.
            }
        }
    }

    /* This method is used to display the ticket information users bought.

    * @param ticket_list :- This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    * */
    private static void show_tickets_info(ArrayList <Ticket> ticket_list){

        double total_price = 0;
        System.out.println("\n~~~  Tickets Information  ~~~");

        for(Ticket ticketObj:ticket_list){
            System.out.println();
            ticketObj.print();
            total_price += ticketObj.getPrice();
        }
        System.out.println("\n     Total Ticket price = " + total_price);
    }

    /* This method is used to sort the tickets stored in the ticket_list (ArrayList) in ascending order of ticket price.

    * @param ticket_list :- This parameter is used to access the ticket_list (ArrayList) inside the main method. The user's purchased tickets are stored here.
    * */
    private static void sort_tickets(ArrayList <Ticket> ticket_list) {  //Referenced from Westminster lecture slide.

        ArrayList <Ticket> sorted_list = new ArrayList<>();
        sorted_list.addAll(ticket_list); //.addAll() method is used to append all the elements in the newly created ArrayList (sorted_list)

        int bottom = sorted_list.size() - 2;
        boolean switched = true;

        while (switched) {
            switched = false;
            for (int i = 0; i <= bottom; i++) {
                Ticket object1 = sorted_list.get(i);
                Ticket object2 = sorted_list.get(i + 1);
                if (object1.getPrice() > object2.getPrice()) {
                    sorted_list.set(i, object2);
                    sorted_list.set(i+1, object1) ;
                    switched = true;
                }
            }
            bottom--;
        }
        System.out.println("\n~~~  Sorted Ticket Information  ~~~");
        for (Ticket sorted_ticket : sorted_list){
            System.out.println();
            sorted_ticket.print();
        }
    }
}