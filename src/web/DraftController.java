package web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import service.DraftService;
import entity.DraftPick;

@Named("draftController")
@SessionScoped
public class DraftController implements Serializable {
	private static final long serialVersionUID = 1L;

	private int year;
	private int importYear;
	private List<Integer> yearList;
	private List<DraftPick> pickList;

	@EJB
	private DraftService draftService;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getImportYear() {
		return importYear;
	}

	public void setImportYear(int importYear) {
		this.importYear = importYear;
	}

	public List<Integer> getYearList() {
		yearList = draftService.findDifferentYears();

		return yearList;
	}

	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}

	public List<DraftPick> getPickList() {
		return pickList;
	}

	public void setPickList(List<DraftPick> pickList) {
		this.pickList = pickList;
	}

	public void refreshPickList() {
		pickList = draftService.findDraftClass(year);
	}

	public void importDraftClass() {
		if (yearList.contains(importYear)) {
			FacesUtil.addInfoMessage("Draft class already imported.");
		} else {
			draftService.importDraftClass(importYear);
		}
	}
}
