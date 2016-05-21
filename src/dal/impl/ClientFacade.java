package dal.impl;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dal.AbstractFacade;
import domain.Client;

@Stateless
@LocalBean
@DeclareRoles({ Client.USER_ROLE, Client.ADMIN_ROLE })
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
	@PermitAll
	public void create(Client entity) throws Exception {
		super.create(entity);
	}

	@Override
	@RolesAllowed({ Client.USER_ROLE, Client.ADMIN_ROLE })
	public void edit(Client entity) {
		super.edit(entity);
	}

	@Override
	@PermitAll
	public Client find(Object id) {
		return super.find(id);
	}

	@RolesAllowed({ Client.ADMIN_ROLE })
	public void removeFavouritePlayer(String name) {
		TypedQuery<String> guery = em.createQuery(
				"SELECT name FROM Client WHERE favouriteplayername = :name",
				String.class);
		guery.setParameter("name", name);
		List<String> clientList = guery.getResultList();

		for (String clientString : clientList) {
			Client client = em.find(Client.class, clientString);
			client.setFavouritePlayerName(null);
			em.merge(client);
		}
	}

	@RolesAllowed({ Client.ADMIN_ROLE })
	public void removeFavouriteTeam(String name) {
		TypedQuery<String> guery = em.createQuery(
				"SELECT name FROM Client WHERE favouriteteamname = :name",
				String.class);
		guery.setParameter("name", name);
		List<String> clientList = guery.getResultList();

		for (String clientString : clientList) {
			Client client = em.find(Client.class, clientString);
			client.setFavouriteTeamName(null);
			em.merge(client);
		}
	}
}