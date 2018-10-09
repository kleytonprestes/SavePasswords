package kleyton.com.br.savepasswords.signup.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import kleyton.com.br.savepasswords.R;
import kleyton.com.br.savepasswords.signup.contract.SignUpContract;
import kleyton.com.br.savepasswords.signup.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View, View.OnClickListener {

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private Button btnSignUp;


    private ProgressBar progressBar;

    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initViews() {
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progress);
        presenter = new SignUpPresenter(progressBar);

        btnSignUp.setOnClickListener(this);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        presenter.signUp(userEmail.getText().toString(), userName.getText().toString(), userPassword.getText().toString());

    }

    @Override
    public void setError(String message) {
        Snackbar.make(userPassword, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
