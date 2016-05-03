package pinnacle.org.nidd.utils;

/*import ch.halarious.core.HalResource;
import lukaround.net.lukie.hateous.HALConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;*/

/** This non instantiable immutable class contains all the Generic API constants to be used.
 * It also encapsulates some API related Utilities
 * Created by Genius on 12/3/2015.
 */
public final class APIConstants {
    private static final String API_BASE="http://lukaround.cfapps.io";
    private static final String API_VERSION="/lukaround/api/v1/";
    public static final String API_RESOLVED_BASE=API_BASE+API_VERSION;
    /**
     * this endpoint describes operator related operations such as
     * add operator ,remove operator etc.
     */
    public static final class UserEndPoint{
        public static final String USERS= "users";
        public static final String USERS_INTEREST= USERS+"/interest";
        public static final String REGISTER=USERS+"/";
        public static final String LOGIN=USERS+"/login";
        public static final String LOGOUT=USERS+"/logout";

        public static final class  Search{
            private static final String BASE=USERS+"/search";
            public static final String  BY_NAME=USERS+"/name?userName={userName}";
            public static final String  BY_Email=USERS+"/email?userEmail={userEmail}";
            public static final String  AROUND=USERS+"/around?point={latitude,longitude}&distance={distance}";

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

    public static final class  InterestEndpoint{
        public static final String INTERESTS= "interests";
        public static final class  Search{
            private static final String BASE=INTERESTS+"/search";
            public static final String  BY_NAME=INTERESTS+"/name?interestName={interestName}";

            /**
             * prevent illegal access
             */
            private Search(){}
        }

        /**
         * prevent illegal access
         */
        private InterestEndpoint(){}
    }

   /* public static <T> T createRetrofitService(final Class<T> clazz, final Class<? extends HalResource> pagerResource) {
        final Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(APIConstants.API_RESOLVED_BASE)
                .addConverterFactory( HALConverterFactory.create(pagerResource))
                .addConverterFactory( GsonConverterFactory.create())
                .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
                .build();

        T service = restAdapter.create(clazz);

        return service;
    }*/
    /**
     * non instantiable constructor
     */
    private APIConstants(){
        throw new AssertionError("cannot instantiate this class");
    }


}
