package com.app.server.businessservice.shoppingcontext;
import com.app.shared.shoppingcontext.FetchCartDetails;
import java.util.List;

public interface FetchCartDetailsBzService {

    public List<FetchCartDetails> executeQueryFetchCartDetails(String userId1) throws Exception;
}
