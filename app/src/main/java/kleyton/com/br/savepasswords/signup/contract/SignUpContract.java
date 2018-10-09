package kleyton.com.br.savepasswords.signup.contract;

import kleyton.com.br.savepasswords.base.BaseContract;

public class SignUpContract {

    public interface View extends BaseContract.View {

        void setError(String message);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void signUp(String email, String name, String password);
    }
}
