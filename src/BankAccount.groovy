/**
 * Created by Hiro on 30/07/2016.
 */
class BankAccount {
    private balance

    BankAccount(openningBalance){
        balance = openningBalance
    }

    def void deposit(amount) {
        balance += amount
    }

    def void withdraw (amount) {
        if (amount > balance) {
            throw new BankException()
        }
        balance -= amount
    }

    def void accrueInterest() {
        def service = new InterestRateService()
        def rate = service.getInterestRate()

        def accruedInterest = balance * rate

        deposit(accruedInterest)
    }
}
