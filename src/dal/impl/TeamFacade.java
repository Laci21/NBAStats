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
import domain.Team;

@Stateless
@LocalBean
@DeclareRoles({ Client.USER_ROLE, Client.ADMIN_ROLE })
public class TeamFacade extends AbstractFacade<Team> {

	@PersistenceContext
	private EntityManager em;

	public TeamFacade() {
		super(Team.class);
	}

	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	@PermitAll
	public Team find(Object id) {
		return super.find(id);
	}

	@Override
	@PermitAll
	public List<Team> findAll() {
		return super.findAll();
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void edit(Team entity) {
		super.edit(entity);
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void create(Team entity) throws Exception {
		super.create(entity);
	}

	@Override
	@RolesAllowed({ Client.ADMIN_ROLE })
	public void remove(Team entity) {
		super.remove(entity);
	}
}