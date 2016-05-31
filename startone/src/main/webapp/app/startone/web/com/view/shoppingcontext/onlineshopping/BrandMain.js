Ext.define('Startone.startone.web.com.view.shoppingcontext.onlineshopping.BrandMain', {
     "xtype": "brand",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "BrandMainController",
     "restURL": "/Brand",
     "defaults": {
          "split": true
     },
     "requires": ["Startone.startone.shared.com.model.shoppingcontext.onlineshopping.BrandModel", "Startone.startone.web.com.controller.shoppingcontext.onlineshopping.BrandMainController", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ProductModel", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CategoryModel", "Startone.startone.shared.com.viewmodel.shoppingcontext.onlineshopping.BrandViewModel"],
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
               "displayName": "Brand",
               "name": "BrandTreeContainer",
               "itemId": "BrandTreeContainer",
               "restURL": "/Brand",
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
                    "itemId": "BrandTree",
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
                         "fieldId": "CA4318A4-2483-4F7C-8D85-92A334F4D8AD",
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
                         "fieldId": "59FD0586-535D-4162-9431-FB4F139B742C",
                         "restURL": "Category",
                         "bindable": "categoryId"
                    }, {
                         "name": "brandName",
                         "itemId": "brandName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Brand",
                         "fieldId": "DAAC6FE7-FA03-4D8C-8E1D-7000B2374697",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "brandName"
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
                    "displayName": "Brand",
                    "title": "Brand",
                    "name": "Brand",
                    "itemId": "BrandForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "brandId",
                         "itemId": "brandId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Brand Id",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Brand Id<font color='red'> *<\/font>",
                         "fieldId": "49CC483A-DE27-47B9-86D7-1DAD1E962A4A",
                         "hidden": true,
                         "value": "",
                         "bindable": "brandId"
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
                         "fieldId": "CA4318A4-2483-4F7C-8D85-92A334F4D8AD",
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
                         "fieldId": "59FD0586-535D-4162-9431-FB4F139B742C",
                         "restURL": "Category",
                         "bindable": "categoryId",
                         "columnWidth": 0.5
                    }, {
                         "name": "brandName",
                         "itemId": "brandName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Brand",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Brand<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "DAAC6FE7-FA03-4D8C-8E1D-7000B2374697",
                         "minLength": "0",
                         "maxLength": "256",
                         "bindable": "brandName",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "48EF76F3-D471-4D2C-BE98-EE92C897B36A",
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
                         "customId": 221,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 221,
                              "customId": 545
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 221,
                              "customId": 546,
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
                              "parentId": 221,
                              "customId": 547,
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
                    "displayName": "Brand",
                    "title": "Details Grid",
                    "name": "BrandGrid",
                    "itemId": "BrandGrid",
                    "restURL": "/Brand",
                    "store": [],
                    "columns": [{
                         "header": "Brand Id",
                         "dataIndex": "brandId",
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
                         "dataIndex": "brandName",
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
               "displayName": "Brand",
               "title": "Brand",
               "name": "Brand",
               "itemId": "BrandForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "brandId",
                    "itemId": "brandId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Brand Id",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Brand Id<font color='red'> *<\/font>",
                    "fieldId": "49CC483A-DE27-47B9-86D7-1DAD1E962A4A",
                    "hidden": true,
                    "value": "",
                    "bindable": "brandId"
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
                    "fieldId": "CA4318A4-2483-4F7C-8D85-92A334F4D8AD",
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
                    "fieldId": "59FD0586-535D-4162-9431-FB4F139B742C",
                    "restURL": "Category",
                    "bindable": "categoryId",
                    "columnWidth": 0.5
               }, {
                    "name": "brandName",
                    "itemId": "brandName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Brand",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Brand<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "DAAC6FE7-FA03-4D8C-8E1D-7000B2374697",
                    "minLength": "0",
                    "maxLength": "256",
                    "bindable": "brandName",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "48EF76F3-D471-4D2C-BE98-EE92C897B36A",
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
                    "customId": 221,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 221,
                         "customId": 545
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 221,
                         "customId": 546,
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
                         "parentId": 221,
                         "customId": 547,
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