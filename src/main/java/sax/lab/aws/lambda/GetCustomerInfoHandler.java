package sax.lab.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;

import java.io.*;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;


/**
 * Created by SalvatoreAngelo.DiSa on 15/06/2018.
 */
public class GetCustomerInfoHandler implements RequestHandler<Request, Response> {

    public Response handleRequest( Request gatewayRequest, Context context)  {
        Response response = new Response();
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-2")
                .build();

        S3Object o = s3.getObject( "ci-storedemo", "sample.json" );
        S3ObjectInputStream s3is = o.getObjectContent();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                baos.write(read_buf, 0, read_len);
            }
            s3is.close();
        } catch ( IOException x) {
           context.getLogger().log( "Reading S3 object exception: " +  x);
        }

        response.setBody( new String( baos.toByteArray() ));
        response.setBase64Encoded( false );
        response.setStatusCode( 200 );

        return response;
    }
}
