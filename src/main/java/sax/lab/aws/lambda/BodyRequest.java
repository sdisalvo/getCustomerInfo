package sax.lab.aws.lambda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by SalvatoreAngelo.DiSa on 27/06/2018.
 */
public class BodyRequest {

    private Customer customer;

    public BodyRequest(){}

    public static BodyRequest createFromJson(String json ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, BodyRequest.class);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
