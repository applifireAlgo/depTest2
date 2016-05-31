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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;

@Table(name = "ast_OrderDetails_TP")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "OrderDetails", complexity = Complexity.LOW)
public class OrderDetails implements Serializable, CommonEntityInterface, Comparator<OrderDetails> {

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
    @NotNull
    @Min(0)
    @Max(10000000)
    private Double subTotal;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "orderItemId")
    @JsonProperty("orderItemId")
    @GeneratedValue(generator = "UUIDGenerator")
    private String orderItemId;

    @Column(name = "itemId")
    @JsonProperty("itemId")
    private String itemId;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private OrderMain orderMain;

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
        if (_subTotal != null) {
            this.subTotal = _subTotal;
        }
    }

    public String getPrimaryKey() {
        return orderItemId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return orderItemId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String _orderItemId) {
        this.orderItemId = _orderItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String _itemId) {
        this.itemId = _itemId;
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

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain _orderMain) {
        this.orderMain = _orderMain;
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

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (orderItemId == null) {
            return super.hashCode();
        } else {
            return orderItemId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.shoppingcontext.onlineshopping.OrderDetails other = (com.app.shared.shoppingcontext.onlineshopping.OrderDetails) obj;
            if (orderItemId == null) {
                return false;
            } else if (!orderItemId.equals(other.orderItemId)) {
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

    @Transient
    @JsonIgnore
    private String fieldName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String _fieldName) {
        this.fieldName = _fieldName;
    }

    @Override
    public int compare(OrderDetails object1, OrderDetails object2) {
        switch(((fieldName))) {
            case "subTotal":
                return (object1.getSubTotal().compareTo(object2.getSubTotal()) == 0) ? 0 : ((object1.getSubTotal().compareTo(object2.getSubTotal()) > 0) ? 1 : -1);
            case "orderItemId":
                return (object1.getOrderItemId().compareTo(object2.getOrderItemId()) == 0) ? 0 : ((object1.getOrderItemId().compareTo(object2.getOrderItemId()) > 0) ? 1 : -1);
        }
        return 0;
    }
}
