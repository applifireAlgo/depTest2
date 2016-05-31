package com.app.server.businessservice.shoppingcontext;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.shoppingcontext.FetchCartDetails;
import java.lang.Override;
import java.util.List;

@Component
public class FetchCartDetailsBzServiceImpl implements FetchCartDetailsBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<FetchCartDetails> executeQueryFetchCartDetails(String userId1) throws Exception {
        java.util.List<com.app.shared.shoppingcontext.FetchCartDetails> listDtoInterface = new java.util.ArrayList<com.app.shared.shoppingcontext.FetchCartDetails>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "D0485D2C-17CD-4C12-9B7C-067B7238CC29");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectUserId1 = new atg.taglib.json.util.JSONObject();
            jsonObjectUserId1.put("name", "userId");
            jsonObjectUserId1.put("value", userId1);
            jsonObjectUserId1.put("datatype", "VARCHAR");
            jsonObjectUserId1.put("index", 1);
            jsonArray.add(jsonObjectUserId1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.shoppingcontext.FetchCartDetails", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
