package pinnacle.org.nidd.presenter;


import android.util.Log;

import javax.net.ssl.HttpsURLConnection;

import pinnacle.org.nidd.MVP;
import pinnacle.org.nidd.model.Login;
import pinnacle.org.nidd.model.Operator;
import pinnacle.org.nidd.utils.APIConstants;
import pinnacle.org.nidd.views.SignInActivity;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DOTECH on 04/05/2016.
 */
public final class SignInImpl implements SignInOps {
    private Operator operator;
    private SignInREST loginOps;
    private ResponseOPsCallback responseOPs;
    private Scheduler schedulers= Schedulers.newThread();

    /**
     * prevent external Instansiation
     */
    private SignInImpl(ResponseOPsCallback presenter){
        responseOPs=presenter;
        loginOps= APIConstants.createRetrofitService(SignInREST.class);
    }

    /**
     * static factory
     * @param presenter
     * @return
     */
    public static final SignInImpl getInstance(ResponseOPsCallback presenter){
        return new SignInImpl(presenter);
    }

    @Override
    public void signIn(String email, String password) {
        operator= new Operator.OperatorBuilder (null,password,email)
                .build();
        final Observable<Login> operatorObservable= loginOps.signIn(operator);
        operatorObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(schedulers)
        //get returned value of operator, or throw error if operator was not found
        .subscribe(new Subscriber<Login>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(e instanceof HttpException){
                    HttpException response=(HttpException)e;
                    Log.e(SignInActivity.class.getSimpleName(),response.code()+"");
                }
                responseOPs.onFailed(e);
            }

            @Override
            public void onNext(Login login) {
                if (login.getCode().equals(HttpsURLConnection.HTTP_OK))responseOPs.onSuccess(operator);
            }
        });
        return  ;
    }

    @Override
    public boolean signOut(Operator operator) {
        return false;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public void onCreate(MVP.GenericNiddPresenter view) {
        if(responseOPs==null)//just in case
            responseOPs= (ResponseOPsCallback) view;//cast the presenter to the callback
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        if(isChangingConfigurations)
            return;
        schedulers.createWorker().unsubscribe();
    }
}
