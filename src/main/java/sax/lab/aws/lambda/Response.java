package sax.lab.aws.lambda;

/**
 * Created by SalvatoreAngelo.DiSa on 26/06/2018.
 */
public class Response {

    private Customer[] customers;

    public Customer[] getCustomers() {
        return customers;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }
}
