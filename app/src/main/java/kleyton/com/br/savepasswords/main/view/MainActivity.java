package kleyton.com.br.savepasswords.main.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import kleyton.com.br.savepasswords.R;
import kleyton.com.br.savepasswords.main.contract.MainContract;
import kleyton.com.br.savepasswords.main.presenter.MainPresenter;


public class MainActivity  extends AppCompatActivity implements MainContract.View, View.OnClickListener {

    MainContract.Presenter presenter ;

    private EditText userName;
    private EditText userPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();
        presenter.attachView(this);

    }

    private void initViews() {
        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progress);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        presenter = new MainPresenter(progressBar);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                presenter.btnLoginClick(userName.getText().toString(), userPassword.getText().toString(), progressBar);

                break;
            case R.id.btn_sign_up:
                presenter.btnSignUpClick();
                break;
        }
    }

    @Override
    public void setError() {
        Snackbar.make(btnSignUp, "Verifique os dados e tente novamente", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
