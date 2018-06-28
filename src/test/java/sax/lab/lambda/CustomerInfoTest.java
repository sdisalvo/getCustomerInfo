package sax.lab.lambda;

import org.junit.*;

import sax.lab.aws.lambda.BodyRequest;
import sax.lab.aws.lambda.Customer;
import sax.lab.aws.lambda.GetCustomerInfoHandler;

/**
 * Created by SalvatoreAngelo.DiSa on 27/06/2018.
 */
public class CustomerInfoTest {

    @Test
    public void testSearchByName() {
        GetCustomerInfoHandler handler = new GetCustomerInfoHandler();

        Customer filter = new Customer();
        filter.setNome( "Marco" );
        Customer[] customers = handler.readFromDynamoDB( filter );

        for( Customer c: customers ) {
            System.out.println( c.toString() );
        }
    }

    @Test
    public void testParseJSon() {
        BodyRequest br = new BodyRequest( "{\n" +
                "    \"customer\" :  {\n" +
                "        \"customerId\" : \"1\"\n" +
                "    }\n" +
                "}");

        System.out.println( br.getCustomer().toString() );

    }

    public static void main( String[] args) {
        new CustomerInfoTest().testParseJSon();
    }
}
