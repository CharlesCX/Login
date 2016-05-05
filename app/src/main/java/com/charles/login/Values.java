package com.charles.login;

/**
 * Created by charles on 16/5/1.
 */
public class Values {
    public static final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String phonePattern = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
    public static final String DATABASE_NAME = "Users.db";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASS = "password";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_PHONE = "phone";

    public static final String ITEM_DATABASE_NAME = "Items.db";
    //public static final String ITEM_COLUMN_EMAIL = "email";
    public static final String ITEM_COLUMN_ITEM_NAME = "item";
    public static final String ITEM_COLUMN_QUANTITY = "quantity";
    public static final String ITEM_COLUMN_DONE = "done";
}
