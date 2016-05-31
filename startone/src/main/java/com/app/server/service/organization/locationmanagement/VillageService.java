package com.app.server.service.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import org.springframework.http.HttpEntity;
import com.app.shared.organization.locationmanagement.Village;
import java.util.List;
import com.athena.server.pluggable.utils.bean.FindByBean;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Village Master table Entity", complexity = Complexity.LOW)
public abstract class VillageService {

    public HttpEntity<ResponseBean> findAll() throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(Village entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> save(List<Village> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> delete(String id) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(Village entity) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> update(List<Village> entity, boolean isArray) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByCountryId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByStateId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByRegionId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByDistrictId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findByTalukaaId(FindByBean findByBean) throws Exception {
        return null;
    }

    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws Exception {
        return null;
    }
}
