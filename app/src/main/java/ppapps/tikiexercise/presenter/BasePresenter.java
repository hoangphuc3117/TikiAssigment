package ppapps.tikiexercise.presenter;

public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();

}
