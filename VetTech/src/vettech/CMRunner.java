import java.util.*;
import java.io.*;
/*==================================================================
 |  CMRunner.java                                                   |
 |------------------------------------------------------------------|
 |  Author:  Hubert Qin                                             |
 |  Class:  ICS4U1                                                  |
 |  Last Modified:  June 23, 2021                                   |
 |------------------------------------------------------------------|
 |  This class manages user input to communicate with the client    |
 |  manager to manage the clients of AYJ VetTech                    |
  ==================================================================*/
public class CMRunner {
    /*========================================================================
   |  void printMenu()                                                       |
   |-------------------------------------------------------------------------|
   |  This method will print the menu of AYJ Vettech will all the commands   |
   |  that are avaliable to the user in regards to operating veternarian     |
   |  services                                                               |
   =========================================================================*/
    public static void printMenu(){
        //print menu
        System.out.println("This is AYJ VetTech Management, what would you like to do?");
        System.out.println();
        
        //Saving and loading clients
        System.out.println("Saving and Loading");
        System.out.println("1. Load all clients from a save file");
        System.out.println("2. Save all clients to a file");
        System.out.println();
        System.out.println("Client Management");
        System.out.println("3. Add a new client");
        System.out.println("4. Delete a client");
        System.out.println("5. Charge a service to a client");
        System.out.println("6. Sort all clients by name");
        System.out.println("7. Sort all clients by client number");
        System.out.println("8. Sort all clients by pet name");
        System.out.println("9. Print all clients");
        System.out.println("10. Print all premium clients");
        System.out.println("11. Print all standard clients");
        System.out.println("12. Print all unpaid clients");
        System.out.println("13. Print all clients who have not used up all their free services");
        System.out.println("14. Upgrade a client");
        System.out.println("15. Downgrade a client");
        System.out.println("16. Deposit money into a clients account");
        System.out.println("17. Search for a client by name and display their details ");
        System.out.println("18. Search for a client by client number and display their details ");
        System.out.println("19. Search for a client by pet name and display their details ");
        System.out.println("20. Print all bills for clients");
        System.out.println("21. Pay an amount of debt for a client");
        System.out.println();
        
        //service management
        System.out.println("Service Management");
        System.out.println("22. List all services and costs");
        System.out.println("23. Change the cost of a service");
        System.out.println("24. Reset yearly services for all clients");
        System.out.println();
        
        //end program
        System.out.println("Exit Program");
        System.out.println("25. Exit program");
    }
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in); //declare scanner
        
        //declare variables
        String fileName, serviceName;
        boolean loop, programLoop;
        int command;
        double groomingPrice, checkupPrice, labTestPrice, surgeryPrice, deposit, newPrice, payment;
        Client c; 
        Client tempArr[];
        ClientManager cm;
        
        //all fields needed for client
        String clientType, firstName, lastName, petName;
        int length, clientNumber, freeGroomingLeft,freeLabTestsLeft, freeCheckupsLeft;
        double balance, outstandingBalance;
        
        programLoop = true; //program always loops
        
        //initialize variables so no error
        tempArr = new Client[0];
        cm = new ClientManager(tempArr);
        groomingPrice=0;
        checkupPrice=0;
        labTestPrice=0;
        surgeryPrice=0;
        
        //continuously loop
        while(programLoop){
            printMenu(); //print menu
        
            command = 0;
            loop = true; //initializing so no errors
        
            while(loop){ //loop while input is invalid
                try{
                    System.out.println("Enter the number for the task you want the client manager to do"); //prompt for command
                    command = Integer.parseInt(in.nextLine());
                
                    if(command < 1 || command > 25){ //invalid commands
                        System.out.println("Invalid command entered");
                    }else{
                        loop = false; //stop looping as valid command is entered
                    }
                }catch(NumberFormatException nfe){
                    System.out.println("Invalid command entered"); //invalid command entered
                }   
            }
        

            if(command == 1){ //option 1, load file
            
                //prompt for file name
                System.out.println("Enter a file name");
                fileName = in.nextLine();
           
                //create clientlist
                cm.loadClientList(fileName);
            
            }
            else if(command == 2){ //option 2, save file
                //prompt for file name
                System.out.println("Enter a file name");
                fileName = in.nextLine();
            
                 //create clientlist
                cm.saveClientList(fileName);
            }
            else if(command == 3){ //add a new client
                try{
                   //prompt
                    System.out.println("Enter the details for a client");
                    System.out.println("Enter the client type");
                    clientType = in.nextLine();
                    System.out.println("Enter the client's client number:");
                    clientNumber = Integer.parseInt(in.nextLine());
                    System.out.println("Enter the client's first name:");
                    firstName = in.nextLine();
                    System.out.println("Enter the client's last name:");
                    lastName = in.nextLine();
                    System.out.println("Enter the client's pet name:");
                    petName = in.nextLine();
                        
                    cm.addClient(clientType, clientNumber, firstName, lastName, petName);
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
                
            }else if(command == 4){ //delete client
                try{
                   //prompt
                    System.out.println("Enter the details for a client");
                    System.out.println("Enter the client's client number:");
                    clientNumber = Integer.parseInt(in.nextLine());
                    
                    cm.deleteClient(clientNumber); //delete client
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input");
                }
            }
            else if (command == 5){ //charge a service
                
                //prompt
                System.out.println("Enter the client number");
                clientNumber = Integer.parseInt(in.nextLine());
                System.out.println("Enter a service (surgery, grooming, checkup, lab test)");
                serviceName = in.nextLine();
                
                //charge for services
                if(serviceName.equals("surgery")){
                    
                    cm.enterService(clientNumber, serviceName, surgeryPrice); //charge surgery
                    
                }else if( serviceName.equals("grooming")){
                    
                    cm.enterService(clientNumber, serviceName, groomingPrice); //charge grooming
                    
                }else if(serviceName.equals("checkup")){
                    
                    cm.enterService(clientNumber, serviceName, checkupPrice); //charge checkup
                    
                }else if (serviceName.equals("lab test")){
                    
                    cm.enterService(clientNumber, serviceName, labTestPrice); //charge lab test
                }else{
                    
                    System.out.println("invalid service was entered"); //invalid service entered
                    
                }
                
            }else if (command == 6){
                
                cm.sortByName(); //sort by client name
                
                cm.listAllClients(); //print all clients after sort
                
            }else if(command == 7){
                
                cm.sortByClientNumber();//sort by client number
                
                cm.listAllClients(); //print all clients after sort
                
            }else if(command == 8){
                
                cm.sortByPetName();//sort by pet name
                
                cm.listAllClients(); //print all clients after sort
                
            }else if(command == 9){
                
                cm.listAllClients(); //list all clients
                
            }else if(command == 10){
                
                cm.listAllPremiumClients(); //print all premium clients
                
            }else if(command == 11){
                
                cm.listAllStandardClients(); //print all standard clients
                
            }else if(command == 12){
                
                cm.listAllUnpaidClients(); //print all unpaid clients
                
            }else if(command == 13){
                
                cm.listAllUnusedClients(); //print all clients with unused services
                
            }else if(command == 14){//upgrade client
                try{
                    //prompt
                    System.out.println("Enter the client number");
                    clientNumber = Integer.parseInt(in.nextLine());
                
                    cm.upgradeClient(clientNumber); //upgrade client
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
            }else if(command == 15){//downgrade client
                try{
                    //prompt
                    System.out.println("Enter the client number");
                    clientNumber = Integer.parseInt(in.nextLine());
                
                    cm.downgradeClient(clientNumber); //downgrade client
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
            }else if(command == 16){ //deposit money into account
                try{
                    //prompt
                    System.out.println("Enter the client number");
                    clientNumber = Integer.parseInt(in.nextLine());
                    System.out.println("Enter the deposit amount (non refundable)");
                    deposit = Double.parseDouble(in.nextLine());
                
                    cm.addBalance(clientNumber, deposit);
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
            }else if(command == 17){ //search by full name and print client 
                
                System.out.println("Enter the first name of the client"); //prompt
                firstName = in.nextLine();
                System.out.println("Enter the last name of the client");
                lastName = in.nextLine();
                
                c = cm.searchClient(firstName, lastName); //search with full name
                
                System.out.println(c.toString()); //print details
            }else if(command == 18){
                try{
                    System.out.println("Enter the client number"); //prompt
                    clientNumber = Integer.parseInt(in.nextLine());
                
                    c = cm.searchClient(clientNumber); //search using number
                
                    System.out.println(c.toString()); //print details
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
            }else if(command == 19){
                
                System.out.println("Enter the clients pet name"); //prompt
                petName = in.nextLine();
                
                c = cm.searchClient(petName); //search by pet name
            }else if(command == 20){
                
                cm.printAllBills(); //print all bills of clients
                
            }else if(command == 21){
                try{
                    //prompt
                    System.out.println("Enter the client number"); //prompt
                    clientNumber = Integer.parseInt(in.nextLine());
                    System.out.println("Enter the amount of money client wants to pay");
                    payment = Double.parseDouble(in.nextLine());
                    
                    cm.payDebt(clientNumber, payment); //pay debt
                    
                }catch(NumberFormatException nfe){
                    System.out.println("Invalid input");
                }
            }else if(command == 22){ //list all services and costs
                
                //print all prices
                System.out.println("Service: Surgery Cost: $"+surgeryPrice);
                System.out.println("Service: Grooming Cost: $"+groomingPrice);
                System.out.println("Service: Checkup Cost: $"+checkupPrice);
                System.out.println("Service: Lab Test Cost: $"+labTestPrice);
                
            }
            
            else if(command == 23){
                try{
                    //prompt
                    System.out.println("Enter the name of the service you want to change prices (surgery, grooming, checkup, lab test)");
                    serviceName = in.nextLine();
                    System.out.println("Enter the new price");
                    newPrice = Double.parseDouble(in.nextLine());
                
                    if(serviceName.equals("surgery")){
                    
                        surgeryPrice = newPrice; //change surgery price
                        
                        System.out.println("The cost of a surgery is now " + newPrice); //print new price
                        
                    }else if(serviceName.equals("grooming")){
                    
                        groomingPrice = newPrice; //change grooming price
                        
                        System.out.println("The cost of grooming is now " + newPrice); //print new price
                    
                    }else if(serviceName.equals("checkup")){
                    
                        checkupPrice = newPrice; //change checkup price
                        
                        System.out.println("The cost of a checkup is now " + newPrice);//print new price
                    
                    }else if(serviceName.equals("lab test")){
                    
                        labTestPrice = newPrice; //change lab test price
                        
                        System.out.println("The cost of a lab test is now " + newPrice);//print new price
                    
                    }else{
                    
                        System.out.println("Wrong service was entered"); //invalid service entered
                        
                    }
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid input"); //invalid input
                }
            }else if(command == 24){
                
                cm.yearlyServiceReset(); //reset all accounts
                
                System.out.println("Accounts reset!"); //print reset account
                
            }else if(command == 25){
                
                programLoop = false; //stop the program loops
                
            }
        }
    }
}
