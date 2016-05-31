package com.app.shared.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Village_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Village", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Village.findByCountryId", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "Village.findByStateId", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "Village.findByRegionId", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.regionId=:regionId"), @javax.persistence.NamedQuery(name = "Village.findByDistrictId", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.districtId=:districtId"), @javax.persistence.NamedQuery(name = "Village.findByTalukaaId", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.talukaaId=:talukaaId"), @javax.persistence.NamedQuery(name = "Village.findById", query = "select e from Village e where e.systemInfo.activeStatus=1 and e.villageId =:villageId") })
public class Village implements Serializable, CommonEntityInterface, Comparator<Village> {

    @Column(name = "villageName")
    @JsonProperty("villageName")
    @NotNull
    @Size(min = 0, max = 256)
    private String villageName;

    @Column(name = "villageDescription")
    @JsonProperty("villageDescription")
    @Size(min = 0, max = 256)
    private String villageDescription;

    @Column(name = "villageFlag")
    @JsonProperty("villageFlag")
    @Size(min = 0, max = 64)
    private String villageFlag;

    @Column(name = "villageCode")
    @JsonProperty("villageCode")
    @NotNull
    @Size(min = 0, max = 32)
    private String villageCode;

    @Column(name = "villageLatitude")
    @JsonProperty("villageLatitude")
    @Size(min = 0, max = 11)
    private String villageLatitude;

    @Column(name = "villageLongtitude")
    @JsonProperty("villageLongtitude")
    @Min(0)
    @Max(11)
    private Integer villageLongtitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "villageId")
    @JsonProperty("villageId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String villageId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

    @Column(name = "regionId")
    @JsonProperty("regionId")
    private String regionId;

    @Column(name = "districtId")
    @JsonProperty("districtId")
    private String districtId;

    @Column(name = "talukaaId")
    @JsonProperty("talukaaId")
    private String talukaaId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String _villageName) {
        if (_villageName != null) {
            this.villageName = _villageName;
        }
    }

    public String getVillageDescription() {
        return villageDescription;
    }

    public void setVillageDescription(String _villageDescription) {
        this.villageDescription = _villageDescription;
    }

    public String getVillageFlag() {
        return villageFlag;
    }

    public void setVillageFlag(String _villageFlag) {
        this.villageFlag = _villageFlag;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String _villageCode) {
        if (_villageCode != null) {
            this.villageCode = _villageCode;
        }
    }

    public String getVillageLatitude() {
        return villageLatitude;
    }

    public void setVillageLatitude(String _villageLatitude) {
        this.villageLatitude = _villageLatitude;
    }

    public Integer getVillageLongtitude() {
        return villageLongtitude;
    }

    public void setVillageLongtitude(Integer _villageLongtitude) {
        this.villageLongtitude = _villageLongtitude;
    }

    public String getPrimaryKey() {
        return villageId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return villageId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String _villageId) {
        this.villageId = _villageId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String _countryId) {
        this.countryId = _countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String _stateId) {
        this.stateId = _stateId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String _regionId) {
        this.regionId = _regionId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String _districtId) {
        this.districtId = _districtId;
    }

    public String getTalukaaId() {
        return talukaaId;
    }

    public void setTalukaaId(String _talukaaId) {
        this.talukaaId = _talukaaId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(Village object1, Village object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((villageName == null ? " " : villageName) + ",");
        sb.append((villageCode == null ? " " : villageCode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (villageId == null) {
            return super.hashCode();
        } else {
            return villageId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.locationmanagement.Village other = (com.app.shared.organization.locationmanagement.Village) obj;
            if (villageId == null) {
                return false;
            } else if (!villageId.equals(other.villageId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
