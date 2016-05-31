package com.app.server.service.shoppingcontext.onlineshopping;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.shoppingcontext.onlineshopping.CategoryRepository;
import com.app.shared.shoppingcontext.onlineshopping.Category;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.shoppingcontext.onlineshopping.Product;
import com.app.server.repository.shoppingcontext.onlineshopping.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CategoryTestCase extends EntityTestCriteria {

    @Autowired
    private CategoryRepository<Category> categoryRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Category createCategory(Boolean isSave) throws Exception {
        Product product = new Product();
        product.setProductName("G21jxWoV6OzOJY0qEN9d0JJlIrx9M0iNX9wia38U0YsaJ8DnDw");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        Category category = new Category();
        category.setProductId((java.lang.String) ProductTest._getPrimarykey());
        category.setCategoryName("1feo1OKclW3oFlz4xN0vlz4LdnPRtb2eSGJuAXnUfhTdJtnENV");
        category.setEntityValidator(entityValidator);
        return category;
    }

    @Test
    public void test1Save() {
        try {
            Category category = createCategory(true);
            category.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            category.isValid();
            categoryRepository.save(category);
            map.put("CategoryPrimaryKey", category._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CategoryPrimaryKey"));
            Category category = categoryRepository.findById((java.lang.String) map.get("CategoryPrimaryKey"));
            category.setVersionId(1);
            category.setCategoryName("ilEoQ8ZSUX8WZaqSO3keZOydKRkljxLEj7EaU5J5VRMZAboxH0");
            category.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            categoryRepository.update(category);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByproductId() {
        try {
            java.util.List<Category> listofproductId = categoryRepository.findByProductId((java.lang.String) map.get("ProductPrimaryKey"));
            if (listofproductId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CategoryPrimaryKey"));
            categoryRepository.findById((java.lang.String) map.get("CategoryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CategoryPrimaryKey"));
            categoryRepository.delete((java.lang.String) map.get("CategoryPrimaryKey")); /* Deleting refrenced data */
            productRepository.delete((java.lang.String) map.get("ProductPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCategory(EntityTestCriteria contraints, Category category) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            category.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            category.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            category.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            categoryRepository.save(category);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "categoryName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "categoryName", "tVOUVlvribDcENUagCQLEXlp1x8R8chsIllNERVp9owdxbzOCUC3Nbm1XQHJBQNQnzioKXUUuaAwr4b29tZDj7aVCGN1lxjHAJn9c8LEzmgoMzIWDbcMWtwvLUYaK6vL77iJ37juJzGVBQBrGsQP5SjWSTLIdGu1mpVPCwtObeCgJHndyP3yphK0TztYs34z5OF5ZKV581gqVYUFd7hplwAhFCyg87ZWaB3JXudmHisrmLUCqCIC0KDS9gusNvL9U"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Category category = createCategory(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = category.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(category, null);
                        validateCategory(contraints, category);
                        failureCount++;
                        break;
                    case 2:
                        category.setCategoryName(contraints.getNegativeValue().toString());
                        validateCategory(contraints, category);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
