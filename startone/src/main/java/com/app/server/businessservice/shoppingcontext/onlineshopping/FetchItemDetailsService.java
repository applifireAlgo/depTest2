package com.app.server.businessservice.shoppingcontext.onlineshopping;
import com.app.server.businessservice.shoppingcontext.FetchItemDetailsBzService;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.app.shared.shoppingcontext.FetchItemDetails;
import java.util.List;

@Component
public class FetchItemDetailsService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private FetchItemDetailsBzService fetchItemDetailsBzService;

    public List<FetchItemDetails> fetchItemDetails() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.FetchItemDetails> fetchItemDetailsList1 = fetchItemDetailsBzService.executeQueryFetchItemDetails();
        return fetchItemDetailsList1;
    }
}
