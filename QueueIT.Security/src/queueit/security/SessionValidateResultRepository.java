package queueit.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionValidateResultRepository extends ValidateResultRepositoryBase {

    @Override
    public IValidateResult getValidationResult(IQueue queue) {
        HttpServletRequest request = RequestContext.getCurrentInstance().getRequest();
        HttpSession session = request.getSession(true);

        return getValidationResult(queue, session);
    }

    public IValidateResult getValidationResult(IQueue queue, HttpSession session) {
        String key = generateKey(queue.getCustomerId(), queue.getEventId());
        SessionStateModel model = (SessionStateModel)session.getAttribute(key);
        
        if (model == null)
            return null;
        
        return new AcceptedConfirmedResult(
            queue, 
            new Md5KnownUser(
                model.QueueId, 
                model.PlaceInQueue, 
                model.TimeStamp, 
                queue.getCustomerId(), 
                queue.getEventId(), 
                model.RedirectType, 
                model.OriginalUrl), 
            true);
    }

    @Override
    public void setValidationResult(IQueue queue, IValidateResult validationResult) {
        HttpServletRequest request = RequestContext.getCurrentInstance().getRequest();
        HttpSession session = request.getSession(true);

        setValidationResult(queue, validationResult, session);
    }

    public void setValidationResult(IQueue queue, IValidateResult validationResult, HttpSession session) {

        if (validationResult instanceof AcceptedConfirmedResult)
        {
            AcceptedConfirmedResult confirmedResult = (AcceptedConfirmedResult)validationResult;

            String key = generateKey(queue.getCustomerId(), queue.getEventId());

            SessionStateModel model = new SessionStateModel();
            model.QueueId = confirmedResult.getKnownUser().getQueueId();
            model.OriginalUrl = confirmedResult.getKnownUser().getOriginalUrl();
            model.TimeStamp = confirmedResult.getKnownUser().getTimeStamp();
            model.RedirectType = confirmedResult.getKnownUser().getRedirectType();
            model.PlaceInQueue = confirmedResult.getKnownUser().getPlaceInQueue();

            session.setAttribute(key, model);
        }
    }

}
