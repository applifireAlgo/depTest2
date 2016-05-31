package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuIcon("Qi1HQufkyMDqeFyJEBo0i9ayWJELrLocTcBqCbDsyev59garDq");
        appmenus.setRefObjectId("rChOxt5sDvu6IR8Qzrhdp6Ydp3yUoDoNayb1UjbkfvXi2wBSZ0");
        appmenus.setMenuHead(true);
        appmenus.setMenuTreeId("01AaW9mlFizMFQXH90jnUO7xWsPkwSy2UekfeKeTH07vMd8taY");
        appmenus.setMenuAction("Fem6x3Yh2xJp1lLQA2e25FYV3lrxfjll7xmkxBIC5lkxp4uz7y");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuCommands("m6jl2gU8S8q45qRL2sbfiODFSzSn0q4LLi8HQJkas1K8GdQv21");
        appmenus.setUiType("SZb");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("R9fct8yCefAhG9YPMSUpJEpNYxiEAgcUMqAuzpf3FrlC1Z11E9");
        appmenus.setAppType(2);
        appmenus.setAppId("VjCC3wLerUTFTsX64mmG2xwbRjpoE8MEDIADkV3KZIHwUeLyMM");
        appmenus.setMenuAccessRights(11);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuIcon("y3PX0VeEaY0nic82pknwUw98KngztXsp4kKHBytS2LGodDne0y");
            appmenus.setRefObjectId("XPMsQdy28KNPhdFgRVhB7BFRSF69vAkJC1zvPv2worUjA7RnlF");
            appmenus.setMenuTreeId("jeVR7Nb9l173XlsCuWZmWDrF4uKr0LQvGcI21nv8MkL8XpdbiX");
            appmenus.setMenuAction("qzfRzTLcaZWJ5w88VTDAGzkZuQ33krANdQJ3SI3m9g25eK8nrk");
            appmenus.setMenuCommands("DQxcKRMmrNTsItWLMFWlAngVhhUEW9nqV3OqIm0MttnxgFvUbV");
            appmenus.setUiType("wAm");
            appmenus.setVersionId(1);
            appmenus.setMenuLabel("bSyjARJ0wU7YdG4zM67rRaLDgFRVAECG3u2WWy9Iut1hqLOIyZ");
            appmenus.setAppType(1);
            appmenus.setAppId("z7k5pOTOaaGzUhCjaVz4XcWxBy5zZ5to2O7GaOTURcplLiuMIw");
            appmenus.setMenuAccessRights(2);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "qVZA6qXohCsY2mNj631v4ofWELyN4rVJHgtcuDlFSIGuM68lTA7VaXOwv9jgXFNxA0hEnY17qMagZRzbwjcb704TQUxzcS3Fddp8kktLf6ZSyGuLbmfCyApvXgvBxK5jarSPsPXDFV8MEQTDtDNGJWTqzpEPEYfzBx69DQ7SF9fnhAA6PwkpWDbDw190HaIa2SOHQpOZfqLweRdC3LhcoVZVUlsrzQUeY8fSsEclwPRZc0bR4kEISXnkUlWiUPLSu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "BcofelHoSpr7FqSlewfNbZ6WGiNCofe7DobIRgEXSSxcdmHTZNCCMFEegLEGgkeqXDiw7wuqymx2sLMqg1UlgCSXYiMjCePM1WGcGRd5YMeDtAVyN55DeublwvQ6PBeyUWnwcipGXeOACDAxLTHF4rTkEfNmUYNlcIoaAxwDMMDOOhXUSHLJvhAR48zlObS4CNiRKsUl7ZPVqJvLZAYZd41gIM24wMGfAt7X2IrkU9oGVAAg1ri6BdSZKtZpMonGs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "ZloJFKUbmi2AXEcW2cso6GSqjRrFgmU0cZ2GAjc0pwNQQgfWLrj33seGJJh1AvtwfIJsaaaqDn8YIctXPRJvyDtmLfbrDK8ibUPBaWqAMTghHGXtKAuTBEvlQPbnPYVO8yM47V1OQsq6T4NizVSqaOJgFUhkNssubbbgt9Bb48cu1Yf4t57FsZR1w15T1yDWTZrM7rIcsVjQBaqbs8Fn7xz9uUUH4eAkruXAzARRQ1Gb2UkzJFy4rSewhPb8bVMks"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "lCsjf6PRJjaFNbD532XowkJRFVAmJwFBfOhlXZ4FSDXeURW7IBkoDlkTTQaZCMryW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "LU46OYvqLbdubAsy1YghcWE237rv0DUpJQuagip334PoBs8eLl2jFvMChmFfeDoQqWgdXo2v8pEVx8qpmu0jwJUPoGH0o6LVJKxdTlhCjsI18CAfEqG9ICrk1VmK9gGIhU2Zx9i2U0p6UfqkCsWmrmWM35CkRWxlUrMDlqISiuZ5TJYzI2cDv1iVrZyutFRGx2Vkb2V0K7W5c1UnT43qbtiZlgIgnVNtOW8AWP3TbYQCx1DHOzzZ7AicaphrjOYuo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "RNZQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "G6Cfq4keTZbaui4VQFy9ywM0WlLiwSclls91AOUi9vrjGeIJZnRYyzbWLxjvvIx0faPi3iU1fAOoAr8vmkQaSuSBF8eAelJBmQyYcXIDRf7AGu4wTWeTzEOTqLfxYq2ecLYmiLt1XTFdmTWjJHtKv35U9J9k14CZSATBOFEXASka1CxWmZF6Qb2ftOIIJPqfAeNDoXNhyFYs07fbrda4vYGFczs6T9DmPNGrl0E0CJImaWCkci85RmMw3UqDMF3ir"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "IJWtDi4IVoyjYY3QubFj5pKDI36jbWodTxK1atpcQBIOpPxK1WAUv8zold1RxTihenqRQGNbEed85RT88htMXSzDBbxehaDZRf5YW7IxandyswrW19RzrQj6Pujy3InBhjjnGwvPXzcSyoLeHDf8pnwLhyF4ZygVsWpTe0tK4my6GbJMT96IZnjQGZtoktQrZ6wBD2mo6d4D4nlQdY20tHIlqAMwq8xvsO6wzssFTkdpqKkOGfK6o7NoMYQb9iA1B"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
