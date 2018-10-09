package kleyton.com.br.savepasswords.signup.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kleyton.com.br.savepasswords.api.SyncInterface;
import kleyton.com.br.savepasswords.passwordslist.view.PasswordListActivity;
import kleyton.com.br.savepasswords.signup.contract.SignUpContract;
import kleyton.com.br.savepasswords.signup.model.SignUpRequest;
import kleyton.com.br.savepasswords.signup.model.User;

public class SignUpPresenter implements SignUpContract.Presenter, SyncInterface {

    SignUpContract.View view;
    SignUpRequest request;

    public SignUpPresenter(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    ProgressBar progressBar;

    @Override
    public void attachView(SignUpContract.View view) {
        this.view = view;
    }


    @Override
    public void detachView() {
        this.view = null;
    }

    private boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    @Override
    public void signUp(String email, String name, String password) {

        if (password.length() >= 10 && isValidPassword(password)) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            progressBar.setVisibility(View.VISIBLE);
            request = new SignUpRequest(this, user);
            request.startSync();
        } else {
            view.setError("Senha inválida");
        }
    }

    @Override
    public void onSuccessSync() {
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(view.getContext(), PasswordListActivity.class);
        view.getContext().startActivity(intent);

    }

    @Override
    public void onFailureSync() {
        progressBar.setVisibility(View.INVISIBLE);
        view.setError("Verifique os dados e tente novamente");
    }
}
