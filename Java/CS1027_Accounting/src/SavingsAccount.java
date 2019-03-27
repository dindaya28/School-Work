/**
 * SavingsAccount that extends BankingAccount
 * 
 * @author Dinushan Dayarathna
 */
public class SavingsAccount extends BankAccount {
	double interestRate;
	double newBalance;

	/**
	 * constructor for SavingsAccount
	 * 
	 * @param initialAmount,
	 *            rate
	 * @param rate
	 */
	public SavingsAccount(double initialAmount, double rate) {
		super(initialAmount);
		super.getBalance();
		interestRate = rate;
	}

	/**
	 * accessor method for interestRate
	 * 
	 * @return
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * Calculates the interest for one month and adds it to the balance
	 */
	public void calculateInterest() {
		newBalance = super.getBalance() + (super.getBalance() * interestRate);
	}

	/**
	 * Methods returns the balance and interestRate as a String
	 * 
	 * @return
	 */
	public String toString() {
		return "Savings Account: balance: " + newBalance + ", interest rate: " + interestRate;
	}

}
