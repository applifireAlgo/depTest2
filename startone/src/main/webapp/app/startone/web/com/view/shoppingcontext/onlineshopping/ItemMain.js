Ext.define('Startone.startone.web.com.view.shoppingcontext.onlineshopping.ItemMain', {
     "xtype": "item",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "ItemMainController",
     "restURL": "/Item",
     "defaults": {
          "split": true
     },
     "requires": ["Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ItemModel", "Startone.startone.web.com.controller.shoppingcontext.onlineshopping.ItemMainController", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ProductModel", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CategoryModel", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.BrandModel", "Startone.view.fw.component.FileUploadComponent", "Startone.startone.shared.com.viewmodel.shoppingcontext.onlineshopping.ItemViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Item",
               "name": "ItemTreeContainer",
               "itemId": "ItemTreeContainer",
               "restURL": "/Item",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "ItemTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": [{
                         "name": "productId",
                         "itemId": "productId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Product",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ProductModel"
                         },
                         "fieldLabel": "Product",
                         "fieldId": "96B560F7-9EA4-4D38-8CD5-DD0FC3F7BCF1",
                         "restURL": "Product",
                         "bindable": "productId"
                    }, {
                         "name": "categoryId",
                         "itemId": "categoryId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Category",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CategoryModel"
                         },
                         "fieldLabel": "Category",
                         "fieldId": "DEC74BAA-5EBE-47C7-8A92-552BD1B0BA1A",
                         "restURL": "Category",
                         "bindable": "categoryId"
                    }, {
                         "name": "brandId",
                         "itemId": "brandId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.BrandModel"
                         },
                         "fieldLabel": "Brand",
                         "fieldId": "5A60C110-A2E3-490A-92AC-626E66C576C4",
                         "restURL": "Brand",
                         "bindable": "brandId"
                    }, {
                         "name": "itemName",
                         "itemId": "itemName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Item",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Item",
                         "fieldId": "8AB049B4-BEBF-4B0E-9C4D-943BD50B99ED",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "itemName"
                    }, {
                         "name": "itemPrice",
                         "itemId": "itemPrice",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Price",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Price",
                         "fieldId": "60095D0F-AB68-46C2-A198-C296196CC9F6",
                         "minValue": "0",
                         "maxValue": "10000000",
                         "bindable": "itemPrice"
                    }, {
                         "name": "itemStock",
                         "itemId": "itemStock",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Stock",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Stock",
                         "fieldId": "5F25553C-1415-42F4-ABC5-036A211E376E",
                         "minValue": "0",
                         "maxValue": "1000000000",
                         "bindable": "itemStock"
                    }, {
                         "name": "itemIcon",
                         "itemId": "itemIcon",
                         "xtype": "fileupload",
                         "customWidgetType": "vdFileUpload",
                         "displayName": "Icon",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Icon",
                         "fieldId": "1EB10D77-A31C-4D94-88EC-AC48A1DBC8BE",
                         "bindable": "itemIcon"
                    }]
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Item",
                    "title": "Item",
                    "name": "Item",
                    "itemId": "ItemForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "itemId",
                         "itemId": "itemId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Item Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Item Id<font color='red'> *<\/font>",
                         "fieldId": "6698AAF3-0E64-4CDA-86E6-5035E7CF46BE",
                         "hidden": true,
                         "value": "",
                         "bindable": "itemId"
                    }, {
                         "name": "productId",
                         "itemId": "productId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Product",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ProductModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Product<font color='red'> *<\/font>",
                         "fieldId": "96B560F7-9EA4-4D38-8CD5-DD0FC3F7BCF1",
                         "restURL": "Product",
                         "bindable": "productId",
                         "columnWidth": 0.5,
                         "listeners": {
                              "change": "onProductIdChange"
                         }
                    }, {
                         "name": "categoryId",
                         "itemId": "categoryId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Category",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CategoryModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Category<font color='red'> *<\/font>",
                         "fieldId": "DEC74BAA-5EBE-47C7-8A92-552BD1B0BA1A",
                         "restURL": "Category",
                         "bindable": "categoryId",
                         "columnWidth": 0.5,
                         "listeners": {
                              "change": "onCategoryIdChange"
                         }
                    }, {
                         "name": "brandId",
                         "itemId": "brandId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.BrandModel"
                         },
                         "allowBlank": false,
                         "fieldLabel": "Brand<font color='red'> *<\/font>",
                         "fieldId": "5A60C110-A2E3-490A-92AC-626E66C576C4",
                         "restURL": "Brand",
                         "bindable": "brandId",
                         "columnWidth": 0.5
                    }, {
                         "name": "itemName",
                         "itemId": "itemName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Item",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Item<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "8AB049B4-BEBF-4B0E-9C4D-943BD50B99ED",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "itemName",
                         "columnWidth": 0.5
                    }, {
                         "name": "itemPrice",
                         "itemId": "itemPrice",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Price",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Price<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "60095D0F-AB68-46C2-A198-C296196CC9F6",
                         "minValue": "0",
                         "maxValue": "10000000",
                         "bindable": "itemPrice",
                         "columnWidth": 0.5
                    }, {
                         "name": "itemStock",
                         "itemId": "itemStock",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Stock",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Stock<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "5F25553C-1415-42F4-ABC5-036A211E376E",
                         "minValue": "0",
                         "maxValue": "1000000000",
                         "bindable": "itemStock",
                         "columnWidth": 0.5
                    }, {
                         "name": "itemIcon",
                         "itemId": "itemIcon",
                         "xtype": "fileupload",
                         "customWidgetType": "vdFileUpload",
                         "displayName": "Icon",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Icon<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "1EB10D77-A31C-4D94-88EC-AC48A1DBC8BE",
                         "bindable": "itemIcon",
                         "columnWidth": 0.5,
                         "title": "Icon<font color='red'> *<\/font>"
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "DB87A4AE-1F51-4CE6-9A3A-0E1959816FEF",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 835,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 835,
                              "customId": 105
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 835,
                              "customId": 106,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 835,
                              "customId": 107,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {
                         "scope": "controller"
                    },
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Item",
                    "title": "Details Grid",
                    "name": "ItemGrid",
                    "itemId": "ItemGrid",
                    "restURL": "/Item",
                    "store": [],
                    "columns": [{
                         "header": "Item Id",
                         "dataIndex": "itemId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Product",
                         "dataIndex": "productId",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Category",
                         "dataIndex": "categoryId",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Brand",
                         "dataIndex": "brandId",
                         "renderer": "renderFormValue",
                         "flex": 1
                    }, {
                         "header": "Item",
                         "dataIndex": "itemName",
                         "flex": 1
                    }, {
                         "header": "Price",
                         "dataIndex": "itemPrice",
                         "flex": 1
                    }, {
                         "header": "Stock",
                         "dataIndex": "itemStock",
                         "flex": 1
                    }, {
                         "header": "Icon",
                         "dataIndex": "itemIcon",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "handler": "onGridRefreshClick"
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Item",
               "title": "Item",
               "name": "Item",
               "itemId": "ItemForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "itemId",
                    "itemId": "itemId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Item Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Item Id<font color='red'> *<\/font>",
                    "fieldId": "6698AAF3-0E64-4CDA-86E6-5035E7CF46BE",
                    "hidden": true,
                    "value": "",
                    "bindable": "itemId"
               }, {
                    "name": "productId",
                    "itemId": "productId",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Product",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ProductModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Product<font color='red'> *<\/font>",
                    "fieldId": "96B560F7-9EA4-4D38-8CD5-DD0FC3F7BCF1",
                    "restURL": "Product",
                    "bindable": "productId",
                    "columnWidth": 0.5,
                    "listeners": {
                         "change": "onProductIdChange"
                    }
               }, {
                    "name": "categoryId",
                    "itemId": "categoryId",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Category",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CategoryModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Category<font color='red'> *<\/font>",
                    "fieldId": "DEC74BAA-5EBE-47C7-8A92-552BD1B0BA1A",
                    "restURL": "Category",
                    "bindable": "categoryId",
                    "columnWidth": 0.5,
                    "listeners": {
                         "change": "onCategoryIdChange"
                    }
               }, {
                    "name": "brandId",
                    "itemId": "brandId",
                    "xtype": "combo",
                    "customWidgetType": "vdCombo",
                    "displayName": "Brand",
                    "margin": "5 5 5 5",
                    "valueField": "primaryKey",
                    "displayField": "primaryDisplay",
                    "typeAhead": true,
                    "queryMode": "local",
                    "minChars": 2,
                    "store": {
                         "data": [],
                         "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.BrandModel"
                    },
                    "allowBlank": false,
                    "fieldLabel": "Brand<font color='red'> *<\/font>",
                    "fieldId": "5A60C110-A2E3-490A-92AC-626E66C576C4",
                    "restURL": "Brand",
                    "bindable": "brandId",
                    "columnWidth": 0.5
               }, {
                    "name": "itemName",
                    "itemId": "itemName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Item",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Item<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "8AB049B4-BEBF-4B0E-9C4D-943BD50B99ED",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "itemName",
                    "columnWidth": 0.5
               }, {
                    "name": "itemPrice",
                    "itemId": "itemPrice",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "Price",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Price<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "60095D0F-AB68-46C2-A198-C296196CC9F6",
                    "minValue": "0",
                    "maxValue": "10000000",
                    "bindable": "itemPrice",
                    "columnWidth": 0.5
               }, {
                    "name": "itemStock",
                    "itemId": "itemStock",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "Stock",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Stock<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "5F25553C-1415-42F4-ABC5-036A211E376E",
                    "minValue": "0",
                    "maxValue": "1000000000",
                    "bindable": "itemStock",
                    "columnWidth": 0.5
               }, {
                    "name": "itemIcon",
                    "itemId": "itemIcon",
                    "xtype": "fileupload",
                    "customWidgetType": "vdFileUpload",
                    "displayName": "Icon",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Icon<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "1EB10D77-A31C-4D94-88EC-AC48A1DBC8BE",
                    "bindable": "itemIcon",
                    "columnWidth": 0.5,
                    "title": "Icon<font color='red'> *<\/font>"
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "DB87A4AE-1F51-4CE6-9A3A-0E1959816FEF",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 835,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 835,
                         "customId": 105
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 835,
                         "customId": 106,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 835,
                         "customId": 107,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {
                    "scope": "controller"
               },
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});