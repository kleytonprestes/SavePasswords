package kleyton.com.br.savepasswords.main.contract;

import android.widget.ProgressBar;

import kleyton.com.br.savepasswords.base.BaseContract;

public class MainContract {

    public interface View extends BaseContract.View {

        void setError();
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void btnSignUpClick();

        void btnLoginClick(String userName, String password, ProgressBar progressBar);
    }
}
