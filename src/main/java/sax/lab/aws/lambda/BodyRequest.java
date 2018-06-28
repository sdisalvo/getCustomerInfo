package sax.lab.aws.lambda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by SalvatoreAngelo.DiSa on 27/06/2018.
 */
public class BodyRequest {

    private Customer customer;

    public BodyRequest(){}

    public BodyRequest( String json ){
        try {
            ObjectMapper mapper = new ObjectMapper();
            BodyRequest br = mapper.readValue(json, BodyRequest.class);
            this.customer = br.customer;
        } catch ( IOException x) {
            x.printStackTrace();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}
