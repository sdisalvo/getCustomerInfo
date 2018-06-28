package sax.lab.aws.lambda;

/**
 * Created by SalvatoreAngelo.DiSa on 26/06/2018.
 */
public class Response {

    private String body;
    private boolean isBase64Encoded = false;
    private int statusCode = 200;

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
