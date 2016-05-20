package web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import dal.DraftFacade;
import entity.DraftPick;

@Named("draftController")
@SessionScoped
public class DraftController implements Serializable {
	private static final long serialVersionUID = 1L;

	private int year;
	private List<Integer> yearList;
	private List<DraftPick> pickList;

	@EJB
	private DraftFacade draftFacade;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Integer> getYearList() {
		yearList = draftFacade.findDifferentYears();

		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public List<DraftPick> getPickList() {
		pickList = draftFacade.findDraftClass(year);

		return pickList;
	}

	public void setPickList(List<DraftPick> pickList) {
		this.pickList = pickList;
	}

}
