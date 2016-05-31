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
        appmenus.setRefObjectId("kAvAA4K0h1JLgrROvHSV47FXnLmkchuTT5KwVSu4bgrHcnKTBF");
        appmenus.setUiType("Fsn");
        appmenus.setAutoSave(true);
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("KQj65ls4MjB3I3s908WEqgLLzrKGh1PKN9VXV0o5oZBBIOQxbo");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("SOkBiV9GmP3eadRfrjPq2OgYWKtFIAfzft52UrvuhxktVsR2Nw");
        appmenus.setAppId("75AHQcJfF4YincGOEFdz24m1QLlMa1KVsRgNrFYZoWKC7H6KtH");
        appmenus.setMenuLabel("8c8kCXyOg9197UabCXaHwLU9lviVmotxvh5YzCo4ac1sXqy2n5");
        appmenus.setAppType(1);
        appmenus.setMenuAction("7BmWeXgDyAUjKbGyrXQxFP9RkGnlTeiupN2ZmxzPppNmltXL26");
        appmenus.setMenuAccessRights(10);
        appmenus.setMenuIcon("4LeUDln4pIUrEJl8N9ivLxuxsiztiYbvfMduBeJzMQ2o3c6PQV");
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
            appmenus.setRefObjectId("EhPdbyZNYsCxc168m9Cj59tKxyf7dOsoiX8d3IfC1LIUGdoAk7");
            appmenus.setUiType("wHo");
            appmenus.setVersionId(1);
            appmenus.setMenuCommands("No5iX0Jvo5F1vsfIMWV1k8ZURIZWht8TAKaZjhTA68SJNV9rHT");
            appmenus.setMenuTreeId("r0XCM5WSOKcbWGOUqEXFv5MjG2RFNllfekfIvcWgSf4CT2RwS1");
            appmenus.setAppId("PV3N0LAAGfZ4nWZUd0rRna6sptveEnncZfdFHPvLDD7nxeI57l");
            appmenus.setMenuLabel("Xnc9D19nsmkam62DQMNVp9Bzh5K7DVnL74Q1TtveymLtulTTA2");
            appmenus.setAppType(2);
            appmenus.setMenuAction("rfPsn8PBRXzVnA4e44iwk6yXe1jxe4Gq4zLhaQzQuegXmVEZ0X");
            appmenus.setMenuAccessRights(1);
            appmenus.setMenuIcon("dtWhzp3rB4XwCF4n2mmNvsU6OyBsmRoNUJXBff7P5GYV92yVbf");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "AWfWDKMW8ae3VxA5A2XoHEtKq6BM0BJXgdoOVfpmprVXmuDdVBtYVLoe4fQshHF6mSGo1fLHDTR0fiS6oPgYTMHu8qEKWLplrqIMcpkTU7Qvx7LMYasvU8csO37LRDHhTJ1y15XAgnAgTQnzGYsazGIEnDVYOe2h1vFfUuu3wNh83X9nQNEcgG7ReNY2El822j1TXRTkD3ZKjwGaaYkmU3wNByc6iqPeWFSJHzHcXtm9YNpddbkVz6qFWup8AmuPh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "nEOvOiFUI627X1u1eUdisK63OLF7LpALrh64yfvXdLoSJ1L3A0nkqxvZjgYIYtgz1xOuRQWOkwYRG4yarAQXoQxJNiKrAAa7JMENCCTIbJOvWPbKpoWiskiPYiAEEIcvnP1Cd11Mqzb7mprpYW49dxdFjAYgoEtHeDzvpOkoP6Ok3nerV68K8qaccddeZIHLneIfIKOEPbBkDTujLxZbsNnRb9Q8euurbTFt3CjgKGZBTCbtnzZgqBNKsx8TdLik7"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "jQDzrn5G2UESyJ7DbcVGMyhXh4ALUP4yufw2YR9CGM6WIAji5TzZjVmPoZV5SzB79xW1K3ayQWzQwpr1yS7EGdMMZGmdIxSVYL2VuJGuYJDJDMfo8g3P0d828tUUKFFpRpTlNVDh6dIzDpw3WvVu9imYT6EJhV8jPt5BBzh3l3ufO0WPptNWKsoX0nE4tKhZ0DKangFakg9o7b9xhn73gGCjRZ6e1gSETO97zAVhUDkanhw2ujxheydRDYrM5S7mS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "7hucXRGMvCUdmVx6WSedMWYnl4dC0b4xwYMy74borvDKqeJTm7YQqhjBDedu3i8qN"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "BKkkSP3qp63Bc7zsCBUFvmqvV18OsavJkoWnZWawMkd983cJbb5V8T3qr0S5boO4qNnIB0DOuvqqa9wBGo9x4UQFZ8pHmE0loSf1YufFLxyJUM9r1s90Gg8GuMqX69L1KB1QSOwNGHaCpGugWNHd4hD6XDKHlwIIfrI1geLKWYMhWCq9OVJl1bOwmzQzcV9XzXhk4sqHfnNlHCDzPaMoJBRmFxBwnQw5QhP2wNQyh99n4KeU2o89a5T3fqQjMDqTJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "EbV0"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "Rn0IsRj8tN5wpWS1X6zxC86e4L2vhyVnczcaQbMj9N6lUXFHDcvpwUme6gXbYsKefEEpKq4JoOP23MGMQv3Hzvf2l6jRsmAvcuJ07v7X2RrD6xuiRFXsvwaEy9kz4uur5ZqVShqY785vewZeD2ydpPr9B3dsvZYw9HcxwNlGuzJRqU54USi44rT1SwPTnsMcgqYv1Idhk8c1zC4zlj8cDyXgGOU5tOqaWI7hGl3a1DvpPPMx3qm4Gfp5BVpUE1MQa"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 21));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "esGyxM3fQji2eAuSLuoPIbVnN0E6Wfv6Kp0BD5yq1TFMnj7KlLiWq8cyW1cGetc0LFlwWKEiyUs22JPWUltIyyKY2cuDyW4t33nsz6CYmoMaejewcpIbzZZY4pNa12CdVfYHiR4WlYDtaaIYiPttqjyT5t0p6BnMJY2CiLiMQtKv1eMEQCtp2cELTwV2kAf3UCSEFkzR5uyBNsaS4PagMGNyThz2gRwbbhlNu7vVosbGZ5VdoE3Hl17cvxUiFGKRW"));
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
