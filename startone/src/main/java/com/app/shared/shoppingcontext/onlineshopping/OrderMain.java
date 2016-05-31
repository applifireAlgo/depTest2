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
import java.sql.Timestamp;
import javax.persistence.Column;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_OrderMain_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "OrderMain", complexity = Complexity.LOW)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "orderId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "OrderMain.DefaultFinders", query = "select e from OrderMain e where e.systemInfo.activeStatus=1 and e.orderDate > :orderDate and e.grandTotal BETWEEN :mingrandTotal AND :maxgrandTotal and e.userId LIKE :userId"), @javax.persistence.NamedQuery(name = "OrderMain.findByUserId", query = "select e from OrderMain e where e.systemInfo.activeStatus=1 and e.userId=:userId"), @javax.persistence.NamedQuery(name = "OrderMain.findById", query = "select e from OrderMain e where e.systemInfo.activeStatus=1 and e.orderId =:orderId") })
public class OrderMain implements Serializable, CommonEntityInterface, Comparator<OrderMain> {

    @Column(name = "orderDate")
    @JsonProperty("orderDate")
    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp orderDate;

    @Column(name = "grandTotal")
    @JsonProperty("grandTotal")
    @Min(0)
    @Max(10000000)
    private Double grandTotal;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "orderId")
    @JsonProperty("orderId")
    @GeneratedValue(generator = "UUIDGenerator")
    private String orderId;

    @Column(name = "userId")
    @JsonProperty("userId")
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "orderMain")
    private List<OrderDetails> orderDetails;

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

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp _orderDate) {
        this.orderDate = _orderDate;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double _grandTotal) {
        this.grandTotal = _grandTotal;
    }

    public String getPrimaryKey() {
        return orderId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String _orderId) {
        this.orderId = _orderId;
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

    public OrderMain addOrderDetails(OrderDetails _orderDetails) {
        if (this.orderDetails == null) {
            this.orderDetails = new java.util.ArrayList<com.app.shared.shoppingcontext.onlineshopping.OrderDetails>();
        }
        if (_orderDetails != null) {
            _orderDetails.setOrderMain(this);
            this.orderDetails.add(_orderDetails);
        }
        return this;
    }

    public OrderMain removeOrderDetails(OrderDetails _orderDetails) {
        if (this.orderDetails != null) {
            this.orderDetails.remove(_orderDetails);
        }
        return this;
    }

    public OrderMain addAllOrderDetails(List<OrderDetails> _orderDetails) {
        if (this.orderDetails == null) {
            this.orderDetails = new java.util.ArrayList<com.app.shared.shoppingcontext.onlineshopping.OrderDetails>();
        }
        if (_orderDetails != null) {
            for (int i = 0; i < _orderDetails.size(); i++) {
                _orderDetails.get(i).setOrderMain(this);
            }
            this.orderDetails.addAll(_orderDetails);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfOrderDetails() {
        if (this.orderDetails != null) {
            return this.orderDetails.size();
        }
        return 0;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> _orderDetails) {
        for (int i = 0; i < _orderDetails.size(); i++) {
            if (_orderDetails.get(i).getOrderMain() == null) {
                _orderDetails.get(i).setOrderMain(this);
            }
        }
        this.orderDetails = _orderDetails;
    }

    @JsonIgnore
    public List<OrderDetails> getDeletedOrderDetailsList() {
        List<OrderDetails> orderdetailsToRemove = new java.util.ArrayList<OrderDetails>();
        for (OrderDetails _orderdetails : orderDetails) {
            if (_orderdetails.isHardDelete()) {
                orderdetailsToRemove.add(_orderdetails);
            }
        }
        orderDetails.removeAll(orderdetailsToRemove);
        return orderdetailsToRemove;
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
        setValidatororderDetails(_validateFactory);
    }

    private void setValidatororderDetails(EntityValidatorHelper<Object> _validateFactory) {
        for (int i = 0; i < orderDetails.size(); i++) {
            orderDetails.get(i).setEntityValidator(_validateFactory);
        }
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
        if (this.orderDetails == null) {
            this.orderDetails = new java.util.ArrayList<OrderDetails>();
        }
        for (OrderDetails _orderDetails : orderDetails) {
            _orderDetails.setEntityAudit(customerId, userId, recordType);
            _orderDetails.setSystemInformation(recordType);
        }
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
        if (this.orderDetails == null) {
            this.orderDetails = new java.util.ArrayList<OrderDetails>();
        }
        for (OrderDetails _orderDetails : orderDetails) {
            _orderDetails.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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
    public int compare(OrderMain object1, OrderMain object2) {
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
        if (orderId == null) {
            return super.hashCode();
        } else {
            return orderId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.shoppingcontext.onlineshopping.OrderMain other = (com.app.shared.shoppingcontext.onlineshopping.OrderMain) obj;
            if (orderId == null) {
                return false;
            } else if (!orderId.equals(other.orderId)) {
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

    public Double getTotalSubTotal() {
        Double sum = 0d;
        if (orderDetails.size() > 0 && orderDetails != null) {
            for (OrderDetails field : orderDetails) {
                sum += field.getSubTotal();
            }
            return sum;
        }
        return 0d;
    }
}
