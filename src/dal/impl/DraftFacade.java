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
import domain.DraftPick;

@Stateless
@LocalBean
@DeclareRoles({ Client.USER_ROLE, Client.ADMIN_ROLE })
public class DraftFacade extends AbstractFacade<DraftPick> {

	@PersistenceContext
	private EntityManager em;

	public DraftFacade() {
		super(DraftPick.class);
	}

	@Override
	protected EntityManager em() {
		return em;
	}

	@Override
	@RolesAllowed(Client.ADMIN_ROLE)
	public void create(DraftPick entity) throws Exception {
		super.create(entity);
	}

	@PermitAll
	public List<Integer> findDifferentYears() {
		TypedQuery<Integer> query = em.createQuery(
				"SELECT DISTINCT draftYear FROM DraftPick", Integer.class);

		return query.getResultList();
	}

	@PermitAll
	public List<DraftPick> findDraftClass(int year) {
		TypedQuery<DraftPick> query = em
				.createQuery(
						"SELECT NEW domain.DraftPick(d.draftYear, d.round, d.pick, d.teamName, d.playerName) FROM "
								+ "DraftPick as d WHERE draftyear = :year",
						DraftPick.class);
		query.setParameter("year", year);

		return query.getResultList();
	}
}