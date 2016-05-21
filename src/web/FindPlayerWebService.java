package web;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dal.impl.PlayerFacade;
import domain.Player;

@WebService
public class FindPlayerWebService {

	@EJB
	private PlayerFacade playerFacade;

	@WebMethod
	public Player search(String name) {
		return playerFacade.find(name);
	}
}
