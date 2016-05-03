package pinnacle.org.nidd.exceptions;

/**
 * Created by DOTECH on 28/04/2016.
 */
public class OperatorNotFoundException extends Exception{
    public OperatorNotFoundException(String msg,Throwable t){
        super(msg,t);
    }
    public OperatorNotFoundException(String msg){
        super(msg);
    }

    @Override
    public String toString() {

        return super.toString();
    }
}
