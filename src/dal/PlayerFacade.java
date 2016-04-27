package dal;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Client;
import entity.Player;

@Stateless
@LocalBean
@DeclareRoles({ Client.GUEST_ROLE, Client.USER_ROLE, Client.ADMIN_ROLE })
public class PlayerFacade extends AbstractFacade<Player> {

	@PersistenceContext
	private EntityManager em;

	public PlayerFacade() {
		super(Player.class);
	}

	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	@RolesAllowed({ Client.GUEST_ROLE, Client.USER_ROLE, Client.ADMIN_ROLE })
	public Player find(Object id) {
		return super.find(id);
	}
}