/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class SessionExpirationPhaseListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
       
    }

 

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest httpRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        
        
        if (httpRequest.getRequestedSessionId() != null && !httpRequest.isRequestedSessionIdValid()) {
           String facesRequestHeader = httpRequest.getHeader("Faces-Request");
            if (facesRequestHeader != null && facesRequestHeader.equals("partial/ajax")) {
                ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
                handler.performNavigation("index");

            }

        }

    }
}

 
