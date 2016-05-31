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
import com.app.server.repository.appbasicsetup.usermanagement.PasswordAlgoRepository;
import com.app.shared.appbasicsetup.usermanagement.PasswordAlgo;
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
public class PasswordAlgoTestCase extends EntityTestCriteria {

    @Autowired
    private PasswordAlgoRepository<PasswordAlgo> passwordalgoRepository;

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

    private PasswordAlgo createPasswordAlgo(Boolean isSave) throws Exception {
        PasswordAlgo passwordalgo = new PasswordAlgo();
        passwordalgo.setAlgoIcon("UtajQ3aUTYrmY3bh8Td3aov1WO6ZSSxvR7u6KuZgA9ZqVvCz2d");
        passwordalgo.setAlgorithm(1);
        passwordalgo.setAlgoHelp("F2XjHrLKv3TTRfsXIpg1GjNlusA5NMyLIgGB3VwfbNkEyyImSo");
        passwordalgo.setAlgoDescription("ZOt4Aj5um9dw11aDlW1kwwNSkKAE52q4RI4f0f6Y5fil7aYUSi");
        passwordalgo.setAlgoName("RXICslVKWY3dlvy2NPZlPifrP7VchXJBBwJbruSkWDJrNaT8Kl");
        passwordalgo.setEntityValidator(entityValidator);
        return passwordalgo;
    }

    @Test
    public void test1Save() {
        try {
            PasswordAlgo passwordalgo = createPasswordAlgo(true);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordalgo.isValid();
            passwordalgoRepository.save(passwordalgo);
            map.put("PasswordAlgoPrimaryKey", passwordalgo._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            PasswordAlgo passwordalgo = passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
            passwordalgo.setAlgoIcon("iz5rBcVN6xAOxI9sX0GGHgOWavvch61ayCxP9Not4LyZ3gzbVu");
            passwordalgo.setAlgorithm(2);
            passwordalgo.setAlgoHelp("ynGcyC4K1UEaObns2m7Qq2h2A2zMFz35ln01grY3wD0EESAv5O");
            passwordalgo.setAlgoDescription("fNPPIabZWM5hkaw2QkScqWuH0pYQl4OZxKbPA7pd2au2ho5kAM");
            passwordalgo.setAlgoName("Y358cxaM4EnKmDjt5a59OuhwuoYNZuIfVmmYjhouX544lPBbMh");
            passwordalgo.setVersionId(1);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordalgoRepository.update(passwordalgo);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.delete((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordAlgo(EntityTestCriteria contraints, PasswordAlgo passwordalgo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordalgoRepository.save(passwordalgo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "algorithm", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "algorithm", 18));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "algoName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "algoName", "UsVw0hckwWRULTzTtqSpDzhg0Gqvy2mP9YqnVF4lxCQ2l0p0px8RsRo2nx8NZzGf0ztdds3GHXx6eevQGO4cxivvOI8aHGngpzTF839m4y5wzw3v2A5mwracntSThZdlRKWp4uk6oA7Jr5uqereN4vMQCBB2GaJhrIRDsxgOn5oNapuI5nUfWnre7X42atNkooTrl8DpK8ZgYqKvCiowhnP7rQ2pETxVxeVxByLSyUaKsC5k9P9qSbYHZ8rXSztHw"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "algoDescription", "MJNiESQGF27MhjQoeavOdmpgPBdwXr80fvotjYOW211Q4O6wLuQpFH0O8PBp95t7COekPVIAGeVtxiXOflbBA57pR8mpprH2mz5kg9Z6xtHkCpi3v4cwZ35g8PR3VnLq9L29yGMT2TjTSLu36KWmaz0ULfjdCv69uVEBmOIoYe4Y5G5Qr4ffTGtQlM7hDP12afvlleSIs9tStyu3aM0vE6IQz0Z4szHfDhEI6AeKFWQth9sG1OA7dB1ECY6XgVWOG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "algoIcon", "ofmLtxYB6eSduEYX31D3Y0bQbqoWfTpGNO7Ebk0yMRelWx47hTPOJ8EeCJj59v6eR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "algoHelp", "qU1NeUPb5EChbwEwH22j5FvbLfHmtNMC5F2bIXTGdfIKxtBLTdqJL493r06AmTIr4iMDtG7QZSfcsBIVdey7C2ZJlFAW0d9BASy7NoC4YKEvn3eufN83oIcmKK0gaNkhTerWttOU3yt2JYxzfoNTCWsAlTmPrjYn9k17MjHIYDQrcmQCDGJpYvMERpwxePCvtCsrVHUtLF2ezV8GaMuLJudkDfD2G9kw5heDBepFtXtt1IJMaat1SJSV9xRczXB9F"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                PasswordAlgo passwordalgo = createPasswordAlgo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordalgo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 2:
                        passwordalgo.setAlgorithm(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 4:
                        passwordalgo.setAlgoName(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 5:
                        passwordalgo.setAlgoDescription(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 6:
                        passwordalgo.setAlgoIcon(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 7:
                        passwordalgo.setAlgoHelp(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
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
