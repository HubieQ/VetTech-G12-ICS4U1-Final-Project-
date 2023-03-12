
import java.util.*;
import java.io.*;


/*==================================================================
 |  ClientManager.java                                              |
 |------------------------------------------------------------------|
 |  Author:  Hubert Qin                                             |
 |  Class:  ICS4U1                                                  |
 |  Last Modified:  June 23, 2021                                   |
 |------------------------------------------------------------------|
 |  This class manages the different clients and performs           |     
 |  operations that prints clients bills, add and deletes them,     |
 |  charges for services, and searches for accounts                 |
  ==================================================================*/



class ClientManager{
    private Client clientList[]; //array of clients
    
    
    //constructor for client manager, fills in the array of clients
    ClientManager(Client clientList[]){
        
        this.clientList = new Client[clientList.length]; //declare size
        
        //loop through all clients
        for(int i=0;i<clientList.length;i++){
            this.clientList[i] = clientList[i]; //assign individually
        }
    }
    /*==============================================================================
    |  void listAllClients()                                                       |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and prints out all clients            |
    ==============================================================================*/
    public void listAllClients(){
        
        System.out.println("List of all clients:"); //print a list of all clients
        
        //loop through all clients
        for(Client c: this.clientList){
            System.out.println(c.toString()); //print every client
        }
    }
    /*==============================================================================
    |  void listAllPremiumClients()                                                |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and prints out all of the ones with   |
    |  premium clients                                                             |
    ==============================================================================*/
    public void listAllPremiumClients(){
        
        System.out.println("List of all premium clients:"); //print list of premium clients
        
        //loop through all clients
        for(Client c: this.clientList){
            
            if(c instanceof PremiumClient){ //check if premium client
                
                System.out.println(c.toString()); //print every premium client
                
            }
        }
    }
    
