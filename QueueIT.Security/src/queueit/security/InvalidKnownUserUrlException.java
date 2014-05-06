package queueit.security;

/**
 *
 * @author Queue-it
 * last update: 2012-10-25
 */
public class InvalidKnownUserUrlException extends KnownUserException {

    private static final long serialVersionUID = 4644556526776997967L;

    public InvalidKnownUserUrlException() {
        super("The hash of the request is invalid", null);
    }
}
