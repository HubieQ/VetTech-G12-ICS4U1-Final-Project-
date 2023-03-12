/*==================================================================
 |  PremiumClient.java                                              |
 |------------------------------------------------------------------|
 |  Author:  Hubert Qin                                             |
 |  Class:  ICS4U1                                                  |
 |  Last Modified:  June 23, 2021                                   |
 |------------------------------------------------------------------|
 |  This class manages Premium Clients and does operations          |
 |  concerning them. These include accepting payments, performing   |
 |  services and printing bills                                     |
  ==================================================================*/

class PremiumClient extends Client{
    final int YEARLY_FREE_GROOMING = 12; //constants
    final int YEARLY_FREE_LAB_TESTS = 12;
    final double PERCENT_DISCOUNT = 0.10;
    final double MEMBERSHIP_FEE = 29.99;
    final double INTEREST_RATE = 0.02;
    
    private int freeGroomingLeft; //instance fields
    private int freeLabTestsLeft;

    //constructor for premium client, fills in fields, calls on super class for other fields
    PremiumClient(int clientNumber, String firstName, String lastName, String petName, double balance, int freeGroomingLeft, int freeLabTestsLeft, double outstandingBalance){
        super(clientNumber, firstName, lastName, petName, balance, outstandingBalance); //fill fields
        this.freeGroomingLeft = freeGroomingLeft;
        this.freeLabTestsLeft = freeLabTestsLeft;
        this.outstandingBalance = outstandingBalance;
                
        
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
            + " Membership fee: $" + MEMBERSHIP_FEE 
            + " Free groomings left: " + this.freeGroomingLeft 
            + " Free lab tests left: " + this.freeLabTestsLeft 
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
            cost = 0;
        }
        else if(serviceName.equals("grooming")){ //grooming service
            
            if(this.freeGroomingLeft > 0){
                this.freeGroomingLeft--; //number
            }else{
                
                cost -= cost * PERCENT_DISCOUNT; //apply discount
                this.outstandingBalance += cost; //add cost to the bill
            }
            
        }else if(serviceName.equals("lab test")){ //lab tests
            if(this.freeLabTestsLeft > 0){
                this.freeLabTestsLeft--; //number of free lab tests decreases
            }else{
                
                cost -= cost * PERCENT_DISCOUNT; //apply discount
                this.outstandingBalance += cost; //add cost to the bill
            }
        }
        else if(serviceName.equals("surgery")){ //surgeries
            cost -= cost * PERCENT_DISCOUNT; //apply discount
            this.outstandingBalance += cost; //add cost to the bill
        }
        
        
    }
    /*========================================================================
   |  void resetServices()                                                   |
   |-------------------------------------------------------------------------|
   |  This method will reset the free services of the client to its maximum  |                                
   =========================================================================*/
    public void resetServices(){
        
        this.freeGroomingLeft = YEARLY_FREE_GROOMING; //reset groomings 
        
        this.freeLabTestsLeft = YEARLY_FREE_LAB_TESTS; //reset lab tests 
    }
   /*========================================================================
   |  void printBill()                                                       |
   |-------------------------------------------------------------------------|
   |  This method will print the clients bill                                |
   =========================================================================*/
    public void printBill(){
        System.out.println(bill()); //print out bill
    }
     /*========================================================================
   |  void addBalance(double payment)                                        |
   |-------------------------------------------------------------------------|
   |  double payment - value that is being added to the balance              |
   |-------------------------------------------------------------------------|
   |  This method will update the balance of the client                      |
   =========================================================================*/
    public void addBalance(double payment){
        this.balance += payment; //add payment
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
        return "Premium Client, Client Number: " + this.clientNumber +", Client Name: " + this.firstName +" " + this.lastName+" Pet Name: " + this.petName;
    }
    public int freeGroomingLeft(){
        return this.freeGroomingLeft;
    }
    public int freeLabTestsLeft(){
        return this.freeLabTestsLeft;
    }
}