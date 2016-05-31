Ext.define('Startone.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Startone.view.appreportui.ReportViewController',
	            'Startone.view.appreportui.datagrid.DataGridPanel',
	            'Startone.view.appreportui.datagrid.DataGridView',
	            'Startone.view.appreportui.querycriteria.QueryCriteriaView',
	            'Startone.view.appreportui.chart.ChartView',
	            'Startone.view.appreportui.datapoint.DataPointView',
	            'Startone.view.googlemaps.map.MapPanel',
	            'Startone.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
