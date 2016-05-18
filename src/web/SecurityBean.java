package web;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import entity.Client;

@Named("securityBean")
@SessionScoped
public class SecurityBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username = "";
	private String password = "";

	// public String getUserName() {
	// ExternalContext ectx = FacesContext.getCurrentInstance()
	// .getExternalContext();
	// return ((ectx == null) || (ectx.getUserPrincipal() == null)) ? null
	// : ectx.getUserPrincipal().getName();
	// }

	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {
			request.login(username, password);
			externalContext.redirect("mainpage.xhtml");
		} catch (ServletException e) {
			username = null;
			password = null;
			externalContext.redirect("loginerror.xhtml");
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return FacesUtil.pageWithRedirect("index.xhtml");
	}

	public boolean isAdmin() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ectx.isUserInRole(Client.ADMIN_ROLE);
	}

	public boolean isUser() {
		ExternalContext ectx = FacesContext.getCurrentInstance()
				.getExternalContext();
		return ectx.isUserInRole(Client.USER_ROLE);
	}

	public String getUsername() {
		if (username == null) {
			username = "";
		}

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if (password == null) {
			password = "";
		}

		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
