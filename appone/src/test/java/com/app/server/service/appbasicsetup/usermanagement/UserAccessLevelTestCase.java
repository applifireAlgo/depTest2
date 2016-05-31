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
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("1j1opMwVaj7bpxDCdJUtWq2Zyu69exY61WehGsLaYySYv3hpBA");
        useraccesslevel.setLevelDescription("RtSLecf5eZ3B2t4sYvnLXRN8QaEIFjoeX7U8StLuoYQIHLZnix");
        useraccesslevel.setLevelIcon("XXXcCyfMAEdVT3P9VtEkKIXYJYyjk8uRiaShYCTyaFCU7L35Xs");
        useraccesslevel.setLevelName("tyJyZDVzaN8rUbmg0qjGZbIDUvyVRMmTY47xLQ7kbPwfRe1Za5");
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
            useraccesslevel.setUserAccessLevel(98504);
            useraccesslevel.setLevelHelp("oXKIazDAtEMwUBHX4eN0E9lEYzhqYBZmMbEWWD2lPfsLWehnJ6");
            useraccesslevel.setLevelDescription("pFo7rpDaCWVHPorQNkl8Unwgd2GJraHdBnistkZPO2wwhouhJB");
            useraccesslevel.setLevelIcon("ri3B7mDdYqQD0gfpz33Cb5jMwOzzAHK80Y2nztGO19nMLBkXdE");
            useraccesslevel.setLevelName("hEIjBh3GuRiZXcPKk3ogGf3dVUJzuQVlskl40oNcmkY8iyA2a2");
            useraccesslevel.setVersionId(1);
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 180732));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "xZgodUuA5qqOS4065ThPhGT0PEr5ciBBXXSXRPziPP7ZfKsdWnk0PT2EZRS7BmkOmaY1Dj3lS1ccaOv0X9j6PfJL9Ydev49wHoXksG9LAH1BhCy5HtgvmzNKK9hJIYVUFCoUB18lsOIhxiz2e93oYx8y2Y2ykUH4ILbOUpKtlL87AnVm2iP795SvZsyFYWyUHIFUNIySL1EAwwqSQOmAvVmFnj1ZTyJxxuuWawEfi11bT9CZQoKgKj6KycViXC6eL"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "yMqHnymAAqzFFg9ch7eL7B7Yf3spJcf2tbeCxG09DTGMNhIKXqMRlZzXKp2MXETCNKcxnKVpvHyQNbvepzsitnnJ6V1dR9plGFijoRbAK6Hsqx1ENlvgk2TcDnosPCXojGukWJv8VwhDCanAcH1D8Ct0t7aV6QLacb8JsF8lQ0ekkTRGr0y68UVrpTvIG9aWkiKURwNOSSIUIHD8J7ljiJhPeej6TkRtn0IZVsAbjCuX53SKJmjYbeVNgeYs5aYX4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "IsvRq5uA5X4Q6Ag0AmgSerjcU6dCWqddCPPqMlqi3IfhLrXNgz5VcvnbEsNLoD3oqzLTaVwYJj5grvjLe5xs8y8dCS81gthsQAdHZQLeCvYyOvxahXdxHXIMMLkH8bOCodZGtQwzXt2sYDYG2r8yPZSlVTCWbm1aZitreK2P6dLKDrVQonMNLgeSMYq34gfJ3X7lfnUeefJvjfMoCO3KLtzZj8PRdiH2DbzfrJ7I2XlGuQyCSgbm999ngJDKvN8eG"));
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
