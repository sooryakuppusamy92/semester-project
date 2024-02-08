import android.content.Context;

import com.app.paisa.account.TestUserAccount;
import com.app.paisa.budget.BudgetManager;
import com.app.paisa.budget.CustomerBudget;
import com.app.paisa.budget.TestCustomerBudget;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BudgetCheck {
    float budgetAmount= 100;
    String month="april";
    String customerName="testuser";
    BudgetManager budget;
    @Before
    public void setup() {
        budget=new TestCustomerBudget();
        budgetAmount= 100;
        month="april";
        customerName="testuser";
    }

    /**
     * Checking whether the customer is able to create a budget.
     *
     */
    @Test
    public void addCustomerBudgetCheck() {
        long budget_row=budget.addBudget(null,budgetAmount,month,customerName);
        Assert.assertEquals("Budget row created",1L,budget_row);
    }

    /**
     * Checking whether the customer is able to add his expense.
     *
     */
    @Test
    public void addCustomerExpenseCheck() {
        String item="food";
        float itemAmount=99;

        long expense_row=budget.addExpense(null,item,itemAmount,month,customerName);
        Assert.assertEquals("Budget row created",1L,expense_row);
    }

    /**
     * Checking whether the customer is able to view the amount left from the budget.
     *
     */

    @Test
    public void getBalanceLeftCheck() {
        float balanceAmount=budget.getBalanceLeft(null,customerName,month);
        Assert.assertEquals("balance left",1,balanceAmount,0);
    }
    /**
     * Retrieving budget details for the customer
     *
     */
    @Test
    public void retrieveBudgetDetailCheck() {

        TestCustomerBudget Customer=new TestCustomerBudget();
        Customer.customerName="testuser";
        Customer.Month="april";
        Customer.budgetAmount=100;
        Customer.balanceLeft=1;

        float expectedbudgetamount=Customer.budgetAmount;
        float expectedbudgetbalance=Customer.balanceLeft;

        //Calling the getbudget API
        TestCustomerBudget testCustomer1=new TestCustomerBudget();
        TestCustomerBudget testCustomer=testCustomer1.getBudget1(null,"testuser","april");

        //Retrieve each value inside the object
        float actualbudgetamount=testCustomer.budgetAmount;
        float actualbalanceamount=testCustomer.balanceLeft;

        //writing test case
        Assert.assertEquals(expectedbudgetamount,actualbudgetamount,0);
        Assert.assertEquals(expectedbudgetbalance,actualbalanceamount,0);
    }






}
