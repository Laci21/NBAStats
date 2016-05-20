package entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import com.sun.istack.internal.Nullable;

@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String USER_ROLE = "USER";
	public static final String ADMIN_ROLE = "ADMIN";

	@Id
	private String name;

	private String password;

	@Nullable
	private String favouritePlayerName;

	@Nullable
	private String favouriteTeamName;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFavouritePlayerName() {
		return favouritePlayerName;
	}

	public void setFavouritePlayerName(String favouritePlayerName) {
		this.favouritePlayerName = favouritePlayerName;
	}

	public String getFavouriteTeamName() {
		return favouriteTeamName;
	}

	public void setFavouriteTeamName(String favouriteTeamName) {
		this.favouriteTeamName = favouriteTeamName;
	}

	public Set<String> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public boolean isAdmin() {
		return roles.contains(ADMIN_ROLE);
	}

	public void setAdmin(boolean val) {
		if (val)
			roles.add(ADMIN_ROLE);
		else
			roles.remove(ADMIN_ROLE);
	}

	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	public void addRole(String role) {
		roles.add(role);
	}

	public void removeRole(String role) {
		roles.remove(role);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Client other = (Client) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
