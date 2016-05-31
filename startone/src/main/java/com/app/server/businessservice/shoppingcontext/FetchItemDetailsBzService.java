package com.app.server.businessservice.shoppingcontext;
import com.app.shared.shoppingcontext.FetchItemDetails;
import java.util.List;

public interface FetchItemDetailsBzService {

    public List<FetchItemDetails> executeQueryFetchItemDetails() throws Exception;
}
