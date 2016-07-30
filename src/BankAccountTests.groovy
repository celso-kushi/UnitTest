import groovy.mock.interceptor.MockFor
import groovy.mock.interceptor.StubFor

/**
 * Created by Hiro on 30/07/2016.
 */
class BankAccountTests extends GroovyTestCase {

    private account

    def void setUp() {
        account = new BankAccount(10)
    }

    def void tearDown() {
        account = null
    }

    def void testCanDepositMoney() {
        account.deposit(5)
        assert 15 == account.balance
    }

    def void testCanWithdrawMoney() {
        account.withdraw(5)
        assert 5 == account.balance
    }

    def void testCanNotWithdrawMoreMoneyThanBanlance() {
        shouldFail(BankException) {
            account.withdraw(15)
        }
    }

    def void testCanAccrueInterest() {
        //Manipulando o retorno da classe Service
        def service = new MockFor(InterestRateService)
        service.demand.getInterestRate() {
            return 0.10
        }
        //Usando o valor do retorno manipulado
        service.use {
            account.accrueInterest()
            assert 11 == account.balance
        }
    }
}
