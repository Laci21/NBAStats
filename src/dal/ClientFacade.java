package dal;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Client;

@Stateless
@LocalBean
@DeclareRoles({ Client.GUEST_ROLE, Client.USER_ROLE, Client.ADMIN_ROLE })
public class ClientFacade extends AbstractFacade<Client> {

	@PersistenceContext
	private EntityManager em;

	public ClientFacade() {
		super(Client.class);
	}

	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	@RolesAllowed({ Client.GUEST_ROLE, Client.USER_ROLE, Client.ADMIN_ROLE })
	public void create(Client entity) {
		super.create(entity);
	}

	@Override
	@RolesAllowed({ Client.USER_ROLE, Client.ADMIN_ROLE })
	public void edit(Client entity) {
		super.edit(entity);
	}

}