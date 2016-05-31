package com.app.shared.shoppingcontext.onlineshopping;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
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

@Table(name = "ast_Cart_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Cart", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Cart.DefaultFinders", query = "select e from Cart e where e.systemInfo.activeStatus=1 and e.itemId LIKE :itemId and e.itemPrice BETWEEN :minitemPrice AND :maxitemPrice and e.itemQuantity BETWEEN :minitemQuantity AND :maxitemQuantity and e.subTotal BETWEEN :minsubTotal AND :maxsubTotal and e.userId LIKE :userId"), @javax.persistence.NamedQuery(name = "Cart.findByItemId", query = "select e from Cart e where e.systemInfo.activeStatus=1 and e.itemId=:itemId"), @javax.persistence.NamedQuery(name = "Cart.findByUserId", query = "select e from Cart e where e.systemInfo.activeStatus=1 and e.userId=:userId"), @javax.persistence.NamedQuery(name = "Cart.findById", query = "select e from Cart e where e.systemInfo.activeStatus=1 and e.cartId =:cartId") })
public class Cart implements Serializable, CommonEntityInterface, Comparator<Cart> {

    @Column(name = "itemPrice")
    @JsonProperty("itemPrice")
    @Min(0)
    @Max(10000000)
    private Double itemPrice;

    @Column(name = "itemQuantity")
    @JsonProperty("itemQuantity")
    @NotNull
    @Min(0)
    @Max(1000000000)
    private Integer itemQuantity;

    @Column(name = "subTotal")
    @JsonProperty("subTotal")
    @Min(0)
    @Max(10000000)
    private Double subTotal;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "cartId")
    @JsonProperty("cartId")
    @GeneratedValue(generator = "UUIDGenerator")
    private String cartId;

    @Column(name = "itemId")
    @JsonProperty("itemId")
    private String itemId;

    @Column(name = "userId")
    @JsonProperty("userId")
    private String userId;

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

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double _itemPrice) {
        this.itemPrice = _itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer _itemQuantity) {
        if (_itemQuantity != null) {
            this.itemQuantity = _itemQuantity;
        }
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double _subTotal) {
        this.subTotal = _subTotal;
    }

    public String getPrimaryKey() {
        return cartId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String _cartId) {
        this.cartId = _cartId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String _itemId) {
        this.itemId = _itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String _userId) {
        this.userId = _userId;
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
    public int compare(Cart object1, Cart object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (cartId == null) {
            return super.hashCode();
        } else {
            return cartId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.shoppingcontext.onlineshopping.Cart other = (com.app.shared.shoppingcontext.onlineshopping.Cart) obj;
            if (cartId == null) {
                return false;
            } else if (!cartId.equals(other.cartId)) {
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
