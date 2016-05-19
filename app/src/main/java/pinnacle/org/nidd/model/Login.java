package pinnacle.org.nidd.model;

/**
 * Created by DOTECH on 14/05/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

public class Login {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("body")
        @Expose
        private Body body;

        /**
         *
         * @return
         * The code
         */
        public Integer getCode() {
            return code;
        }

        /**
         *
         * @param code
         * The code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         *
         * @return
         * The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         *
         * @return
         * The body
         */
        public Body getBody() {
            return body;
        }

        /**
         *
         * @param body
         * The body
         */
        public void setBody(Body body) {
            this.body = body;
        }

}
