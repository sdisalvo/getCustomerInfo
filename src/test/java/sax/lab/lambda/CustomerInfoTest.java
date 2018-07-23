package sax.lab.lambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;

import sax.lab.aws.lambda.Customer;
import sax.lab.aws.lambda.GetCustomerInfoHandler;

/**
 * Created by SalvatoreAngelo.DiSa on 27/06/2018.
 */
public class CustomerInfoTest {

    @Test
    public void testSearchByName() {
        /*
        GetCustomerInfoHandler handler = new GetCustomerInfoHandler();

        try {
            Customer filter = new Customer();
            filter.setNome( "Marco" );
            Customer[] customers = handler.readFromDynamoDB( filter );

            for( Customer c: customers ) {
                System.out.println( c.toString() );
            }

            ObjectMapper mapper = new ObjectMapper();
            String json =  mapper.writeValueAsString( customers );

            System.out.println( "Json : " + json );

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        */
    }

    public static void main( String[] args) {
        new CustomerInfoTest().testSearchByName();
    }
}
