package com.app.paisa.account;

import android.content.Context;

public interface UserAccountInterface {
    long addCustomer(Context context, String userName, String password);
    boolean authenticateCustomer(Context context,String userName, String password);
}
