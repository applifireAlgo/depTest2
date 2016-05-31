package com.app.server.repository.shoppingcontext.onlineshopping;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.shoppingcontext.onlineshopping.OrderMain;
import org.springframework.stereotype.Repository;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.alarms.AppAlarm;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.app.shared.shoppingcontext.onlineshopping.OrderDetails;
import java.util.Map;

@Repository
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "2", comments = "Repository for OrderMain Transaction table", complexity = Complexity.MEDIUM)
public class OrderMainRepositoryImpl extends SearchInterfaceImpl implements OrderMainRepository<OrderMain> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<OrderMain> findAll() throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.OrderMain> query = emanager.createQuery("select u from OrderMain u where u.systemInfo.activeStatus=1").getResultList();
        Log.out.println("nullOS324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    @Override
    @Transactional
    public OrderMain save(OrderMain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("nullOS322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "save", entity);
        return entity;
    }

    @Override
    @Transactional
    public List<OrderMain> save(List<OrderMain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.shoppingcontext.onlineshopping.OrderMain obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("nullOS322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    @Transactional
    @Override
    public void delete(String id) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        com.app.shared.shoppingcontext.onlineshopping.OrderMain s = emanager.find(com.app.shared.shoppingcontext.onlineshopping.OrderMain.class, id);
        emanager.remove(s);
        Log.out.println("nullOS328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteOrderDetails(List<OrderDetails> orderdetails) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.shoppingcontext.onlineshopping.OrderDetails _orderdetails : orderdetails) {
            com.app.shared.shoppingcontext.onlineshopping.OrderDetails s = emanager.find(com.app.shared.shoppingcontext.onlineshopping.OrderDetails.class, _orderdetails.getOrderItemId());
            emanager.remove(s);
        }
    }

    @Override
    @Transactional
    public void update(OrderMain entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("nullOS321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "update", entity);
    }

    @Override
    @Transactional
    public void update(List<OrderMain> entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            com.app.shared.shoppingcontext.onlineshopping.OrderMain obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("nullOS321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    @Transactional
    public List<Object> search(String finderName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery(finderName);
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        Map<String, String> metaData = new java.util.HashMap<String, String>();
        metaData = fieldMetaData;
        String inputStr = "01-01-1850 00:00:00 UTC";
        java.util.Date date = setFormattedDate(inputStr);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            boolean matched = false;
            for (Map.Entry<String, Object> entry1 : fields.entrySet()) {
                if (entry.getKey() == entry1.getKey()) {
                    if (entry.getValue().equalsIgnoreCase("integer") || entry.getValue().equalsIgnoreCase("double") || entry.getValue().equalsIgnoreCase("float") || entry.getValue().equalsIgnoreCase("long")) {
                        map.put("min" + entry1.getKey(), entry1.getValue());
                        map.put("max" + entry1.getKey(), entry1.getValue());
                    } else if (entry.getValue().equalsIgnoreCase("String")) {
                        map.put(entry1.getKey(), "%" + entry1.getValue() + "%");
                    } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DateTime")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), dateValue);
                    } else if (entry.getValue().equalsIgnoreCase("TimeStamp")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), new java.sql.Timestamp(dateValue.getTime()));
                    } else {
                        map.put(entry1.getKey(), entry1.getValue());
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                if (entry.getValue().equalsIgnoreCase("String")) {
                    map.put(entry.getKey(), "%");
                } else if (entry.getValue().equalsIgnoreCase("integer")) {
                    map.put("min" + entry.getKey(), Integer.MIN_VALUE);
                    map.put("max" + entry.getKey(), Integer.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("double")) {
                    map.put("min" + entry.getKey(), java.lang.Double.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Double.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("long")) {
                    map.put("min" + entry.getKey(), java.lang.Long.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Long.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("float")) {
                    map.put("min" + entry.getKey(), java.lang.Float.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Float.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DATETIME")) {
                    map.put(entry.getKey(), date);
                } else if (entry.getValue().equalsIgnoreCase("TINYINT")) {
                    map.put(entry.getKey(), 1);
                } else if (entry.getValue().equalsIgnoreCase("timestamp")) {
                    map.put(entry.getKey(), timestamp);
                } else if (entry.getValue().equalsIgnoreCase("integer_userAccesCode")) {
                    map.put(entry.getKey(), runtimeLogInfoHelper.getUserAccessCode());
                }
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        java.util.List<Object> list = query.getResultList();
        Log.out.println("nullOS324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    @Transactional
    public List<OrderMain> findByUserId(String userId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("OrderMain.findByUserId");
        query.setParameter("userId", userId);
        java.util.List<com.app.shared.shoppingcontext.onlineshopping.OrderMain> listOfOrderMain = query.getResultList();
        Log.out.println("nullOS324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findByUserId", "Total Records Fetched = " + listOfOrderMain.size());
        return listOfOrderMain;
    }

    @Transactional
    public OrderMain findById(String orderId) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        javax.persistence.Query query = emanager.createNamedQuery("OrderMain.findById");
        query.setParameter("orderId", orderId);
        com.app.shared.shoppingcontext.onlineshopping.OrderMain listOfOrderMain = (com.app.shared.shoppingcontext.onlineshopping.OrderMain) query.getSingleResult();
        Log.out.println("nullOS324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "OrderMainRepositoryImpl", "findById", "Total Records Fetched = " + listOfOrderMain);
        return listOfOrderMain;
    }
}
