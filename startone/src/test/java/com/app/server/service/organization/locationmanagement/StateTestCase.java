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
        country.setCapitalLatitude(10);
        country.setCountryCode2("gow");
        country.setCountryFlag("Ei9BBiGblCek7lIBO6P9wsTAiS1prbqIAfoKg6NDUiQNUvNpOh");
        country.setCountryCode1("9N6");
        country.setCapitalLongitude(3);
        country.setCurrencyName("dBAXS1jaJcqxaLLrmhh5E5vb1pxXeYf7sueyQhEPFSFbxkZyL0");
        country.setCapital("M1Ifcqyem6jliBqlCFcSwCEkh1xjiwfz");
        country.setCurrencyCode("7lB");
        country.setCountryName("5Z1xr2cPjO0NYGMgbYFBzxk8GexgFQePACGVbwWEPlq2w3aAg9");
        country.setCurrencySymbol("qqQ4EJeT0BcyqM2DEzoPF2yeNAmHgeJQ");
        country.setIsoNumeric(165);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(10);
        state.setStateFlag("jFfQcBMM84nhZinLyqhnIQvOZQOIgWGShHRiTiJo9EYVfly6yh");
        state.setStateDescription("UJzyWAbWEkaVLZJkoKHoJpBgCMdJVIxFTsgIVGFQRiYqM1dFww");
        state.setStateCodeChar2("WufultFPA954L7uD1tJhGznJ7xtV7fsl");
        state.setStateName("gsz143PtwzoshzICCUNrqWUxTzpIAhC24jS2KboJdT1ve2HMAr");
        state.setStateCode(1);
        state.setStateCodeChar3("JBFe1sqOSyBy2H2INkojDBNY827iTdE6");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapital("2I2OUZluI43uA95nExgYU7AnFKiqsG2hqyHeIRhBrAjs7cGc6i");
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
            state.setStateCapitalLongitude(11);
            state.setStateCapitalLatitude(7);
            state.setStateFlag("PjYFQsQrxYgeaWgyv4e0kG4delwo1C1bTYxLimEV1lnXFJRdLP");
            state.setStateDescription("tIpWBGk2mLW2yradBRqxRZwv6jZZYVmaym3nsiK3wXemvr7Pn2");
            state.setStateCodeChar2("J5SaJAacwm1SOfeuyhj1lfs8wO9D4MVf");
            state.setVersionId(1);
            state.setStateName("ue71jqr98S99wcQmjduOr30nii60UaU5trPJLwSKzYC1Rffxyj");
            state.setStateCode(2);
            state.setStateCodeChar3("YWXhXU0Hi2ca4dYUgjLYPxaq28W1aHnv");
            state.setStateCapital("PWlaUONzV7MgOzgF103CxWJ2TnaT29PMQpCfhlwGRVNOKETjgO");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "p9idUcl7dFYDYKE81TsFMJyIoMhevAHi1u3mudc31or73lh28wwNMcKlNfUA7cbhhB1BqyUm4suVtZgt3OwHvFl42ESyk6IRiH9zAYF9FOFeM3OKtzlTtB3iOimlgy9DcFAWYVPgAfty29xsvu5Pkq6AIvz4ZMOyOuPGIWSueTgmLXHRbH3VROiUA2uDbvVlH53zpmiW7Tz4QcEikme53KJH2nGGRRiV7WN36nlbKWsURWaPU0TBTo15bPit2ynlO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "pO9NrkK8viHuoQgMS0k8AlWWNvyqfnqvh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "sl6VZm3HOXxEjGnHgsZXTyS984oEEwDPb"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "pIL9DSqZQCO9SHqLj4BSdRTLzk5ZgO64DjFMYbAuaf6JMchM0FGxK5pPnWzoP5FBLhXpCi6EZTskkrZAQbBgltNg6deZIPmogkpi9GTaTO9KqTqcn3JcJezTxlU3qKR0G4qTPoyFiKXsLsxdeRdwRe9skHbYLzlF7FS8fhMilKnq2fHfDx6qaOImqztDuMeCzRRxNZNeCzptVzoSCdd0hbV789Bv0pygWSuM0kvaf8fbiGflImZYECi7XAgO2C14v"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "KMVj4T07g01Rm72hJLceSi1MjQrDZEjS35qAzBd7S0Hx2JPgCFogeHoBu3XNoUGnHQRyzdd3MpJ331XohBwxhi57TNcNciUMlwQq7xlyv1Rhnec6fFdCmmJgxig2G6M3P"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "yxpDeDsVr7x7JXX5lUztj5OSXpBAXRK5ST0mD0k6bPQ2MnhtmxFgugUi5fbiwYqGCJMrtSCTblwWotrd0Xt3iuO4RBePVyjDyz2WSXTiMiydKR1JZ5W3Yl1zJwhqtZ16T"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 15));
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
