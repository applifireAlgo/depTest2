package com.app.server.service.shoppingcontext.onlineshopping;
import org.springframework.web.bind.annotation.RestController;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.shoppingcontext.onlineshopping.CategoryRepository;
import com.app.shared.shoppingcontext.onlineshopping.Category;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Category Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/Category")
public class CategoryServiceImpl extends CategoryService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CategoryRepository<Category> categoryrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.Category> lstcategory = categoryrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", lstcategory);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "findAll", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Category entity) throws Exception {
        categoryrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", entity);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "save", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Category> entity, @RequestHeader("isArray") boolean request) throws Exception {
        categoryrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "save", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        categoryrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "delete", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Category entity) throws Exception {
        categoryrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "update", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Category> entity, @RequestHeader("isArray") boolean request) throws Exception {
        categoryrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "update", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lstcategory = categoryrepo.search("Category.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", lstcategory);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "search", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<java.lang.String, java.lang.String> fieldMetaData = new java.util.HashMap<java.lang.String, java.lang.String>();
        fieldMetaData.put("productId", "String");
        fieldMetaData.put("categoryName", "String");
        return fieldMetaData;
    }

    @RequestMapping(value = "/findByProductId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByProductId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.app.shared.shoppingcontext.onlineshopping.Category> lstcategory = categoryrepo.findByProductId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", lstcategory);
        Log.out.println("nullOS124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "save", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.app.shared.shoppingcontext.onlineshopping.Category lstcategory = categoryrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Category"));
        responseBean.add("data", lstcategory);
        Log.out.println("nullOS124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CategoryServiceImpl", "save", "Category");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }
}
