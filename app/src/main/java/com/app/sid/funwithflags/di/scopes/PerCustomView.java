package com.app.sid.funwithflags.di.scopes;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
public @interface PerCustomView {
}