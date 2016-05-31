package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("J6gitdIAKidLQ25t9NdouLYYepcdREhYkiH2TAPvwpibJINSae");
        useraccesslevel.setLevelIcon("cKRIOYyefeb58wAWI03T4WEJxvnDTbvhUfd9nMl74JaDHyE6NI");
        useraccesslevel.setLevelHelp("vYOwdG5ic2gyP0EbQrBk9mr90PVVjAU55Ndwmzd8m89ojZd4cA");
        useraccesslevel.setLevelName("eRR9B2StfhmJcBbkYX24hhjCtrYBfAdBRYEBqsmwptOQpjQ0PJ");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("C4dIcEpAWDEQixugD0ePxfbqCH9TQNiXpYCI5XBAZpGae3ukg0");
            useraccesslevel.setLevelIcon("GNZiCOOZnpm6fihBq8JDBaiCKizmcUNsBExMVGnKOUMs6URSFv");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelHelp("fe1lQ5NfoeaozPdCr3CTBTCccSJuvdnYJi8hGyt1uiXXNre4xd");
            useraccesslevel.setLevelName("DdnAbxbRHZQj0yqvpU5wQObX9F55XOhHl716Z5LJtgaKCfX4vZ");
            useraccesslevel.setUserAccessLevel(18287);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 181898));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "aAZ7wchaZlMICUjCCOHxJZJ1KONZc12RK21MP2cE8jyf9ArcnmqVGulxHFF9Z7srJgIXGr4m5cgv2zQQZ68HIt7NYNkbsR0ohQSLHgF9juBUTD9P2JZUIRnha9YkDjnwPYbouaosuwuPPVAPEkCuJw1Sgvje0SeYj1GA9yutzJQmtUxNKdp3RvsQRXu7D9ZRcRWnq1T9SWGfbuJcrJ8i6bn6sHVkpDwjSqFjJVE1AwRTdLTFpRz54x2hgg2vDlk14"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "8fViDb75bGgTD1C4nugHSZZiGuif6ueIyXQrIbKWshaypEK1ILuclm41FO789qW7SP6StXp8nFuYKv6JeX3yXTxyRYSkOikwBDfp9pgE4CWc0sOtu2FhaK8yokOOEPtaEVzMhSCxrMEmDtjBrOo4C0x7Q7qmCCNaShj8aYykNMQbk7uKYLPCNhtQvTEvxa31ivNPWlkWCN591Bno5OcHY0nUQ8ItNNuYExvAmYZM7H90XnXMzCXmRUP25Lk4v1WO9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "ot4ApO2sVc3sJGX0C5sclNcpcp2BYphrMKbMmoKf0bjz75txaaapKqiVL0Utc1C59Y1gQqq8VRSiGQo0uBZXIShf1oxQMmurSrqs20lXAgb9VWAJbUVwimQZItkTIyfg3yDZ4JNRI0nbzQXzg5TqiZaHDnlMxwoekoVSrLvd3NhncD2rUe2sEoTfEgzAJ8RKqBMCSPwHDjsFmEPVe9ba5yvGwYBaHz8mDx9BqpSjjMrcckCJ4qhnSgzpVc4cKZV4f"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
