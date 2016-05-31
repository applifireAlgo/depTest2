package com.app.server.businessservice.shoppingcontext.onlineshopping;
import com.app.server.businessservice.shoppingcontext.FetchCartDetailsBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.spartan.server.session.bizService.SessionDataMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException;
import java.util.List;

@Component
public class FetchCartDetails {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchCartDetailsBzService fetchCartDetailsBzService;

    @Autowired
    private SessionDataMgtService sessionService;

    public List<com.app.shared.shoppingcontext.FetchCartDetails> fetchcartDetails() throws SessionDataNotFoundException, Exception {
        java.lang.String userIdFromSession = (java.lang.String) sessionService.getSessionData("userId");
        if (userIdFromSession == null) {
            throw new com.spartan.pluggable.exception.layers.ds.SessionDataNotFoundException();
        }
        java.util.List<com.app.shared.shoppingcontext.FetchCartDetails> fetchCartDetailsList1 = fetchCartDetailsBzService.executeQueryFetchCartDetails(userIdFromSession);
        return fetchCartDetailsList1;
    }
}
