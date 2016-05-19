package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.PlayerFacade;
import entity.Player;

@Named("playerController")
@SessionScoped
public class PlayerController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Player player;
	private DataModel<Player> players = null;
	private boolean editing;

	@EJB
	private PlayerFacade facade;

	public Player getPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public DataModel<Player> getPlayers() {
		if (players == null)
			players = new ListDataModel<Player>(facade.findAll());
		return players;
	}

	public void setPlayers(DataModel<Player> players) {
		this.players = players;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public String createNew() {
		editing = false;
		players = null;
		player = null;
		return FacesUtil.pageWithRedirect("editplayer.xhtml");
	}

	public String editPlayer() {
		editing = true;
		player = getPlayers().getRowData();
		return FacesUtil.pageWithRedirect("editplayer.xhtml");
	}

	public String removePlayer() {
		facade.remove(getPlayers().getRowData());

		FacesUtil.addInfoMessage("Player successfully removed");

		players = new ListDataModel<Player>(facade.findAll());
		return FacesUtil.pageWithRedirect("mainpage.xhtml");
	}

	public String save() {
		if (editing) {
			facade.edit(player);

			FacesUtil.addInfoMessage("Player successfully saved");

			players = null;
			player = null;

			return FacesUtil.pageWithRedirect("mainpage.xhtml");
		} else {
			try {
				facade.create(player);

				FacesUtil.addInfoMessage("Player successfully saved");

				players = null;
				player = null;

				return FacesUtil.pageWithRedirect("mainpage.xhtml");
			} catch (Exception e) {
				FacesUtil.addInfoMessage(player.getName() + " already saved.");

				players = null;
				player = null;

				return FacesUtil.pageWithRedirect("editplayer.xhtml");
			}
		}
	}

	public void search(String name) {
		player = facade.find(name);
	}

	public boolean isSearchFoundPlayer() {
		if (player.getName() == null) {
			return false;
		} else {
			return true;
		}
	}
}
