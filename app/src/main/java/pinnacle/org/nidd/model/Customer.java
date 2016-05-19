package pinnacle.org.nidd.model;



/**
 * Created by DOTECH on 04/05/2016.
 */
public class Customer {

    /**
     * the customer has atrributes of an operator. although is a (extending operator will have been good), but i think
     * composition will be more appropiate, since only selected features of the operator will be exposed
     */
    private Operator operator;

    /**
     * Default constructor
     */
    public Customer(Operator operator) {
        setOperator(operator);
    }

    /**
     * get the operator attribute of a customer
     *
     * @return
     */
    public Operator asOperator() {
        return this.operator;
    }

    /**
     * set the operator attribute of a customer
     *
     * @param operator
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}