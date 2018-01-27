package com.app.sid.funwithflags.di.components;

import com.app.sid.funwithflags.di.modules.ApplicationModule;
import com.app.sid.funwithflags.di.modules.RealmManagerModule;
import com.app.sid.funwithflags.di.provider.ApplicationBaseComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sidhanshu Udawat on 27-Jan-18.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RealmManagerModule.class})
public interface ApplicationComponent extends ApplicationBaseComponent {

}
