package com.app.shared.shoppingcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;

public class FetchItemDetails implements DTOMapperInterface {

    private String categoryName;

    private String brandName;

    private String productName;

    private String itemId;

    private String itemName;

    private Double itemPrice;

    private String itemIcon;

    public FetchItemDetails(Object[] aryObject) {
        if (aryObject != null) {
            categoryName = (java.lang.String) aryObject[0];
            brandName = (java.lang.String) aryObject[1];
            productName = (java.lang.String) aryObject[2];
            itemId = (java.lang.String) aryObject[3];
            itemName = (java.lang.String) aryObject[4];
            itemPrice = (java.lang.Double) aryObject[5];
            itemIcon = (java.lang.String) aryObject[6];
        } else {
            categoryName = null;
            brandName = null;
            productName = null;
            itemId = null;
            itemName = null;
            itemPrice = null;
            itemIcon = null;
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getProductName() {
        return productName;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getItemIcon() {
        return itemIcon;
    }
}
