Ext.define('Startone.view.databrowsercalendar.DBCalendar', {
	extend : 'Startone.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Startone.view.databrowsercalendar.DBCalendarController',
	             'Startone.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
