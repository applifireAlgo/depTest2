package com.app.shared.shoppingcontext.onlineshopping;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemTemplate implements EntityValidatorInterface {

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    @Min(0)
    @Max(65535)
    private String itemId;

    @Min(0)
    @Max(65535)
    private String itemName;

    @Min(0)
    @Max(65535)
    private String itemIcon;

    @Min(0)
    @Max(65535)
    private String productName;

    @Min(0)
    @Max(65535)
    private String categoryName;

    @Min(0)
    @Max(65535)
    private String brandName;

    @Min(0)
    @Max(65535)
    private String itemPrice;

    @Min(-2147483648L)
    @Max(2147483647)
    private Integer itemQuantity;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String _itemId) {
        this.itemId = _itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String _itemName) {
        this.itemName = _itemName;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String _itemIcon) {
        this.itemIcon = _itemIcon;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String _productName) {
        this.productName = _productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String _categoryName) {
        this.categoryName = _categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String _brandName) {
        this.brandName = _brandName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String _itemPrice) {
        this.itemPrice = _itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer _itemQuantity) {
        this.itemQuantity = _itemQuantity;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
