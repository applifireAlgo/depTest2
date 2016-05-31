package com.app.server.service.shoppingcontext.onlineshopping;
import com.app.server.businessservice.shoppingcontext.onlineshopping.FetchCartItems;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.athena.server.pluggable.utils.bean.ResponseBean;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/FetchCartItemsWS")
public class FetchCartItemsWS {

    @Autowired
    private FetchCartItems fetchcartitems;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @RequestMapping(value = "/fetchCartItems", consumes = "application/json", method = RequestMethod.POST)
    public HttpEntity<ResponseBean> fetchCartItems() throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("nullOS247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.Cart> _ruleOutput = fetchcartitems.fetchCartItems();
        responseBean.add("success", true);
        responseBean.add("message", "Successfully executed ");
        responseBean.add("data", _ruleOutput);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.app.server.businessservice.shoppingcontext.onlineshopping.FetchCartItems", "fetchCartItems");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}
