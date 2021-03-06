package com.lc.clz.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * 自定义Session管理（实现功能，将Session放到reques中，如果request没有则从redis中取）
 */
public class CustomSesionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException{
        Serializable sessionId=getSessionId(sessionKey);
        ServletRequest request=null;
        if(sessionKey instanceof WebSessionKey){
            request=((WebSessionKey)sessionKey).getServletRequest();
        }
        if(request!=null && sessionId!=null){
            Session session=(Session)request.getAttribute(sessionId.toString());
            if(session!=null){
                return session;
            }
        }
        Session session=super.retrieveSession(sessionKey);
        if(request!=null && sessionId!=null){
            request.setAttribute(sessionId.toString(),session);
        }
        return session;
    }
}