    /*==============================================================================
    |  void listAllStandardClients()                                                |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and prints out all of the ones with   |
    |  standard clients                                                            |
    ==============================================================================*/
    public void listAllStandardClients(){
        
        System.out.println("List of all standard clients:"); //print list of standard clients
        
        //loop through all clients
        for(Client c: this.clientList){
            
            if(c instanceof StandardClient){ //check if standard client
                
                System.out.println(c.toString()); //print every standard client
                
            }
        }
    }
    /*==============================================================================
    |  void listAllUnpaidClients()                                                 |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and prints out all of the ones with   |
    |  unpaid balances                                                             |
    ==============================================================================*/
    public void listAllUnpaidClients(){
        
        System.out.println("List of all unpaid clients:"); //print list of unpaid clients
        
        //loop through all clients
        for(Client c: this.clientList){
            
            if(c.outstandingBalance != 0){ //check if unpaid client
                
                System.out.println(c.toString()); //print every unpaid client
                
            }
        }
    }
    /*==============================================================================
    |  void listAllUnusedClients()                                                 |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and prints out all of the ones with   |
    |  unused services                                                             |
    ==============================================================================*/
    public void listAllUnusedClients(){
        System.out.println("List of all unused clients:"); //print list of unused clients
        
        //loop through all clients
        for(Client c: this.clientList){
            
            if(c instanceof StandardClient){ //check if standard client
                
                StandardClient sc = (StandardClient) c; //convert to standard client
                
                if(sc.freeCheckupsLeft() != 0){ //still more checkups
                    
                    System.out.println(c.toString()); //print client if checkups left
                    
                }
                
                
            }
            else{
                PremiumClient pc = (PremiumClient) c; //convert to premium client

                if(pc.freeGroomingLeft() != 0 || pc.freeLabTestsLeft() != 0){ //still more groomings or lab tests
                    
                    System.out.println(c.toString()); //print client
                    
                }
            }
        }
    }
     /*==============================================================================
    |  void addClient(String clientType, int clientNumber, String firstName,       |
    |  String lastName, String petName)                                            |                             
    |------------------------------------------------------------------------------|
    |  String clientType - type of client (standard or premium)                    |
    |  int clientNumber - clients number used for identification                   |
    |  String firstName - first name of client                                     |
    |  String lastName - last name of client                                       |
    |  String petName - clients pets name                                          |
    |------------------------------------------------------------------------------|
    |  This method adds a new Client to the array of Clients given the client type |
    |  client number, name, and pet name of the client. The free services are set  |
    |  to the maximum as well                                                      |
    ==============================================================================*/
    public void addClient(String clientType, int clientNumber, String firstName, String lastName, String petName){
       Client newClient; //declare variables
       Client cloneArray[];
       
       cloneArray = this.clientList.clone(); //clone array so that values can be added later
       
       this.clientList = new Client[this.clientList.length+1]; //resize array to add client
       
       //add new client
       if(clientType.equals("premiumclient")){
           newClient = new PremiumClient(clientNumber,firstName,lastName,petName,0,0,0,0); //set premium client
       }else{
           newClient = new StandardClient(clientNumber,firstName,lastName,petName,0,0,0); //set standard client
       }
       newClient.resetServices(); //default yearly services
       
       //loop through all clients
       for(int i=0;i<cloneArray.length;i++){
           this.clientList[i] = cloneArray[i]; //copy clients
       }
       this.clientList[this.clientList.length - 1] = newClient; //add new client
    }
     /*==============================================================================
    |  void deleteClient(int clientNumber)                                         |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number used for identification                   |
    |------------------------------------------------------------------------------|
    |  This method takes the client number to search for the clients account in an |
    |  array and deletes it from the array. The array is also resizes in the       |
    |  process                                                                     |
    ==============================================================================*/
    public void deleteClient(int clientNumber){
       Client cloneArray[]; //declare variables
       Client c;
       int curIdx;
       
       cloneArray = this.clientList.clone(); //clone array

       curIdx = 0; //index of next item in array
       
       this.clientList = new Client[this.clientList.length - 1]; //resize array
       
       //loop through all clients
       for(int i=0;i<cloneArray.length;i++){
           c = cloneArray[i];
           
           if(c.clientNumber != clientNumber){ //add client if it isnt being deleted
               
               this.clientList[curIdx] = cloneArray[i]; //add client
               
               curIdx++; //move along in the array
               
           }
       }
       
    }
     /*==============================================================================
    |  void enterService(int clientNumber,String service, double payment)          |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - client number to identify account                        |
    |  String service - name of service being applied to the client                |
    |  double payment - charge of the service being provided                       |
    |------------------------------------------------------------------------------|
    |  This method takes a file than reads all the client's information on it      |                                            
    ==============================================================================*/
    public void enterService(int clientNumber,String service, double payment ){
        boolean found = false; //client not found
        
        for(int i = 0;!found && i < this.clientList.length; i++){ //loop through all clients until found
            
            if(this.clientList[i].clientNumber == clientNumber){ //check if clients are the same
                
                this.clientList[i].chargeForServices(service, payment); //charge for services
                
                found = true; //client found
            }
        }
    }
     /*==============================================================================
    |  void printAllBills()                                                        |                             
    |------------------------------------------------------------------------------|
    |  This method takes prints all the bills for each client                      |                                            
    ==============================================================================*/
    public void printAllBills(){
        for(Client c: this.clientList){ //loop through all clients
            
            System.out.println(c.bill()); //print bill for each client
            
        }
    }
     /*==============================================================================
    |  void saveClientList(String fileName)                                        |                             
    |------------------------------------------------------------------------------|
    |  String fileName - name of file data is being saved to                       |
    |------------------------------------------------------------------------------|
    |  This method takes a file than saves all the clients information on it       |                                            
    ==============================================================================*/
    public void saveClientList(String fileName) throws IOException{
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));//bufferedwriter for file output
            
            bw.write(clientList.length+"\n"); //print number of clients
            
            for(Client c: clientList){
                if(c instanceof PremiumClient){ //premium client
                    PremiumClient pc = (PremiumClient)c; //convert to premium client
                    
                    //write all fields
                    bw.write("premiumclient\n");
                    bw.write(pc.clientNumber+"\n");
                    bw.write(pc.firstName+"\n");
                    bw.write(pc.lastName+"\n");
                    bw.write(pc.petName+"\n");
                    bw.write(pc.balance+"\n");
                    bw.write(pc.freeGroomingLeft()+"\n");
                    bw.write(pc.freeLabTestsLeft()+"\n");
                    bw.write(pc.outstandingBalance+"\n");
                    
                }else{ //standard client
                    StandardClient sc = (StandardClient)c; //convert to standard client
                    
                    //write all fields
                    bw.write("standardclient\n");
                    bw.write(sc.clientNumber+"\n");
                    bw.write(sc.firstName+"\n");
                    bw.write(sc.lastName+"\n");
                    bw.write(sc.petName+"\n");
                    bw.write(sc.balance+"\n");    
                    bw.write(sc.freeCheckupsLeft()+"\n");
                    bw.write(sc.outstandingBalance+"\n");
                }
                
                
            }bw.close(); //close bufferedwriter
        }
        catch (IOException ioe){
            System.out.println("File does not exist");
        }
        
        
        
    }
     /*==============================================================================
    |  void loadClientList(String fileName)                                        |                             
    |------------------------------------------------------------------------------|
    |  String fileName - name of file data is being loaded from                    |
    |------------------------------------------------------------------------------|
    |  This method takes a file than reads all the clients information on it       |                                            
    ==============================================================================*/
    public void loadClientList(String fileName) throws IOException{
        try{
           BufferedReader br = new BufferedReader(new FileReader(fileName)); //bufferedreader for file input
           
           //variables for fields
           String clientType, firstName, lastName, petName;
           int length, clientNumber, freeGroomingLeft,freeLabTestsLeft, freeCheckupsLeft;
           double balance, outstandingBalance;
           
           length = Integer.parseInt(br.readLine()); //number of clients or length of array
           
           this.clientList = new Client[length]; //declare new array
           
           //read all clients
           for(int i = 0;i < length;i++){
               clientType = br.readLine();
               if(clientType.equals("premiumclient")){ //premium client
                   
                   //read fields
                   clientNumber = Integer.parseInt(br.readLine());
                   firstName = br.readLine();
                   lastName = br.readLine();
                   petName = br.readLine();
                   balance = Double.parseDouble(br.readLine());
                   freeGroomingLeft = Integer.parseInt(br.readLine());
                   freeLabTestsLeft = Integer.parseInt(br.readLine());
                   outstandingBalance = Double.parseDouble(br.readLine());
                   
                   this.clientList[i] = new PremiumClient(clientNumber, firstName,lastName,petName,balance, freeGroomingLeft, freeLabTestsLeft, outstandingBalance);//create client
                   
               }else{ //standard client
                   
                   //read fields
                   clientNumber = Integer.parseInt(br.readLine());
                   firstName = br.readLine();
                   lastName = br.readLine();
                   petName = br.readLine();
                   balance = Double.parseDouble(br.readLine());
                   freeCheckupsLeft = Integer.parseInt(br.readLine());
                   outstandingBalance = Double.parseDouble(br.readLine());
                   
                   this.clientList[i] = new StandardClient(clientNumber, firstName, lastName, petName, balance, freeCheckupsLeft, outstandingBalance);//create client
               }
           }
        }
        catch (IOException ioe){
            System.out.println("File does not exist"); //file does not exist
        }
    }
    /*==============================================================================
    |  Client searchClient(String firstName, String lastName)                      |                             
    |------------------------------------------------------------------------------|
    |  Returns Client - Client's account with specified name                       |
    |------------------------------------------------------------------------------|
    |  String firstName - first name of client                                     |
    |  String lastName - last name of client                                       |
    |------------------------------------------------------------------------------|
    |  This method takes the clients first and last name to find the clients       |
    |  account in an array with linear search and returns it                       |
    ==============================================================================*/
    public Client searchClient(String firstName, String lastName){
        Client ret = new StandardClient(-1,"firstName","lastName","petName",0,0,0); //client to be returned, set to default random value
        boolean found = false; //currently false because client is not found
        
        for(int i = 0; !found && i<this.clientList.length;i++){ //loop through all clients while not found
            
            if(this.clientList[i].firstName.equals(firstName) && this.clientList[i].lastName.equals(lastName)){ //check if client matches
                
                ret = clientList[i]; //set return object for client
                
                found = true; //client is found
            }
        }
        return ret; //return client that is found
    }
    /*==============================================================================
    |  Client searchClient(int clientNumber)                                       |                             
    |------------------------------------------------------------------------------|
    |  Returns Client - Client's account with specified client number              |
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number needed to find account                    |
    |------------------------------------------------------------------------------|
    |  This method takes the clients number to find the clients account in an      |
    |  array with linear search and returns it                                     |
    ==============================================================================*/
    public Client searchClient(int clientNumber){
        Client ret = new StandardClient(-1,"firstName","lastName","petName",0,0,0); //client to be returned, set to default random value
        boolean found = false; //currently false because client is not found
        
        for(int i = 0; !found && i<this.clientList.length;i++){ //loop through all clients while not found
            
            if(this.clientList[i].clientNumber == clientNumber){ //check if client matches client number
                
                ret = clientList[i]; //set return object for client
                
                found = true; //client is found
            }
        }
        return ret; //return client that is found
    }
    /*==============================================================================
    |  Client searchClient(String petName)                                         |                             
    |------------------------------------------------------------------------------|
    |  Returns Client - Client's account with specified pet name                   |
    |------------------------------------------------------------------------------|
    |  String petName - name of pet                                                |
    |------------------------------------------------------------------------------|
    |  This method takes the clients pets name to find the clients account in an   |
    |  array with linear search and returns it                                     |
    ==============================================================================*/
    public Client searchClient(String petName){
        Client ret = new StandardClient(-1,"firstName","lastName","petName",0,0,0); //client to be returned, set to default random value
        boolean found = false; //currently false because client is not found
        
        for(int i = 0; !found && i<this.clientList.length;i++){ //loop through all clients while not found
            
            if(this.clientList[i].petName.equals(petName)){ //check if client matches pet name
                
                ret = clientList[i]; //set return object for client
                
                found = true; //client is found
            }
        }
        return ret; //return client that is found
    }
    /*==============================================================================
    |  void sortByPetName()                                                        |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and sorts it using bubble sort by     |
    |  client name in alphabetical order                                           |
    ==============================================================================*/
    public void sortByName(){
        //declare variables
        boolean loop;
        Client tmp;
        
        loop = true; //start looping
        
        //loop through all integers 
        for(int i = 0;i < this.clientList.length - 1 && loop;i++){
            
            //if array is sorted then loop stays false
            loop = false;
            
            //values left to be sorted
            for(int j = 0;j<this.clientList.length - i - 1;j++){
                
                //bubble the value along
                if((this.clientList[j].firstName+this.clientList[j].lastName).compareTo(this.clientList[j+1].firstName+this.clientList[j+1].lastName) > 0){
                    
                    //swap values
                    tmp = this.clientList[j];
                    this.clientList[j] = this.clientList[j+1];
                    this.clientList[j+1] = tmp;
                    
                    loop = true; //loop again
                }
            }
        }
    }
    /*==============================================================================
    |  void sortByClientNumber()                                                   |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and sorts it using bubble sort by     |
    |  client number in ascending order                                            |
    ==============================================================================*/
    public void sortByClientNumber(){
        //declare variables
        boolean loop;
        Client tmp;
        
        loop = true; //start looping
        
        //loop through all integers 
        for(int i = 0;i < this.clientList.length - 1 && loop;i++){
            
            //if array is sorted then loop stays false
            loop = false;
            
            //values left to be sorted
            for(int j = 0;j<this.clientList.length - i - 1;j++){
                
                //bubble the value along
                if(this.clientList[j].clientNumber > this.clientList[j+1].clientNumber){
                    
                    //swap values
                    tmp = this.clientList[j];
                    this.clientList[j] = this.clientList[j+1];
                    this.clientList[j+1] = tmp;
                    
                    loop = true; //loop again
                }
            }
        }
       
    }
    /*==============================================================================
    |  void sortByPetName()                                                        |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and sorts it using bubble sort by pet |
    |  name in alphabetical order                                                  |
    ==============================================================================*/
    
    public void sortByPetName(){
        //declare variables
        boolean loop;
        Client tmp;
        
        loop = true; //start looping
        
        //loop through all integers 
        for(int i = 0;i < this.clientList.length - 1 && loop;i++){
            
            //if array is sorted then loop stays false
            loop = false;
            
            //values left to be sorted
            for(int j = 0;j<this.clientList.length - i - 1;j++){
                
                //bubble the value along
                if((this.clientList[j].petName).compareTo(this.clientList[j+1].petName) > 0){
                    
                    //swap values
                    tmp = this.clientList[j];
                    this.clientList[j] = this.clientList[j+1];
                    this.clientList[j+1] = tmp;
                    
                    loop = true; //loop again
                }
            }
        }
    }
     /*==============================================================================
    |  void upgradeClient(int clientNumber)                                        |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number used for identification                   |
    |------------------------------------------------------------------------------|
    |  This method takes the client number to access the account and upgrades it   |
    |  from standard to premium                                                    |
    ==============================================================================*/
    public void upgradeClient(int clientNumber){
        boolean found = false; //client not found
        
        Client c; //temporary client for simplifying
        
        //loop through all clients
        for(int i = 0;!found && i<this.clientList.length;i++){
            c = this.clientList[i];
            
            if(this.clientList[i].clientNumber == clientNumber){ //matching clients
                
                this.clientList[i] = new PremiumClient(c.clientNumber,c.firstName,c.lastName,c.petName,c.balance,0,0,c.outstandingBalance); //update client status
                this.clientList[i].resetServices();
                
                found = true; //client found
            }
        }
    }
    /*==============================================================================
    |  void downgradeClient(int clientNumber)                                      |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number used for identification                   |
    |------------------------------------------------------------------------------|
    |  This method takes the client number to access the account and downgrades it |
    |  from premium to standard                                                    |
    ==============================================================================*/
    public void downgradeClient(int clientNumber){
        boolean found = false; //client not found
        
        Client c;//temporary client for simplifying
        
        //loop through all clients
        for(int i = 0;!found && i<this.clientList.length;i++){
            c = this.clientList[i];
            
            if(this.clientList[i].clientNumber == clientNumber){ //matching clients
                
                this.clientList[i] = new StandardClient(c.clientNumber,c.firstName,c.lastName,c.petName,c.balance,0,c.outstandingBalance); //update client status
                this.clientList[i].resetServices();
                
                found = true; //client found
            }
        }
    }
    /*==============================================================================
    |  void yearlyServiceReset()                                                   |
    |------------------------------------------------------------------------------|
    |  This method takes an array of Clients and resets all free services to its   |
    |  full values every year                                                      | 
    ==============================================================================*/
    public void yearlyServiceReset(){
        //loop through all clients
        for(Client c: this.clientList){
            c.resetServices(); //reset services 
        }
    }
    /*==============================================================================
    |  void addBalance(int clientNumber, double payment)                           |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number used for identification                   |
    |  double payment - amount of money being deposited in the clients account     |
    |------------------------------------------------------------------------------|
    |  This method takes the client number and a deposit amount to update the      |
    |  account balance of the client                                               |
    ==============================================================================*/
    public void addBalance(int clientNumber,double payment){
        boolean found = false; //client not found
        
        for(int i = 0;!found && i < this.clientList.length; i++){ //loop through all clients until found
            
            if(this.clientList[i].clientNumber == clientNumber){ //check if clients are the same
                
                this.clientList[i].addBalance(payment); //charge for services
                
                found = true; //client found
            }
        }
    }
    /*==============================================================================
    |  void addBalance(int clientNumber, double payment)                           |                             
    |------------------------------------------------------------------------------|
    |  int clientNumber - clients number used for identification                   |
    |  double payment - amount of money being deposited in the clients account     |
    |------------------------------------------------------------------------------|
    |  This method takes the client number and a payment amount to decrease the    |
    |  outstanding balance. Prints if debt has been paid off or not                |
    ==============================================================================*/
    public void payDebt(int clientNumber, double payment){
        boolean found = false; //client not found
        
        
        
        for(int i = 0;!found && i < this.clientList.length; i++){ //loop through all clients until found
            
            if(this.clientList[i].clientNumber == clientNumber){ //check if clients are the same
                
                
                if(this.clientList[i].acceptPayment(payment)){ //decrease debt
                    System.out.println("Outstanding balance has been cleared");
                }
                else{
                    System.out.println("Outstanding balance still remains"); //still debt to pay
                }

                
                found = true; //client found
            }
        }
    }
}


