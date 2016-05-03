package pinnacle.org.nidd.exceptions;

/**
 * Created by DOTECH on 28/04/2016.
 */
public class OperatorAlreadyExistException extends AuthenticationException{
    public OperatorAlreadyExistException(String msg,Throwable t){
        super(msg,t);
    }
    public OperatorAlreadyExistException(String msg){
        super(msg);
    }
}
