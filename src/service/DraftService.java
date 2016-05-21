package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import web.FacesUtil;
import dal.DraftFacade;
import entity.DraftPick;

@Stateless
@LocalBean
public class DraftService {

	@EJB
	private DraftFacade draftFacade;

	@EJB
	private DraftHTTPImport draftHTTPImport;

	public void importDraftClass(int importYear) {
		List<DraftPick> draftPickList = draftHTTPImport
				.importDraftClass(importYear);

		if (draftPickList.isEmpty()) {
			FacesUtil.addInfoMessage("No such draft class.");
		} else {
			try {
				for (DraftPick draftPick : draftPickList) {
					draftFacade.create(draftPick);
				}

				FacesUtil.addInfoMessage("Draft class imported successfully!");
			} catch (Exception e) {
				FacesUtil.addInfoMessage("Draft class already imported.");
				e.printStackTrace();
			}
		}
	}

	public List<Integer> findDifferentYears() {
		return draftFacade.findDifferentYears();
	}

	public List<DraftPick> findDraftClass(int year) {
		return draftFacade.findDraftClass(year);
	}

}
