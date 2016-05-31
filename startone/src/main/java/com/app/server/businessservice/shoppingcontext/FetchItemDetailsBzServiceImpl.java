package com.app.server.businessservice.shoppingcontext;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.FetchItemDetails;
import java.lang.Override;
import java.util.List;

@Component
public class FetchItemDetailsBzServiceImpl implements FetchItemDetailsBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchItemDetails> executeQueryFetchItemDetails() throws Exception {
        java.util.List<com.app.shared.shoppingcontext.FetchItemDetails> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.FetchItemDetails>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "84319D0C-A7C3-45A6-A5CE-2B835942D410");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.FetchItemDetails", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
