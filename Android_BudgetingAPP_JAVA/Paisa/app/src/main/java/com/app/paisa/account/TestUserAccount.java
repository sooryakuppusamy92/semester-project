package com.app.paisa.account;

import android.content.Context;

public class TestUserAccount implements UserAccountInterface{
    @Override
    public long addCustomer(Context Context, String Username, String Password) {
        return 0;
    }

    @Override
    public boolean authenticateCustomer(Context Context, String Username, String Password) {
        return false;
    }
}
