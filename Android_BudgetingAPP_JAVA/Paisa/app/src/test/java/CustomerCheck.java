import android.content.Context;

import com.app.paisa.account.TestUserAccount;
import com.app.paisa.account.UserAccountDAO;
import com.app.paisa.account.UserAccountInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

public class CustomerCheck {
    Context context;
    String username;
    String password;

    UserAccountInterface account;
    @Before
    public void setup() {
        account = new TestUserAccount();
        context = null;
        username = "testuser";
        password = "abc";
    }

    /**
     * Checking whether a user account was created for the customer
     *
     */
    @Test
    public void addCustomerCheck() {

        long rows_inserted = account.addCustomer(null, username, password);
        Assert.assertEquals("row inserted", 1L, rows_inserted);
    }
    /**
     * Verifying whether the same person is able to log in using the details
     *
     */
    @Test
    public void authenticateCustomer() {

        boolean result=account.authenticateCustomer(null,username,password);
        Assert.assertTrue(result);

    }
}
