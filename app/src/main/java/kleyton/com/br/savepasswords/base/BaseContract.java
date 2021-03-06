package kleyton.com.br.savepasswords.base;

import android.content.Context;

public class BaseContract {

    public interface Presenter<V> {
        void attachView(V view);

        void detachView();
    }

    public interface View {
        Context getContext();
    }
}
