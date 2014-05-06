package queueit.security;

/**
 *
 * @author Queue-it
 * last update: 2012-10-25
 */
public class InvalidKnownUserHashException extends KnownUserException {

    private static final long serialVersionUID = -3597734554522977548L;

    public InvalidKnownUserHashException() {
        super("The hash of the request is invalid", null);
    }
}
