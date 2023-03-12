/*==================================================================
 |  StandardClient.java                                             |
 |------------------------------------------------------------------|
 |  Author:  Hubert Qin                                             |
 |  Class:  ICS4U1                                                  |
 |  Last Modified:  June 23, 2021                                   |
 |------------------------------------------------------------------|
 |  This class manages Standard Clients and does operations         |
 |  concerning them. These include accepting payments, performing   |
 |  services and printing bills                                     |
  ==================================================================*/

class StandardClient extends Client{
    final int YEARLY_FREE_CHECKUPS = 12; //constants
    final double MEMBERSHIP_FEE = 29.99;
    final double INTEREST_RATE = 0.02;
    
    
    private int freeCheckupsLeft; //instance field
    
    //constructor for standard client, fills in fields, calls on super class for other fields
    StandardClient(int clientNumber, String firstName, String lastName, String petName, double balance, int freeCheckupsLeft, double outstandingBalance){
        super(clientNumber, firstName, lastName, petName, balance, outstandingBalance); //fill fields
        this.freeCheckupsLeft = freeCheckupsLeft;
    }
     /*========================================================================
   |  String bill()                                                          |
   |-------------------------------------------------------------------------|
   |  Returns String - Client's billing information in string format         |
   |-------------------------------------------------------------------------|
   |  This method will return a string will all the clients financial        |
   |  information                                                            |
   =========================================================================*/
    public String bill(){
        String ret; //return string
        
        ret = "Current balance: $" + this.balance //add information
            + " Free checkups left: " + this.freeCheckupsLeft 
            + " Amount owed: $" + this.outstandingBalance; //add amounted owed
        
        return ret; //return bill
    }
    
   /*========================================================================
   |  boolean acceptPayment(double payment)                                  |
   |-------------------------------------------------------------------------|
   |  Returns boolean - true if the balance is fully paid off, false if it   |
   |  has not been                                                           |
   |-------------------------------------------------------------------------|
   |  double payment - amount of money being used to pay off the clients debt|
   |-------------------------------------------------------------------------|
   |  This method will reduce the outstanding balance and return true if it  |
   |  has fully been paid off and false if it hasn't                         |
   =========================================================================*/
     public boolean acceptPayment(double payment){
        boolean paidAll; //boolean if paid or not
        
        payment = Math.min(payment, this.outstandingBalance); //shouldnt have to overpay
        payment = Math.min(payment, this.balance);
        
        this.outstandingBalance -= payment; //reduce payment
        this.balance -= payment; //take payment out of balance
        
        this.outstandingBalance = Math.round(100 * this.outstandingBalance)/100; //round to 2 decimal places
        this.balance = Math.round(100 * this.balance)/100;
        
        if(this.outstandingBalance > 0){
            paidAll = false; //did not pay full balance
            this.outstandingBalance *= (1 + INTEREST_RATE); //charge interest
        }
        else{
            paidAll = true; //finished paying all
        }
        return paidAll; //return
    }
     /*========================================================================
   |  void chargeForServices(String serviceName, double cost)                |
   |-------------------------------------------------------------------------|
   |  String serviceName - name of the service being provided to the client  |
   |  double cost - price of the service                                     |
   |-------------------------------------------------------------------------|
   |  This method will charge the client for a service, or deduct the number |
   |  of free services remaining if applicable                               |
   =========================================================================*/
    public void chargeForServices(String serviceName, double cost){
        
        //if service is checkup then it is free
        if(serviceName.equals("checkup")){
            
            if(this.freeCheckupsLeft > 0){ //if checkups are still avaliable
                this.freeCheckupsLeft--;
            }else{
                this.outstandingBalance += cost; //add cost to the bill
            }
        }
        else if(serviceName.equals("grooming")){ //grooming service

            this.outstandingBalance += cost; //add cost to the bill
           
           
        }else if(serviceName.equals("lab test")){ //lab tests
 
            this.outstandingBalance += cost; //add cost to the bill
            
        }
        else if(serviceName.equals("surgery")){ //surgeries
            
            this.outstandingBalance += cost; //add cost to the bill
        }
        
    }
    /*========================================================================
   |  void resetServices()                                                   |
   |-------------------------------------------------------------------------|
   |  This method will reset the free services of the client to its maximum  |                                
   =========================================================================*/
    public void resetServices(){
        this.freeCheckupsLeft = YEARLY_FREE_CHECKUPS; //reset checkups
    }
    /*========================================================================
   |  void printBill()                                                       |
   |-------------------------------------------------------------------------|
   |  This method will print the clients bill                                |
   =========================================================================*/
    public void printBill(){
        System.out.println(bill()); //print bill
    }
    
    /*========================================================================
   |  void addBalance(double payment)                                        |
   |-------------------------------------------------------------------------|
   |  double payment - value that is being added to the balance              |
   |-------------------------------------------------------------------------|
   |  This method will update the balance of the client                      |
   =========================================================================*/
    public void addBalance(double payment){
        this.balance += payment; //update balance
    }
   /*========================================================================
   |  String toString()                                                      |
   |-------------------------------------------------------------------------|
   |  Returns String - Client's information in string format                 |
   |-------------------------------------------------------------------------|
   |  This method will ureturn a string will all the clients information     |
   =========================================================================*/
    public String toString(){
        //return string with client type, client number, full name, and pet name
        return "Standard Client, Client Number: " + this.clientNumber +", Client Name: " + this.firstName +" " + this.lastName+" Pet Name: " + this.petName;
    }
    public int freeCheckupsLeft(){
        return this.freeCheckupsLeft;
    }
}