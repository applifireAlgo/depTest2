package com.app.server.repository.shoppingcontext.onlineshopping;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;
import com.app.shared.shoppingcontext.onlineshopping.OrderDetails;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public interface OrderMainRepository<T> extends SearchInterface {

    public List<T> findAll() throws Exception;

    public T save(T entity) throws Exception;

    public List<T> save(List<T> entity) throws Exception;

    public void delete(String id) throws Exception;

    public void deleteOrderDetails(List<OrderDetails> orderdetails);

    public void update(T entity) throws Exception;

    public void update(List<T> entity) throws Exception;

    public List<T> findByUserId(String userId) throws Exception;

    public T findById(String orderId) throws Exception;
}
