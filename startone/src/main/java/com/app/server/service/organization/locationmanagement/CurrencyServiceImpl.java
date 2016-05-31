package com.app.server.service.organization.locationmanagement;
import org.springframework.web.bind.annotation.RestController;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.organization.locationmanagement.CurrencyRepository;
import com.app.shared.organization.locationmanagement.Currency;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import com.athena.server.pluggable.utils.bean.FindByBean;

@RestController
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Currency Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/Currency")
public class CurrencyServiceImpl extends CurrencyService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CurrencyRepository<Currency> currencyrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.app.shared.organization.locationmanagement.Currency> lstcurrency = currencyrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("nullLM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", lstcurrency);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "findAll", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Currency entity) throws Exception {
        currencyrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("nullLM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "save", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Currency> entity, @RequestHeader("isArray") boolean request) throws Exception {
        currencyrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("nullLM122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "save", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        currencyrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("nullLM128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "delete", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Currency entity) throws Exception {
        currencyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullLM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "update", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Currency> entity, @RequestHeader("isArray") boolean request) throws Exception {
        currencyrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullLM123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "update", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lstcurrency = currencyrepo.search("Currency.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("nullLM124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", lstcurrency);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "search", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<java.lang.String, java.lang.String> fieldMetaData = new java.util.HashMap<java.lang.String, java.lang.String>();
        fieldMetaData.put("currencyId", "String");
        fieldMetaData.put("countryId", "String");
        return fieldMetaData;
    }

    @RequestMapping(value = "/findByCountryId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByCountryId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.app.shared.organization.locationmanagement.Currency> lstcurrency = currencyrepo.findByCountryId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullLM124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", lstcurrency);
        Log.out.println("nullLM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "save", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.app.shared.organization.locationmanagement.Currency lstcurrency = currencyrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullLM124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Currency"));
        responseBean.add("data", lstcurrency);
        Log.out.println("nullLM124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CurrencyServiceImpl", "save", "Currency");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
