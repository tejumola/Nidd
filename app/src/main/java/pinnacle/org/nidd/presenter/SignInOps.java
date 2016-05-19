package pinnacle.org.nidd.presenter;


import pinnacle.org.nidd.MVP;
import pinnacle.org.nidd.model.Login;
import pinnacle.org.nidd.model.Operator;
import pinnacle.org.nidd.utils.APIConstants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by DOTECH on 28/04/2016.
 */
public interface SignInOps extends MVP.GenericNiddModelsOps{
    void signIn(String email,String password);
    boolean signOut(Operator operator);
    Operator getOperator();


    /**
     * this defines the Retrofits REST Operations relating to Signin
     */
    interface SignInREST{
        @POST(APIConstants.UserEndPoint.LOGIN)
        Observable<Login> signIn(@Body Operator operator);

        @GET(APIConstants.UserEndPoint.Search.BY_Email)
        Call<Operator> getOperator(@Path("userEmail") String userEmail);
    }

}
