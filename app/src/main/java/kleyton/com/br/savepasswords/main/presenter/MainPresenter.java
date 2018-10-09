package kleyton.com.br.savepasswords.main.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import kleyton.com.br.savepasswords.api.SyncInterface;
import kleyton.com.br.savepasswords.main.contract.MainContract;
import kleyton.com.br.savepasswords.main.model.MainRequest;
import kleyton.com.br.savepasswords.passwordslist.view.PasswordListActivity;
import kleyton.com.br.savepasswords.signup.model.User;
import kleyton.com.br.savepasswords.signup.view.SignUpActivity;

public class MainPresenter implements MainContract.Presenter, SyncInterface {

    MainContract.View view;
    MainRequest request;

    public MainPresenter(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    ProgressBar progressBar;

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void btnSignUpClick() {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void btnLoginClick(String email, String password, ProgressBar progressBar) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        request = new MainRequest(this, user);
        request.startSync();
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onSuccessSync() {
        Intent intent = new Intent(view.getContext(), PasswordListActivity.class);
        view.getContext().startActivity(intent);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailureSync() {
        view.setError();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
