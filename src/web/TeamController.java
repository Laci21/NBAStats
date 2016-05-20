package web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import dal.ClientFacade;
import dal.TeamFacade;
import entity.Team;

@Named("teamController")
@SessionScoped
public class TeamController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Team team;
	private DataModel<Team> teams = null;
	private boolean editing;

	@EJB
	private TeamFacade teamFacade;

	@EJB
	private ClientFacade clientFacade;

	public Team getTeam() {
		if (team == null) {
			team = new Team();
		}
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public DataModel<Team> getTeams() {
		if (teams == null)
			teams = new ListDataModel<Team>(teamFacade.findAll());
		return teams;
	}

	public void setTeams(DataModel<Team> teams) {
		this.teams = teams;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public String createNew() {
		editing = false;
		teams = null;
		team = null;
		return FacesUtil.pageWithRedirect("editteam.xhtml");
	}

	public String editTeam() {
		editing = true;
		return FacesUtil.pageWithRedirect("editteam.xhtml");
	}

	public String removeTeam() {
		teamFacade.remove(team);
		clientFacade.removeFavouriteTeam(team.getName());
		team = null;
		SecurityBean.refreshCurrent();

		FacesUtil.addInfoMessage("Team successfully removed");

		teams = new ListDataModel<Team>(teamFacade.findAll());
		return FacesUtil.pageWithRedirect("mainpage.xhtml");
	}

	public String save() {
		if (editing) {
			teamFacade.edit(team);

			FacesUtil.addInfoMessage("Team successfully saved");

			teams = null;
			team = null;

			return FacesUtil.pageWithRedirect("mainpage.xhtml");
		} else {
			try {
				teamFacade.create(team);

				FacesUtil.addInfoMessage("Team successfully saved");

				teams = null;
				team = null;

				return FacesUtil.pageWithRedirect("mainpage.xhtml");
			} catch (Exception e) {
				FacesUtil.addInfoMessage(team.getName() + " already saved.");

				teams = null;
				team = null;

				return FacesUtil.pageWithRedirect("editteam.xhtml");
			}
		}
	}

	public void search(String name) {
		team = teamFacade.find(name);
	}

	public boolean isSearchFoundTeam() {
		if (team.getName() == null) {
			return false;
		} else {
			return true;
		}
	}
}
