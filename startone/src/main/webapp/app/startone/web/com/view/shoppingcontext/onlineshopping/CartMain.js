Ext.define('Startone.startone.web.com.view.shoppingcontext.onlineshopping.CartMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "cart",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "CartMainController",
     "restURL": "/Cart",
     "defaults": {
          "split": true
     },
     "requires": ["Startone.startone.shared.com.model.shoppingcontext.onlineshopping.CartModel", "Startone.startone.web.com.controller.shoppingcontext.onlineshopping.CartMainController", "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ItemModel", "Startone.startone.shared.com.model.appbasicsetup.usermanagement.UserModel", "Startone.startone.shared.com.viewmodel.shoppingcontext.onlineshopping.CartViewModel"],
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
               "displayName": "Cart",
               "name": "CartTreeContainer",
               "itemId": "CartTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "CartTree",
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
                         "name": "itemId",
                         "itemId": "itemId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "Item Id",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ItemModel"
                         },
                         "fieldLabel": "Item Id",
                         "fieldId": "76E2157B-9B09-492F-8ABB-B8AD7A3EC8DD",
                         "restURL": "Item",
                         "bindable": "itemId"
                    }, {
                         "name": "itemPrice",
                         "itemId": "itemPrice",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Price",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Price",
                         "fieldId": "2CABB53E-A89C-488B-8309-1F72463D3622",
                         "minValue": "0",
                         "maxValue": "10000000",
                         "bindable": "itemPrice"
                    }, {
                         "name": "itemQuantity",
                         "itemId": "itemQuantity",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Quantity",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Quantity",
                         "fieldId": "334D7FFA-2619-4839-8177-ABDEDDBDCCEF",
                         "minValue": "0",
                         "maxValue": "1000000000",
                         "bindable": "itemQuantity"
                    }, {
                         "name": "subTotal",
                         "itemId": "subTotal",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "Sub Total",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Sub Total",
                         "fieldId": "F23C5FC8-93F9-4D99-AC4E-2F8E34384BC9",
                         "minValue": "0",
                         "maxValue": "10000000",
                         "bindable": "subTotal"
                    }, {
                         "name": "userId",
                         "itemId": "userId",
                         "xtype": "combo",
                         "customWidgetType": "vdCombo",
                         "displayName": "User",
                         "margin": "5 5 5 5",
                         "valueField": "primaryKey",
                         "displayField": "primaryDisplay",
                         "typeAhead": true,
                         "queryMode": "local",
                         "minChars": 2,
                         "store": {
                              "data": [],
                              "model": "Startone.startone.shared.com.model.appbasicsetup.usermanagement.UserModel"
                         },
                         "fieldLabel": "User",
                         "fieldId": "C0CB500A-D84B-4F4C-AA29-E2C8A9AF6632",
                         "restURL": "User",
                         "bindable": "userId"
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
                    "xtype": "form",
                    "displayName": "Cart",
                    "name": "Cart",
                    "itemId": "CartForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "cartId",
                                   "itemId": "cartId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Cart Id",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Cart Id<font color='red'> *<\/font>",
                                   "fieldId": "D4CDEA22-CBFE-40D4-8AC2-B5C2E7E4B10E",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "cartId"
                              }, {
                                   "name": "itemId",
                                   "itemId": "itemId",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Item Id",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ItemModel"
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Item Id<font color='red'> *<\/font>",
                                   "fieldId": "76E2157B-9B09-492F-8ABB-B8AD7A3EC8DD",
                                   "restURL": "Item",
                                   "bindable": "itemId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "itemPrice",
                                   "itemId": "itemPrice",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Price",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Price",
                                   "fieldId": "2CABB53E-A89C-488B-8309-1F72463D3622",
                                   "minValue": "0",
                                   "maxValue": "10000000",
                                   "bindable": "itemPrice",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "itemQuantity",
                                   "itemId": "itemQuantity",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Quantity",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Quantity<font color='red'> *<\/font>",
                                   "allowBlank": false,
                                   "fieldId": "334D7FFA-2619-4839-8177-ABDEDDBDCCEF",
                                   "minValue": "0",
                                   "maxValue": "1000000000",
                                   "bindable": "itemQuantity",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "subTotal",
                                   "itemId": "subTotal",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "Sub Total",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Sub Total",
                                   "fieldId": "F23C5FC8-93F9-4D99-AC4E-2F8E34384BC9",
                                   "minValue": "0",
                                   "maxValue": "10000000",
                                   "bindable": "subTotal",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "userId",
                                   "itemId": "userId",
                                   "xtype": "combo",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "User",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "store": {
                                        "data": [],
                                        "model": "Startone.startone.shared.com.model.appbasicsetup.usermanagement.UserModel"
                                   },
                                   "fieldLabel": "User",
                                   "fieldId": "C0CB500A-D84B-4F4C-AA29-E2C8A9AF6632",
                                   "restURL": "User",
                                   "bindable": "userId",
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
                                   "fieldId": "C6D992A1-7920-48C9-9B31-FDEC157E47A1",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "listeners": {},
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Cart",
                    "title": "Details Grid",
                    "name": "CartGrid",
                    "itemId": "CartGrid",
                    "store": [],
                    "requires": [],
                    "columns": [{
                         "header": "Cart Id",
                         "dataIndex": "cartId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Item Id",
                         "dataIndex": "itemId",
                         "flex": 1
                    }, {
                         "header": "Price",
                         "dataIndex": "itemPrice",
                         "flex": 1
                    }, {
                         "header": "Quantity",
                         "dataIndex": "itemQuantity",
                         "flex": 1
                    }, {
                         "header": "Sub Total",
                         "dataIndex": "subTotal",
                         "flex": 1
                    }, {
                         "header": "User",
                         "dataIndex": "userId",
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
               "xtype": "form",
               "displayName": "Cart",
               "name": "Cart",
               "itemId": "CartForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "cartId",
                              "itemId": "cartId",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Cart Id",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Cart Id<font color='red'> *<\/font>",
                              "fieldId": "D4CDEA22-CBFE-40D4-8AC2-B5C2E7E4B10E",
                              "hidden": true,
                              "value": "",
                              "bindable": "cartId"
                         }, {
                              "name": "itemId",
                              "itemId": "itemId",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "Item Id",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Startone.startone.shared.com.model.shoppingcontext.onlineshopping.ItemModel"
                              },
                              "allowBlank": false,
                              "fieldLabel": "Item Id<font color='red'> *<\/font>",
                              "fieldId": "76E2157B-9B09-492F-8ABB-B8AD7A3EC8DD",
                              "restURL": "Item",
                              "bindable": "itemId",
                              "columnWidth": 0.5
                         }, {
                              "name": "itemPrice",
                              "itemId": "itemPrice",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Price",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Price",
                              "fieldId": "2CABB53E-A89C-488B-8309-1F72463D3622",
                              "minValue": "0",
                              "maxValue": "10000000",
                              "bindable": "itemPrice",
                              "columnWidth": 0.5
                         }, {
                              "name": "itemQuantity",
                              "itemId": "itemQuantity",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Quantity",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Quantity<font color='red'> *<\/font>",
                              "allowBlank": false,
                              "fieldId": "334D7FFA-2619-4839-8177-ABDEDDBDCCEF",
                              "minValue": "0",
                              "maxValue": "1000000000",
                              "bindable": "itemQuantity",
                              "columnWidth": 0.5
                         }, {
                              "name": "subTotal",
                              "itemId": "subTotal",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "Sub Total",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Sub Total",
                              "fieldId": "F23C5FC8-93F9-4D99-AC4E-2F8E34384BC9",
                              "minValue": "0",
                              "maxValue": "10000000",
                              "bindable": "subTotal",
                              "columnWidth": 0.5
                         }, {
                              "name": "userId",
                              "itemId": "userId",
                              "xtype": "combo",
                              "customWidgetType": "vdCombo",
                              "displayName": "User",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "store": {
                                   "data": [],
                                   "model": "Startone.startone.shared.com.model.appbasicsetup.usermanagement.UserModel"
                              },
                              "fieldLabel": "User",
                              "fieldId": "C0CB500A-D84B-4F4C-AA29-E2C8A9AF6632",
                              "restURL": "User",
                              "bindable": "userId",
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
                              "fieldId": "C6D992A1-7920-48C9-9B31-FDEC157E47A1",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "listeners": {},
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});