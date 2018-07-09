package ada.osc.myfirstweatherapp.networking;

public interface OnResponseListener<T> {

    void onSuccess(T response);

    void onFailure(Throwable throwable);

}
