package com.app.sid.funwithflags.data.database.loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.app.sid.funwithflags.data.database.DatabaseManager;
import com.app.sid.funwithflags.data.database.schema.CountriesTableSchema;
import com.app.sid.funwithflags.datasets.remote.Countries;
import com.app.sid.funwithflags.datasets.remote.SelectedCountry;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class CountriesDataLoader extends AbstractDTO<Countries> {

    private DatabaseManager mInstance;
    private String[] mColumns;
    private Func1<Cursor, Countries> mapperFunction;

    public CountriesDataLoader() {
        this.mInstance = DatabaseManager.getInstance();
        this.mColumns = this.getColumns();
        mapperFunction = new Func1<Cursor, Countries>() {
            @Override
            public Countries call(Cursor cursor) {
                return getSumOfPart(cursor);
            }
        };
    }

    @NonNull
    private Countries getSumOfPart(@NonNull Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(CountriesTableSchema.ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NAME));
        String alpha_2_code = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_ALPHA_2_CODE));
        String alpha_3_code = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_ALPHA_3_CODE));
        String c_capital = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_CAPITAL));
        String c_region = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_REGION));
        String c_sub_region = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_SUB_REGION));
        String population = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_POPUlATION));
        String demonym = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_DEMONYM));
        String area = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_AREA));
        String nativename = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NATIVE_NAME));
        String numericcode = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NUMERIC_CODE));
        String flag = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_FLAG));

        String lang = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_LANG));
        String currency = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_CURRENCY));
        String domain = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_INTERNET_DOMAIN));
        String phonecode = cursor.getString(cursor.getColumnIndexOrThrow(CountriesTableSchema.C_PHONE_CODE));

        Countries country = new Countries();
        country.setId(id);
        country.setName(name);
        country.setAlpha2Code(alpha_2_code);
        country.setAlpha3Code(alpha_3_code);
        country.setCapital(c_capital);
        country.setRegion(c_region);
        country.setSubregion(c_sub_region);
        country.setPopulation(population);
        country.setDemonym(demonym);
        country.setArea(area);
        country.setNativeName(nativename);
        country.setNumericCode(numericcode);
        country.setFlag(flag);
        country.setLang(lang);
        country.setCurrency(currency);
        country.setDomain(domain);
        country.setPhonecode(phonecode);
        return country;
    }

    @Override
    public long insert(Countries entity, final String tableName) {
        return this.mInstance.add(this.getContentValues(entity),
                CountriesTableSchema.TABLE);
    }

    @Override
    public long delete(Countries entity) {
        String aplhaCode = entity.getAlpha2Code();
        String selection = " " + CountriesTableSchema.C_ALPHA_2_CODE + " =?  ";
        return this.mInstance.delete(CountriesTableSchema.TABLE, selection,
                new String[]{String.valueOf(aplhaCode)});
    }

    @Override
    public long delete(String tableName) {
        return this.mInstance.delete(CountriesTableSchema.TABLE, null, null);
    }

    @Override
    public long update(Countries entity) {
        String selection = " " + CountriesTableSchema.C_ALPHA_2_CODE + " =? "
                + "AND"
                + " " + CountriesTableSchema.C_NAME + " =? ";

        return this.mInstance.update(CountriesTableSchema.TABLE,
                getContentValues(entity),
                selection,
                new String[]{
                        entity.getAlpha2Code(),
                        entity.getName()
                });
    }

    @Override
    public Observable<List<Countries>> getAllCountries() {
        String sql = "Select * from " + CountriesTableSchema.TABLE;
        return this.mInstance.read(CountriesTableSchema.TABLE, sql, mapperFunction);
    }

    @Override
    public Observable<List<Countries>> getAllCountries(Countries obj) {
        String sql = String.format("SELECT * FROM %s WHERE %s LIKE ? AND %s LIKE ?",
                CountriesTableSchema.TABLE, CountriesTableSchema.C_NAME, CountriesTableSchema.C_ALPHA_2_CODE);
        return mInstance.read(CountriesTableSchema.TABLE, sql, mapperFunction, obj.getName(), obj.getAlpha2Code());
    }

    public Observable<Countries> getCountry(SelectedCountry obj) {
        String sql = String.format("SELECT * FROM %s WHERE %s LIKE ? AND %s LIKE ?",
                CountriesTableSchema.TABLE, CountriesTableSchema.C_NAME, CountriesTableSchema.C_ALPHA_2_CODE);
        return mInstance.readSingle(CountriesTableSchema.TABLE, sql, mapperFunction, obj.getName(), obj.getAlpha2Code());
    }

    @Override
    public ArrayList<Countries> read() {
        Cursor resultSet = null;
        ArrayList<Countries> list = null;
        resultSet = this.mInstance.read(CountriesTableSchema.TABLE,
                this.mColumns);
        if (null != resultSet) {
            list = this.getListFromCursor(resultSet);
        }
        return list;
    }

    @Override
    public ArrayList<Countries> read(String selection,
                                     String[] selectionArgs, String groupBy, String having,
                                     String orderBy) {
        Cursor resultSet = null;
        ArrayList<Countries> list = null;
        resultSet = this.mInstance.read(CountriesTableSchema.TABLE,
                this.mColumns, selection, selectionArgs, groupBy, having,
                orderBy);
        if (resultSet != null) {
            list = this.getListFromCursor(resultSet);
        }
        return list;
    }

    @Override
    public ContentValues getContentValues(Countries entity) {
        if (null == entity) {
            return null;
        }
        ContentValues values = new ContentValues();
        values.put(CountriesTableSchema.C_NAME, entity.getName());
        values.put(CountriesTableSchema.C_ALPHA_2_CODE, entity.getAlpha2Code());
        values.put(CountriesTableSchema.C_ALPHA_3_CODE, entity.getAlpha3Code());
        values.put(CountriesTableSchema.C_CAPITAL, entity.getCapital());
        values.put(CountriesTableSchema.C_REGION, entity.getRegion());
        values.put(CountriesTableSchema.C_SUB_REGION, entity.getSubregion());
        values.put(CountriesTableSchema.C_POPUlATION, entity.getPopulation());
        values.put(CountriesTableSchema.C_DEMONYM, entity.getDemonym());
        values.put(CountriesTableSchema.C_AREA, entity.getArea());
        values.put(CountriesTableSchema.C_NATIVE_NAME, entity.getNativeName());
        values.put(CountriesTableSchema.C_NUMERIC_CODE, entity.getNumericCode());
        values.put(CountriesTableSchema.C_FLAG, entity.getFlag());
        values.put(CountriesTableSchema.C_LANG, entity.getLang());
        values.put(CountriesTableSchema.C_CURRENCY, entity.getCurrency());
        values.put(CountriesTableSchema.C_INTERNET_DOMAIN, entity.getDomain());
        values.put(CountriesTableSchema.C_PHONE_CODE, entity.getPhonecode());
        return values;
    }

    @Override
    public ArrayList<Countries> getListFromCursor(Cursor cursor) {
        if (null == cursor) {
            return null;
        }
        int id = cursor.getColumnIndexOrThrow(CountriesTableSchema.ID);
        int name = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NAME);
        int alpha_2_code = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_ALPHA_2_CODE);
        int alpha_3_code = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_ALPHA_3_CODE);
        int capital = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_CAPITAL);
        int region = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_REGION);
        int sub_region = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_SUB_REGION);
        int population = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_POPUlATION);
        int denoym = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_DEMONYM);
        int area = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_AREA);
        int native_name = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NATIVE_NAME);
        int numeric_code = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_NUMERIC_CODE);
        int flag = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_FLAG);

        int lang = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_LANG);
        int currency = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_CURRENCY);
        int netDomain = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_INTERNET_DOMAIN);
        int phCode = cursor.getColumnIndexOrThrow(CountriesTableSchema.C_PHONE_CODE);

        ArrayList<Countries> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Countries country = new Countries();
                country.setId(cursor.getInt(id));
                country.setName(cursor.getString(name));
                country.setAlpha2Code(cursor.getString(alpha_2_code));
                country.setAlpha3Code(cursor.getString(alpha_3_code));
                country.setCapital(cursor.getString(capital));
                country.setRegion(cursor.getString(region));
                country.setSubregion(cursor.getString(sub_region));
                country.setPopulation(cursor.getString(population));
                country.setDemonym(cursor.getString(denoym));
                country.setArea(cursor.getString(area));
                country.setNativeName(cursor.getString(native_name));
                country.setNumericCode(cursor.getString(numeric_code));
                country.setFlag(cursor.getString(flag));
                country.setLang(cursor.getString(lang));
                country.setCurrency(cursor.getString(currency));
                country.setDomain(cursor.getString(netDomain));
                country.setPhonecode(cursor.getString(phCode));
                list.add(country);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    @Override
    public String[] getColumns() {
        return new String[]{
                CountriesTableSchema.ID,
                CountriesTableSchema.C_NAME,
                CountriesTableSchema.C_ALPHA_2_CODE,
                CountriesTableSchema.C_ALPHA_3_CODE,
                CountriesTableSchema.C_CAPITAL,
                CountriesTableSchema.C_REGION,
                CountriesTableSchema.C_SUB_REGION,
                CountriesTableSchema.C_POPUlATION,
                CountriesTableSchema.C_DEMONYM,
                CountriesTableSchema.C_AREA,
                CountriesTableSchema.C_NATIVE_NAME,
                CountriesTableSchema.C_NUMERIC_CODE,
                CountriesTableSchema.C_FLAG,
                CountriesTableSchema.C_LANG,
                CountriesTableSchema.C_CURRENCY,
                CountriesTableSchema.C_INTERNET_DOMAIN,
                CountriesTableSchema.C_PHONE_CODE,
        };
    }
}

