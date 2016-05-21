package dal.impl;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dal.AbstractFacade;
import domain.Client;
import domain.Player;

@Stateless
@LocalBean
@DeclareRoles({ Client.USER_ROLE, Client.ADMIN_ROLE })
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
	@PermitAll
	public Player find(Object id) {
		return super.find(id);
	}

	@Override
	@PermitAll
	public List<Player> findAll() {
		return super.findAll();
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void edit(Player entity) {
		super.edit(entity);
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void create(Player entity) throws Exception {
		super.create(entity);
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void remove(Player entity) {
		super.remove(entity);
	}
}