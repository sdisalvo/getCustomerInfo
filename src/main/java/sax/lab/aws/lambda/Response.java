package sax.lab.aws.lambda;

/**
 * Created by SalvatoreAngelo.DiSa on 26/06/2018.
 */
public class Response {
    private String message;

    Response( String message ) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
