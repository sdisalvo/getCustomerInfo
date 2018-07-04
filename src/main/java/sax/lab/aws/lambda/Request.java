package sax.lab.aws.lambda;

/**
 * Created by SalvatoreAngelo.DiSa on 26/06/2018.
 */
public class Request {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*private String body;
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "body: " + body;
    }*/
}
