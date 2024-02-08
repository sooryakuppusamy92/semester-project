import static org.junit.Assert.assertEquals;

import com.app.paisa.account.UserAccount;
import com.app.paisa.account.UserAccountDAO;
import com.app.paisa.account.UserAccountInterface;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 34
)
public class test{
    /*
    private UserAccountDAO dbHelper;

    @Before
    public void setup() {
        dbHelper = new UserAccountDAO(RuntimeEnvironment.application);
        //dbHelper.clearDbAndRecreate();
    }

    @Test
    public void testDbInsertion() throws Exception {
        // Given
        String Username="testuser";
        String password="abc";

        UserAccountInterface account=new UserAccount();
        long result =account.addCustomer(RuntimeEnvironment.application,Username,password);
        Assert.assertEquals(1L,result);
    }

    @After
    public void tearDown() {
       // dbHelper.clearDb();
    }
    */

}