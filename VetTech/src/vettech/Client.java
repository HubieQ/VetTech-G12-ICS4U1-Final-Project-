/*==================================================================
 |  Client.java                                                     |
 |------------------------------------------------------------------|
 |  Author:  Hubert Qin                                             |
 |  Class:  ICS4U1                                                  |
 |  Last Modified:  June 23, 2021                                   |
 |------------------------------------------------------------------|
 |  This abstract class manages the template methods for its child  |
 |  classes PremiumClient and StandardClient and ties them together |
 |  with fields and methods that client account should have such as |
 |  personal information and charging money for services            |
  ==================================================================*/
abstract class Client{
    protected int clientNumber; //intialize fields
    protected String firstName;
    protected String lastName;
    protected String petName;
    protected double balance;
    protected double outstandingBalance;
    //constructor
    Client(int clientNumber, String firstName, String lastName, String petName, double balance, double outstandingBalance){
        this.clientNumber = clientNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.petName = petName;
        this.balance = balance;
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
    public abstract String bill();
    
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
    public abstract boolean acceptPayment(double payment);
    
   /*========================================================================
   |  void chargeForServices(String serviceName, double cost)                |
   |-------------------------------------------------------------------------|
   |  String serviceName - name of the service being provided to the client  |
   |  double cost - price of the service                                     |
   |-------------------------------------------------------------------------|
   |  This method will charge the client for a service, or deduct the number |
   |  of free services remaining if applicable                               |
   =========================================================================*/
    public abstract void chargeForServices(String serviceName, double cost);
    
     /*========================================================================
   |  void resetServices()                                                   |
   |-------------------------------------------------------------------------|
   |  This method will reset the free services of the client to its maximum  |                                
   =========================================================================*/
    public abstract void resetServices();
    
     /*========================================================================
   |  void printBill()                                                       |
   |-------------------------------------------------------------------------|
   |  This method will print the clients bill                                |
   =========================================================================*/
    public abstract void printBill();
    
    /*========================================================================
   |  void addBalance(double payment)                                        |
   |-------------------------------------------------------------------------|
   |  double payment - value that is being added to the balance              |
   |-------------------------------------------------------------------------|
   |  This method will update the balance of the client                      |
   =========================================================================*/
    public abstract void addBalance(double payment);
    
    /*========================================================================
   |  String toString()                                                      |
   |-------------------------------------------------------------------------|
   |  Returns String - Client's information in string format                 |
   |-------------------------------------------------------------------------|
   |  This method will ureturn a string will all the clients information     |
   =========================================================================*/
    public abstract String toString();
    
}