package pinnacle.org.nidd.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pinnacle.org.nidd.utils.Builder;
import pinnacle.org.nidd.utils.BuilderBuild;

/**
 * Created by DOTECH on 04/05/2016.
 */
public final class Operator {
    private String id;
    private final String username;
    private final String password;
    private final String email;
    private OperatorRole role;
    private final Set<String> visitor;
    private byte[] userAvatar=null;//innitialize the user profile Image


    /**
     * Creates an operator from a operatorBuilder
     *
     * @param operatorBuilder
     */
    private Operator (OperatorBuilder operatorBuilder){
        this.username=operatorBuilder.username;
        this.password=operatorBuilder.password;
        this.email=operatorBuilder.email;
        this.setOperatorRole(operatorBuilder.role);
        this.visitor=operatorBuilder.visitor;
    }

    /**
     * Copy constructor for Operator
     * Create a copy of user. This is highly efficient than clone
     *
     * @param operator
     */
    public Operator(Operator operator) {
        this.username = operator.username;
        this.password = operator.password;
        this.email = operator.email;
        this.id = operator.id;
        this.visitor = operator.visitor;
        this.setOperatorRole(operator.getOperatorRole());
    }


    /**
     * Copy Constructor for User
     * Create a copy of user.
     * This is highly efficient than clone
     *
     * @param operator    the user object to clone
     * @param newoperator, the operator to update the {@code Operator} with. PECS
     */

    public Operator(Operator operator, Collection<String> newoperator) {
        this.username = operator.username;
        this.password = operator.password;
        this.visitor = operator.visitor;
        this.id =  operator.getId();
        this.email = operator.getUserEmail();
        this.setOperatorRole(operator.getOperatorRole());

    }

    /**
     * This method returns the given id of the user entity
     *
     * @return id of the user entity
     */
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id=(id);
    }

    /**
     * username
     */
    public String getUserEmail() {
        return email;
    }

    /**
     *
     */
    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<String> getCustomers() {
        if(visitor==null)
            return new HashSet<>();
        return visitor;
    }


    public byte[] getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        this.userAvatar = userAvatar;
    }




    public OperatorRole getOperatorRole() {
        return this.role;
    }

    public void setOperatorRole(OperatorRole operatorRole) {
        this.role = operatorRole;
    }

    /**
     * compute hashcode only on email and password for now
     *
     * @return
     */
    public int hashCode() {
        int result = this.username.hashCode();
        result = 31 * result + this.password.hashCode();
        result = 31 * result + this.email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Operator{" +

                "  userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getUserEmail() + '\'' +
                ", friends=" +  Arrays.toString(getCustomers().toArray()) +
                '}';
    }

    /**
     * <p>
     *     This is a builder Class for the operator.It implements two build interface.
     *
     * * @code{build(Builder<? extends B>, R)} , where R is the return type of User object and B is the builder instance</p>
     * </p>
     */
    public static class OperatorBuilder implements Builder<Operator>,BuilderBuild<Builder<Operator>,Operator>{

        private final String username;
        private final String password;
        private final String email;
        private  OperatorRole role;
        private Set<String> visitor=new HashSet<>();

        public OperatorBuilder(String userName, String password, String email) {
            this.username = (userName);
            this.password = (password);
            this.email = (email);
        }

        public OperatorBuilder role(OperatorRole role){
            this.role=role;
            return this;
        }

        @Override
        public Operator build(Operator type) {
            return null;
        }

        public OperatorBuilder visitors(Set<String> visitors) {
            this.visitor = visitors==null? new HashSet<String>():visitors;
            return this;
        }

        @Override
        public Operator build() {
            return new Operator(this);
        }

        /**
         * build with a builder
         * @param builder
         * @return
         */
        @Override
        public Operator build(Builder<Operator> builder) {
            return new Operator((OperatorBuilder)builder) ;
        }
    }

}
