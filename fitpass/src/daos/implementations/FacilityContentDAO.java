package daos.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.FacilityContent;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class FacilityContentDAO implements IDAO<FacilityContent>{

	private Map<String, FacilityContent> facilityContents = new HashMap<String, FacilityContent>();
	private IRepository<FacilityContent> facilityContentRepository;
	
	public FacilityContentDAO(IRepository<FacilityContent> facilityContentRepository) {
		super();
		this.facilityContentRepository = facilityContentRepository;
		facilityContents = facilityContentRepository.load();
	}

	@Override
	public Collection<FacilityContent> getAll() {
		Collection<FacilityContent> retVal = new ArrayList<FacilityContent>(facilityContents.values());
		retVal.removeIf(x -> (x.getIsDeleted()));
		return retVal;
	}

	@Override
	public FacilityContent get(String id) {
		if(facilityContents.containsKey(id)) {
			if(facilityContents.get(id).getIsDeleted() == false){
				return facilityContents.get(id);
			}
		}
		return null;	
	}

	@Override
	public FacilityContent create(FacilityContent facilityContent) {
		long maxId = 0;
		for (String id : facilityContents.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		facilityContent.setId(maxId);
		facilityContents.put(String.valueOf(facilityContent.getId()), facilityContent);
		facilityContentRepository.save(facilityContents);
		return facilityContent;
	}

	@Override
	public boolean update(FacilityContent facilityContent) {
		if(facilityContents.put(String.valueOf(facilityContent.getId()), facilityContent) != null) {
			facilityContentRepository.save(facilityContents);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(facilityContents.containsKey(id)) {
			facilityContents.get(id).setIsDeleted(true);
			facilityContentRepository.save(facilityContents);
			return true;
		}
		return false;
	}
}
