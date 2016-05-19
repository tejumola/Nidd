package pinnacle.org.nidd.model;

/**
 * Created by DOTECH on 04/05/2016.
 */
public enum OperatorRole {
    ADMIN("Admin"),SIMPLE("Simple");

    private String role;

    OperatorRole(String name){
        setRole(name);
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role=role;
    }

    @Override
    public String toString() {
        return role;
    }
}
