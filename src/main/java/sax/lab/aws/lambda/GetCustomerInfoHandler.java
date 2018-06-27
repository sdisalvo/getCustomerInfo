package sax.lab.aws.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;


/**
 * Created by SalvatoreAngelo.DiSa on 15/06/2018.
 */
public class GetCustomerInfoHandler implements RequestHandler<Request, Response> {

    public Response handleRequest( Request gatewayRequest, Context context)  {
        Customer customer = gatewayRequest.getCustomer();

        context.getLogger().log( "Customer input filter: " + customer.toString());

        Response response = new Response();
        Body respBody = new Body();
        respBody.setCustomer(readFromDynamoDB( customer ));
        response.setBody( respBody );
        response.setBase64Encoded( false );
        response.setStatusCode( 200 );

        return response;
    }

    public Customer[] readFromDynamoDB( Customer customer ) {
        ScanSpec spec = new ScanSpec();
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1).build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Customer");

        if( customer.getCustomerId() != null )
            spec.withFilterExpression("customerId = :id")
                    .withValueMap( new ValueMap().withString(":id", customer.getCustomerId()));

        if( customer.getCognome() != null )
            spec.withFilterExpression( "cognome = :cognome" )
                    .withValueMap( new ValueMap().withString( ":cognome", customer.getCognome()));

        if( customer.getNome() != null )
            spec.withFilterExpression( "nome = :nome" )
                    .withValueMap( new ValueMap().withString( ":nome", customer.getNome()));

        if( customer.getProjectExpession() != null )
            spec.withProjectionExpression( customer.getProjectExpession() );

        LinkedList<Customer> customers = new LinkedList<Customer>();
        ItemCollection<ScanOutcome> items = table.scan( spec );
        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            Customer c = new Customer();
            c.setCustomerId( item.getString( "customerId"));
            c.setCognome( item.getString( "cognome"));
            c.setNome( item.getString( "nome"));
            c.setVia( item.getString( "via"));
            c.setNumeroCivico( item.getString( "numeroCivico"));
            customers.add( c );
        }

        return customers.toArray( new Customer[customers.size()]);
    }

    protected String readFromS3( Context context ) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-2")
                .build();

        S3Object o = s3.getObject("ci-storedemo", "sample.json");
        S3ObjectInputStream s3is = o.getObjectContent();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                baos.write(read_buf, 0, read_len);
            }
            s3is.close();
        } catch (IOException x) {
            context.getLogger().log("Reading S3 object exception: " + x);
        }

        return new String( baos.toByteArray() );
    }
}
