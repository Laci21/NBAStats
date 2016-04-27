package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dal.PlayerFacade;
import entity.Player;

@Named("playerController")
@SessionScoped
public class PlayerController implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Player player = null;

	@EJB
	private PlayerFacade facade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setplayer(Player player) {
		this.player = player;
	}

	public void search() {
		player = facade.find(name);
	}
}
