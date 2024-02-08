import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



import com.app.paisa.account.UserAccount;
import com.app.paisa.account.UserAccountDAO;
import com.app.paisa.account.UserAccountInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class CustomerCheck2 {
    /*

    private UserAccountInterface account;
    private UserAccountDAO helper;

    @Mock
    Context mockContext;
    @Mock
    SQLiteDatabase mockDb;
    @Before
    public void onSetup() {
        //MockitoAnnotations.openMocks(this);

        mockContext = null;
        //androidx.test:core:1.5.0@aar

        helper = new UserAccountDAO(mockContext);

        // Mock the behavior of SQLiteDatabase openHelper.getWritableDatabase()
        Mockito.when(helper.getWritableDatabase()).thenReturn(mockDb);
    }




    @Test
    public void addCustomerCheck() {

        //Context Context= null;
        String Username="testuser";
        String password="abc";

        // Mock the behavior of UserAccountDAO
        Mockito.when(helper.getWritableDatabase()).thenReturn(mockDb);

        // Mock the behavior of SQLiteDatabase insert method
        Mockito.when(mockDb.insert(eq("useraccount"), eq(null), any(ContentValues.class)))
                .thenReturn(1L);

         account=new UserAccount();
        long result =account.addCustomer(mockContext,Username,password);
        Assert.assertEquals(1L,result);

        //boolean result=account.authenticateCustomer(Username,password);
        //Assert.assertTrue(result);
    }
*/
}