package com.app.sid.funwithflags.di;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Scope;

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}