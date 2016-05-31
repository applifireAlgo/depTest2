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
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapital("Kz2sBgmoddIicVwK5GAhi6FpdYn7VJUJ");
        country.setCountryName("swGeo9kx7PpuMpNCPVBXsIS8SCqNlvVdJdJhu4ywVjVKigBgDX");
        country.setCurrencyName("L5i4rf77bvUsdVNLRq9U3t92COZM6kRj80GclYd7UZe1OXawMu");
        country.setCountryCode2("2VN");
        country.setCapitalLatitude(5);
        country.setCountryFlag("wr84LXcTtYZCEvvpqCGzRvvGmpeMXGFxL0XVjvG9tmZBDIqCtm");
        country.setCurrencySymbol("FCiYNOaZ2ktXvDd0mNAYEvW1LKUoOp8Z");
        country.setIsoNumeric(787);
        country.setCurrencyCode("ISE");
        country.setCapitalLongitude(1);
        country.setCountryCode1("Sj2");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar2("Q4eTasIFDrQXfFbTb7ojCH5Lnkrgm5eV");
        state.setStateFlag("1fWPwaf8xZALLK14YFgf2e6d3Rq5d9v93ekewl7cMCg2XStFxI");
        state.setStateName("cEezrdu3ebD6Fw4nXx5PiV36HTnZ2hB0yEqidxqm1pPCi8J0kg");
        state.setStateCode(1);
        state.setStateCapital("6QJKY72H8dRG6seu3XseHhZSFhqVjef4yEc9SULezmyeT9ybXv");
        state.setStateCodeChar3("CO5JstIdaxAtC0RGgUfxAMFQTw6fXV18");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapitalLatitude(1);
        state.setStateDescription("usJkrcqqwuH7wOdXoGeJuPX672nDUUN6BoAlpTbNAigeeNRLNO");
        state.setStateCapitalLongitude(6);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCodeChar2("HLFTCOCXX8Xow9X8Ew9AXvncGmUmoC2V");
            state.setVersionId(1);
            state.setStateFlag("JDeVaHJMflSeeRdAgHTSmh51F4kuATVf8ZE0vM9f6wSpZnqoHr");
            state.setStateName("4AueJQ149HuBm7UKeHWnkopwrkh78tBleVEl1B5N221p19bBtW");
            state.setStateCode(1);
            state.setStateCapital("0ZXJlPQTBy9cu18tjdZC9tX8CnY8QAwVFVaR52kuWcBRXNHyOh");
            state.setStateCodeChar3("bArjiPhNLFkTTu6mHSSCwJPO8s8v6u8b");
            state.setStateCapitalLatitude(4);
            state.setStateDescription("h1kVUbxMAhKStw2LNEolJU2DNogbrxdKKycTfP32mB3TtietOo");
            state.setStateCapitalLongitude(5);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "hVT1kiytlf9P8WYbPbn8XoZ4Q7o9mbQGJKRbEMF7JtghgW9YDhxcCg93QZ9AjSDEICuPK64gjqkvvMjO9hRmbrTr2x0nEPfF1vj9pHSC3jhUUtut1dUJ4DwBVsqS7DmIXTjROplorc9FDekgvvivi7sJT1q5xhgJHQNCBoGrfuhcFeACsPq8kAKmUA1TaCS8HgyZfJKWfD1UrTY32fnB6zqEDuMX79qwFUcPHY4scqGjXjMZH4TTt5njolGeRqqaj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "TxY5PP5pJNdnx6080ZagtlkQVjwhY2AuV"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "NiEBbjwXRErA4h1yCTNkzUPAwrUVwYxzr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "4hkPZHAX2DWRvAsd8VxafrtQT4RD9lHzPkzlJESV9XlZuc7jk71Qjz6py21Xb2bs5AZVf9TeSikwErrbI5jZncaHGUlco6jRR4xJq5jvIcsqaZ5iCScsjfvxO9nonLc13thSoU0LBLQbqA2a2JCxpbrtAE8tvFefh5VUHSodDAmRaL4i1NP0Av5YnHxGs9YEf1k5VizRO2ltnH93AS8L3qbXYh5fEYC45eDpiexW8hjv4zvr1HfqKdyUPBluWCEQf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "IWmCn6Jn8pd9VytRVXpnNwsluYP4j8VECBRGHb9O4tVcmwJCsvwLQxpCoLa6tY5vKrHu2c7gWUJEnxZZfZccApDJAoExehBxdzS0c6rfQziREaEbLfVqRjXXjddMZFv01"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "afW1dKxLHM4HEluEW9MQ50r1RRhEPJYMibB8mx8QcdvGL0Kli4f4oe0pWmgx1qUQio6spvWza3w3c6oV3ulyPU4SomaTNPjmMFrJ9B9pZCWdCatmYy42OdbsMNJ0oEBbt"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 19));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
