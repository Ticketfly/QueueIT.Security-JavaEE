package queueit.security;

public class ExpiredValidationException extends SessionValidationException {
    private static final long serialVersionUID = 2203419613358387003L;

    private IKnownUser knownUser;
    
    public IKnownUser getKnownUser() {
        return this.knownUser;
    }
    
    ExpiredValidationException(IQueue queue, IKnownUser knownUser)
    {
        super("Known User token is expired", queue);
        this.knownUser = knownUser;
    }
}
