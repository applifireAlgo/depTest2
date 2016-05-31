package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organization.locationmanagement.Village;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for Village Master table Entity", complexity = Complexity.LOW)
public class VillageRepositoryImpl extends SearchInterfaceImpl implements VillageRepository<Village> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<Village> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.organization.locationmanagement.Village> query = emanager.createQuery("select u from Village u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public Village save(Village entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("nullLM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<Village> save(List<Village> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.organization.locationmanagement.Village obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("nullLM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.organization.locationmanagement.Village s = emanager.find(com.app.shared.organization.locationmanagement.Village.class, id);
        emanager.remove(s);
        Log.out.println("nullLM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void update(Village entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("nullLM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<Village> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.organization.locationmanagement.Village obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("nullLM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Village> findByCountryId(String countryId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organization.locationmanagement.Village> listOfVillage = query.getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    @Transactional
    public List<Village> findByStateId(String stateId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organization.locationmanagement.Village> listOfVillage = query.getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    @Transactional
    public List<Village> findByRegionId(String regionId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findByRegionId");
        query.setParameter("regionId", regionId);
        java.util.List<com.app.shared.organization.locationmanagement.Village> listOfVillage = query.getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByRegionId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    @Transactional
    public List<Village> findByDistrictId(String districtId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findByDistrictId");
        query.setParameter("districtId", districtId);
        java.util.List<com.app.shared.organization.locationmanagement.Village> listOfVillage = query.getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByDistrictId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    @Transactional
    public List<Village> findByTalukaaId(String talukaaId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findByTalukaaId");
        query.setParameter("talukaaId", talukaaId);
        java.util.List<com.app.shared.organization.locationmanagement.Village> listOfVillage = query.getResultList();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByTalukaaId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    @Transactional
    public Village findById(String villageId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("Village.findById");
        query.setParameter("villageId", villageId);
        com.app.shared.organization.locationmanagement.Village listOfVillage = (com.app.shared.organization.locationmanagement.Village) query.getSingleResult();
        Log.out.println("nullLM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findById", "Total Records Fetched = " + listOfVillage);
        return listOfVillage;
    }
}
