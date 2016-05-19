package pinnacle.org.nidd.utils;

/*import ch.halarious.core.HalResource;
import lukaround.net.lukie.hateous.HALConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;*/

import okhttp3.OkHttpClient;
import pinnacle.org.nidd.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** This non instantiable immutable class contains all the Generic API constants to be used.
 * It also encapsulates some API related Utilities
 * Created by Genius on 12/3/2015.
 */
public final class APIConstants {
    private static final String API_BASE="http://www.niddapp.com";
    private static final String API_VERSION="/account";
    public static final String API_RESOLVED_BASE=API_BASE+API_VERSION+"/";
    private static OkHttpClient okHttpClient = new OkHttpClient();


    /**
     * this endpoint describes operator related operations such as
     * add operator ,remove operator etc.
     */
    public static final class UserEndPoint{
        public static final String OPERATORS= "create";
        public static final String LOGIN=OPERATORS+"/token";
        public static final String LOGOUT=OPERATORS+"/logout";

        public static final class  Search{
            private static final String BASE=OPERATORS+"/search";
            public static final String  BY_Email=OPERATORS+"/email?userEmail={userEmail}";

            /**
             * prevent illegal access
             */
            private Search(){}
        }

        /**
         * prevent illegal access
         */
        private UserEndPoint(){}


    }


    public static <T> T createRetrofitService(final Class<T> clazz) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(APIConstants.API_RESOLVED_BASE)
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }
    /**
     * non instantiable constructor
     */
    private APIConstants(){
        throw new AssertionError("cannot instantiate this class");
    }


}
