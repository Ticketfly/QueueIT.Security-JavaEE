package queueit.security;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public class SessionStateModel implements java.io.Serializable {
    private static final long serialVersionUID = 3538940694656626404L;
    public UUID QueueId;
    public URI OriginalUrl;
    public Date TimeStamp;
    public RedirectType RedirectType;
    public Integer PlaceInQueue;
}
