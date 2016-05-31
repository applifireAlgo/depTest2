package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("mN3h4FqFd1H9fhM2mhqaaamparzSTALzVYLnVB2tB37JXucGq2");
        timezone.setTimeZoneLabel("DxvQq4ZL1EDx7M1Mr8nAaFasBc37zw4BXlyTpygjHlnl3wBoRS");
        timezone.setCountry("zysRxPZtyiIrVDtUpd4AJI9oc4mzEGptFLDu3RPHYJmp4hGjxj");
        timezone.setUtcdifference(8);
        timezone.setCities("asGktkCAe50orfD4dtk5lXjiuEYeOgM9RIdz4B4zCrcdqE5Lwk");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setGmtLabel("lxcEI1yr2bYjwWRzLYnJu3fS5PGz7H1Xn0aBb0HdqHUXwq3iKL");
            timezone.setVersionId(1);
            timezone.setTimeZoneLabel("GDGHCAPwVKacpP88d02qzYnQ84sqhT4qUvMkIjXR8DIHg65MRd");
            timezone.setCountry("kxo3FvR2g4Hruc0PgkrewfCFnOfZZk04XK6B5M3axx4d3ccKbQ");
            timezone.setUtcdifference(4);
            timezone.setCities("cW23fDsEbb6zWz5jqAimJvrcopgZXnhpmzy2W3dStIvpk2udE0");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "8niYiZMjMyJi9dO0hSXNA6Wvv1uHkqvOY6wQbc6YBV5FtB5Y4hoPcMnMAtQh811TZCLZMzT3Mr0vlBxhNiAI1kmCbue8zrgHmIqZp8uih4RmYfHFhp59Hyu4HdKkFwLjZ5bqIdn55Fi3qvITx4TMzYWqgbB9wekEv2gUMgFLJhcCjLlktGANZwf8ZZ1AAHeG3gjovoWBhcFDhHX0AHE59HoT1wu4HfzB5kiyT4SGxznB5324tIvVemXyzNCdKi26W"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "9RmlcTIBMiaVhd9KDNDizPRxRJUMUwwzSVbVXfDnVcHNzvxWE3pr7vD0pXJFRDpeiYuiGFwaxYh4H9uc01VUaOHRBdcLjcOedCbo9LuvnSS2V87wZnC1pvFMqBqQDyKciXkZbAazsQ650bTVp5Ls4PVsyvWq6QD0PhVrQFO8dOh2UzIkH8wp6kMQk2WXUdLNnXNym92D5g0jueONiy3vbpzgs1OggMlqEBXv9Tp9VvuoWVcNEB7k5nuVzkpqyzYDj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "XTKC7RVYrLEbzPTvd4cOMxYp29taX8DMvkQXuF5TKDpfeST6iuq8wjlpX6h7Rpf8fCBzJAfJYCr33143dYk70phhxN2pucg1Tz5Ynor35F3I1GjO5LAJg2rSdfhTBmda2OnFF25XTBBePap4u9XOvuQLZMgHCEIYiJ9chGKYTunNMixjJQB0C3QHisdP2Mj4dJpoWzTkpRVRtpZqAJmbrd29dSD9AgLtcidNZTEUlClerLVHSxXvxePabkwz71nPO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "AcNFpWisTfaU68bDKS3gklh7mqR2UkK308u7MhX9TZVFvViKMzB8SjETfTTwtuJgIJlPAwAmaqK9TZDw252C0Eg6wwuJDUQflkyS6qKSr7gTitB4Eom3nMpwJCKuHNJ6IT8lqi0bKtnweTv1kggF2wAREQ4cKLHK5Cl1YbphvZaMUVafF6KbZJzhwWSkN9EDguAhNbclTqSWHo5R8tZ2MB351nX3to7cpRhfo0kzVRZlx63zTiudWHyFqgfTTPgmb"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
