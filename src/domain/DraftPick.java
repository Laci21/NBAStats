package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DraftPick implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int draftYear;

	@Id
	private int round;

	@Id
	private int pick;

	private String teamName;
	private String playerName;

	public DraftPick() {
	}

	public DraftPick(int draftYear, int round, int pick, String teamName,
			String playerName) {
		this.draftYear = draftYear;
		this.round = round;
		this.pick = pick;
		this.teamName = teamName;
		this.playerName = playerName;
	}

	public int getDraftYear() {
		return draftYear;
	}

	public void setDraftYear(int draftYear) {
		this.draftYear = draftYear;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPick() {
		return pick;
	}

	public void setPick(int pick) {
		this.pick = pick;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pick;
		result = prime * result
				+ ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + round;
		result = prime * result
				+ ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + draftYear;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DraftPick other = (DraftPick) obj;
		if (pick != other.pick)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (round != other.round)
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		if (draftYear != other.draftYear)
			return false;
		return true;
	}
}
