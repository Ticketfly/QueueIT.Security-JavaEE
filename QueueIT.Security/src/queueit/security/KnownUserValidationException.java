package queueit.security;

public class KnownUserValidationException extends SessionValidationException {
    private static final long serialVersionUID = -6105477264534577776L;
    KnownUserValidationException(KnownUserException cause, IQueue queue)
    {
        super(cause.getMessage(), cause, queue);
    }
}
