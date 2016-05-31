package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Village Master table Entity", complexity = Complexity.LOW)
public interface VillageRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByCountryId(String countryId) throws Exception;

    public List<T> findByStateId(String stateId) throws Exception;

    public List<T> findByRegionId(String regionId) throws Exception;

    public List<T> findByDistrictId(String districtId) throws Exception;

    public List<T> findByTalukaaId(String talukaaId) throws Exception;

    public T findById(String villageId) throws Exception;
}
