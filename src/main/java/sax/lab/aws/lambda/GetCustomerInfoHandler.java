package sax.lab.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by SalvatoreAngelo.DiSa on 15/06/2018.
 */
public class GetCustomerInfoHandler
    implements RequestStreamHandler {
        public void handleRequest(InputStream inputStream,
                                  OutputStream outputStream,
                                  Context context) throws IOException {
            String input = IOUtils.toString(inputStream, "UTF-8");

            final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    .withRegion("us-east-2")
                    .build();

            List<Bucket> buckets = s3.listBuckets();
            for (Bucket b : buckets) {
                outputStream.write( ("* " + b.getName()).getBytes() );

            }
            outputStream.write( " ciao ".getBytes() );

            S3Object o = s3.getObject( "sax-image-reko", "GetCustomerInfoHandler.java" );
            S3ObjectInputStream s3is = o.getObjectContent();

            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                outputStream.write(read_buf, 0, read_len);
            }
            s3is.close();
        }
}
