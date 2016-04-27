package web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static String pageWithRedirect(String page){
		return page + "?faces-redirect=true";
	}

	public static void addInfoMessage(String text) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, text,
				text);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, msg);
		
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}
}
