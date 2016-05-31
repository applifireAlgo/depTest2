Ext.define('Startone.startone.shared.com.model.organization.locationmanagement.CurrencyModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "currencyId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "countryid",
          "reference": "Country",
          "defaultValue": ""
     }, {
          "name": "currencyCode",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "unicodeDecimal",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "unicodeHex",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});