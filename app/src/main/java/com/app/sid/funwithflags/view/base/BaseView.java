
package com.app.sid.funwithflags.view.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void dispatchError(String error);

    void dispatchError(int resId);

}
