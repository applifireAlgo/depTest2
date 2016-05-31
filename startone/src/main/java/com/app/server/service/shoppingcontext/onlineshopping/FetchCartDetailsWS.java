package com.app.server.service.shoppingcontext.onlineshopping;
import com.app.server.businessservice.shoppingcontext.onlineshopping.FetchCartDetails;
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
@RequestMapping("/FetchCartDetailsWS")
public class FetchCartDetailsWS {

    @Autowired
    private FetchCartDetails fetchcartdetails;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @RequestMapping(value = "/fetchcartDetails", consumes = "application/json", method = RequestMethod.POST)
    public HttpEntity<ResponseBean> fetchcartDetails() throws Exception {
        com.spartan.pluggable.logger.alarms.AppAlarm appAlarm = Log.getAlarm("nullOS247100200");
        com.athena.server.pluggable.utils.bean.ResponseBean responseBean = new com.athena.server.pluggable.utils.bean.ResponseBean(appAlarm);
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        java.util.List<com.app.shared.shoppingcontext.FetchCartDetails> _ruleOutput = fetchcartdetails.fetchcartDetails();
        responseBean.add("success", true);
        responseBean.add("message", "Successfully executed ");
        responseBean.add("data", _ruleOutput);
        Log.out.println(appAlarm.getAlarmID(), runtimeLogInfoHelper.getRequestHeaderBean(), "com.app.server.businessservice.shoppingcontext.onlineshopping.FetchCartDetails", "fetchcartDetails");
        return new org.springframework.http.ResponseEntity<com.athena.server.pluggable.utils.bean.ResponseBean>(responseBean, httpStatus);
    }
}
