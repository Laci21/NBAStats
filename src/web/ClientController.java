package web;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import dal.impl.ClientFacade;
import domain.Client;

@Named("clientController")
@SessionScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Client current = null;

	@EJB
	private static ClientFacade facade;

	public Client getCurrent() {
		if (current == null)
			current = new Client();
		return current;
	}

	public void setCurrent(Client current) {
		ClientController.current = current;
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

	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		try {
			String userName = current.getName();
			request.login(userName, current.getPassword());
			current = facade.find(userName);

			FacesUtil.addInfoMessage(userName + " login succeeded!");

			externalContext.redirect("/NBAStats/faces/guest/mainpage.xhtml");
		} catch (ServletException e) {
			current = null;
			externalContext.redirect("/NBAStats/faces/guest/loginerror.xhtml");
		}
	}

	public String logout() {
		current = null;
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return FacesUtil.pageWithRedirect("/faces/guest/index.xhtml");
	}

	public String register() {
		current.addRole(Client.USER_ROLE);
		try {
			facade.create(current);

			FacesUtil.addInfoMessage(current.getName()
					+ " successfully registered. You can login now.");

			current = null;

			return FacesUtil.pageWithRedirect("/faces/guest/index.xhtml");
		} catch (Exception e) {
			FacesUtil
					.addInfoMessage(current.getName() + " already registered.");

			current = null;

			return FacesUtil.pageWithRedirect("/faces/guest/register.xhtml");
		}
	}

	public void deleteFavouritePlayer() {
		current.setFavouritePlayerName(null);
		facade.edit(current);

		FacesUtil.addInfoMessage("Favourite player successfully deleted.");
	}

	public void deleteFavouriteTeam() {
		current.setFavouriteTeamName(null);
		facade.edit(current);

		FacesUtil.addInfoMessage("Favourite team successfully deleted.");
	}

	public void saveAsFavouritePlayer(String favouritePlayerName) {
		current.setFavouritePlayerName(favouritePlayerName);
		facade.edit(current);

		FacesUtil.addInfoMessage("Player successfully saved.");
	}

	public void saveAsFavouriteTeam(String favouriteTeamName) {
		current.setFavouriteTeamName(favouriteTeamName);
		facade.edit(current);

		FacesUtil.addInfoMessage("Team successfully saved.");
	}

	public boolean hasFavouritePlayer() {
		if (current != null) {
			if (current.getFavouritePlayerName() != null) {
				return true;
			}
		}

		return false;
	}

	public boolean hasFavouriteTeam() {
		if (current != null) {
			if (current.getFavouriteTeamName() != null) {
				return true;
			}
		}

		return false;
	}

	public static void refreshCurrent() {
		String userName = current.getName();
		current = facade.find(userName);
	}
}
