package com.app.sid.funwithflags.data.database.schema;

public final class CountriesTableSchema {

    private CountriesTableSchema() {
    }

    public static final String TABLE = "CountriesTable";

    public static final String ID = "id";

    public static final String C_NAME = "name";

    public static final String C_ALPHA_2_CODE = "alpha2Code";

    public static final String C_ALPHA_3_CODE = "alpha3Code";

    public static final String C_CAPITAL = "c_capital";

    public static final String C_REGION = "c_region";

    public static final String C_SUB_REGION = "c_subregion";

    public static final String C_POPUlATION = "c_population";

    public static final String C_DEMONYM = "c_demonym";

    public static final String C_AREA = "c_area";

    public static final String C_NATIVE_NAME = "c_nativeName";

    public static final String C_NUMERIC_CODE = "c_numericCode";

    public static final String C_FLAG = "c_flag";

    public static final String C_LANG = "c_language";

    public static final String C_CURRENCY = "c_currency";

    public static final String C_INTERNET_DOMAIN = "c_internet_domain";

    public static final String C_PHONE_CODE = "c_phone_code";


    public static final String CREATE_TABLE = "create table " + TABLE + "( "
            + ID + " INTEGER primary key AUTOINCREMENT NOT NULL,"
            + " " + C_NAME + " TEXT,"
            + " " + C_ALPHA_2_CODE + " TEXT,"
            + " " + C_ALPHA_3_CODE + " TEXT,"
            + " " + C_CAPITAL + " TEXT,"
            + " " + C_REGION + " TEXT,"
            + " " + C_SUB_REGION + " TEXT,"
            + " " + C_POPUlATION + " TEXT,"
            + " " + C_DEMONYM + " TEXT,"
            + " " + C_AREA + " TEXT,"
            + " " + C_NATIVE_NAME + " TEXT,"
            + " " + C_NUMERIC_CODE + " TEXT,"
            + " " + C_FLAG + " TEXT,"
            + " " + C_LANG + " TEXT,"
            + " " + C_CURRENCY + " TEXT,"
            + " " + C_INTERNET_DOMAIN + " TEXT,"
            + " " + C_PHONE_CODE + " TEXT"
            + ")" + ";";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE;
}
