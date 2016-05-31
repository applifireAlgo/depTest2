package com.app.server.service.shoppingcontext.onlineshopping;
import org.springframework.web.bind.annotation.RestController;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.http.HttpStatus;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.app.server.repository.shoppingcontext.onlineshopping.CartRepository;
import com.app.shared.shoppingcontext.onlineshopping.Cart;
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
import com.app.server.businessservice.shoppingcontext.onlineshopping.AddToCartService;

@RestController
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Service for Cart Transaction table", complexity = Complexity.MEDIUM)
@RequestMapping("/Cart")
public class CartServiceImpl extends CartService {

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CartRepository<Cart> cartrepo;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.Cart> lstcart = cartrepo.findAll();
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "findAll", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody List<Cart> entity, @RequestHeader("isArray") boolean request) throws Exception {
        cartrepo.save(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS122100201");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "save", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
    @Override
    public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws Exception {
        cartrepo.delete(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS128100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "delete", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody Cart entity) throws Exception {
        cartrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", entity._getPrimarykey());
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "update", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
    @Override
    public HttpEntity<ResponseBean> update(@RequestBody List<Cart> entity, @RequestHeader("isArray") boolean request) throws Exception {
        cartrepo.update(entity);
        AppAlarm appAlarm = Log.getAlarm("nullOS123100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "update", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/search", consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> search(@RequestBody Map<String, Object> fieldData) throws Exception {
        List<java.lang.Object> lstcart = cartrepo.search("Cart.DefaultFinders", fieldData, getFieldMetaData());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "search", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    private Map<String, String> getFieldMetaData() {
        java.util.Map<java.lang.String, java.lang.String> fieldMetaData = new java.util.HashMap<java.lang.String, java.lang.String>();
        fieldMetaData.put("itemId", "String");
        fieldMetaData.put("itemPrice", "double");
        fieldMetaData.put("itemQuantity", "integer");
        fieldMetaData.put("subTotal", "double");
        fieldMetaData.put("userId", "String");
        return fieldMetaData;
    }

    @RequestMapping(value = "/findByItemId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByItemId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.app.shared.shoppingcontext.onlineshopping.Cart> lstcart = cartrepo.findByItemId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("nullOS124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "save", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findByUserId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByUserId(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        List<com.app.shared.shoppingcontext.onlineshopping.Cart> lstcart = cartrepo.findByUserId((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("nullOS124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "save", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws Exception {
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        com.app.shared.shoppingcontext.onlineshopping.Cart lstcart = cartrepo.findById((java.lang.String) findByBean.getFindKey());
        AppAlarm appAlarm = Log.getAlarm("nullOS124100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new ResponseBean(appAlarm);
        responseBean.add("message", String.format(appAlarm.getMessage(), "Cart"));
        responseBean.add("data", lstcart);
        Log.out.println("nullOS124100200", runtimeLogInfoHelper.getRequestHeaderBean(), "CartServiceImpl", "save", "Cart");
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, HttpStatus.valueOf(appAlarm.getAlarmStatus()));
    }

    @Autowired
    private AddToCartService addtocartservice;

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody Cart entity) throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("nullOS247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        addtocartservice.addToCart(entity);
        responseBean.add("message", String.format(appAlarm.getMessage(), "AddToCartService", "addToCart"));
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.app.server.businessservice.shoppingcontext.onlineshopping.AddToCartService", "addToCart");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}
