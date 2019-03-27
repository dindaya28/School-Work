/**
 * TestBankAccounts.java: This class will test aspects of inheritance for the
 * BankAccount class and its subclasses.
 * 
 * @author CS027b 2007
 */

// The toString method in CheckingAccount demonstrates overriding

public class TestBankAccounts {

	public static void main(String[] args) {

		BankAccount bacc0 = new BankAccount(0);
		System.out.println(bacc0.toString());

		BankAccount bacc1 = new BankAccount(5000);
		System.out.println(bacc1.toString());

		CheckingAccount chacc1 = new CheckingAccount(500.0);
		System.out.println(chacc1.toString());

		SavingsAccount sacc1 = new SavingsAccount(1000.0, 1.0);
		System.out.println(sacc1.toString());

		// -------------------------------------------------------
		// add your code here

		bacc0 = chacc1; // Yes, the line doesn't affect any of the previous code at the moment
		System.out.println(bacc0.toString()); // Used toString method from CheckingAccount because bacc0 now refers to a
												// CheckingAccount
		// chacc1 = bacc1; //Cannot compile because checking cannot refer to a bank
		// account since it inherits from BankAccount
		BankAccount bacc2 = new CheckingAccount(200.0);
		chacc1 = ((CheckingAccount) bacc2);

		// bacc0.deductFees();
		chacc1.deductFees(); // only CheckingAccount objects work as it is the only object that contains the
								// deductFees method
		// sacc1.deductFees();

		chacc1.deposit(100.0); // StackOverFlowError, the CheckingAccount class doesn't have a deposit method
								// and so can't run the desired method

	}

}
