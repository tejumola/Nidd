package pinnacle.org.nidd.presenter;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import pinnacle.org.nidd.MVP;
import pinnacle.org.nidd.R;
import pinnacle.org.nidd.model.Operator;
import pinnacle.org.nidd.utils.ConnectionHelper;
import pinnacle.org.nidd.utils.ValidationUtils;
import pinnacle.org.nidd.views.MainActivity;
import pinnacle.org.nidd.views.SignInActivity;
import pttextview.widget.PTEditText;
import pinnacle.org.nidd.R.id;

/**
 * Created by DOTECH on 05/05/2016.
 */
public class SignInPresenter implements MVP.GenericNiddPresenter<Operator,SignInActivity>,SignInOps.ResponseOPsCallback{
    private final SignInOps signInOps;
    private WeakReference<SignInActivity> view;
    private EditText operatorEmailEditText,operatorPasswordEditText;
    private AppCompatButton signInButton;


    /**
     * to allow for reflective instantiation by Class<?>instantiator
     *
     */
    public SignInPresenter(){
        this.signInOps=SignInImpl.getInstance(this);
    }

    public SignInOps getSignInOps() {
        return this.signInOps;
    }

    @Override
    public SignInActivity getView() {
        return this.view.get();
    }

    @Override
    public Operator getModel() {
        return this.getSignInOps().getOperator();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(SignInActivity view) {
        this.setViews(view);
    }

    @Override
    public void onConfigurationChange(SignInActivity view) {
        this.view= new WeakReference<>( view );

    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        if(!isChangingConfigurations)
            view =null;
    }

    @Override
    public void goToNextActivity(Class<? extends Activity> activityClass) {
        SignInActivity view = this.getView ();
        view.goToNextActivity(activityClass, null,null);
    }



    @Override
    public void onSuccess(Operator operator) {
        this.getView ().showShortToast(operator.getUserName ()+" Signed In successfully");
        this.signInButton.setEnabled(false);
        this.getView ().setResult(Activity.RESULT_OK, null);
        this.getView ().finish();
        this.goToNextActivity (MainActivity.class);
        Log.e("Mainactivity","login");
    }


    @Override
    public void onFailed(Throwable cause) {
        this.getView ().showShortToast("Login failed: "+ cause);
        this.signInButton.setEnabled(true);
    }

    private void setViews(SignInActivity view) {
        this.view= new WeakReference< > ( view );
        this.operatorPasswordEditText = (EditText) view.findViewById( id.password);
        this.operatorEmailEditText = (EditText) view.findViewById(id.email);
        this.signInButton =(AppCompatButton)view.findViewById( id.email_sign_in_button);
        this.signInButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] details= SignInPresenter.this.getDetails ();
                String userEmail=details[0];
                String userPassword=details[1];

                if(!SignInPresenter.this.validateDetails (userEmail,userPassword)) {
                    return;
                }
                SignInPresenter.this.signInAsyncOperation (userEmail,userPassword);
            }
        });
    }

    private void signInAsyncOperation(String userEmail, String userPassword) {
        if (ConnectionHelper.isConnectedOrConnecting(getView())){
             this.getSignInOps ().signIn(userEmail,userPassword);}
        else
            Toast.makeText(getView(),"No Internet Connection",Toast.LENGTH_SHORT).show();
    }


    private String[] getDetails(){
        return new String[]{
                this.operatorEmailEditText.getText().toString(),
                this.operatorPasswordEditText.getText().toString()
        };
    }
    private boolean validateDetails( String userEmail, String userPassword) {

        if (this.validateEmail ( userEmail )) return false;
        return !this.validatePassword ( userPassword );

    }
    private boolean validateEmail(String userEmail) {
        if(!ValidationUtils.INSTANCE.isValidEmail(userEmail)){
            this.operatorEmailEditText.setError("email is invalid");
            return true;
        }else
            this.resetErrorEditText ( this.operatorEmailEditText );
        return false;
    }


    private void resetErrorEditText(EditText editText) {
        editText.setError(null);
        editText.clearFocus();
    }

    private boolean validatePassword(String userPassword) {
        //test password
        if(!ValidationUtils.INSTANCE.isValidPassword(userPassword)){
            boolean whitepaceReason=ValidationUtils.INSTANCE.containsWhiteSpace (userPassword);
            String reason="password cannot "+(whitepaceReason? "contain space"
                    :"be less than 6 characters");
            this.operatorPasswordEditText.setError(reason);
            return true;
        }else
            this.resetErrorEditText ( this.operatorPasswordEditText );
        return false;
    }
    //blue ocean strategy
    //quickbooks




}
