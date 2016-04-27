package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.ClientFacade;
import entity.Client;

@Named("clientController")
@SessionScoped
public class ClientController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Client current = new Client();
	private DataModel<Client> items = null;

	@EJB
	private ClientFacade facade;

	public Client getCurrent() {
		if (current == null)
			current = new Client();
		return current;
	}

	public void setCurrent(Client current) {
		this.current = current;
	}

	public DataModel<Client> getItems() {
		if (items == null)
			items = new ListDataModel<Client>(facade.findAll());
		return items;
	}

	public void setItems(DataModel<Client> items) {
		this.items = items;
	}

	public String register() {
		current.addRole(Client.USER_ROLE);
		facade.create(current);

		FacesUtil
				.addInfoMessage("User successfully registered. You can login now.");

		items = null;
		current = null;

		return FacesUtil.pageWithRedirect("index.xhtml");
	}

	public void saveAsFavourite(String favouritePlayerName) {
		getCurrent().setFavouritePlayerName(favouritePlayerName);
		facade.edit(current);

		FacesUtil.addInfoMessage("Team successfully saved.");
	}
}
