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
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCodeChar2("zlro2J7Pl2z1VaU0eSyN3iwOBh6oZWKw");
        state.setStateFlag("sVDeCo5ufEI70H2Q34ERRAe5brZT9pANX2ksbFJZh0Wqf0q4x0");
        state.setStateName("M2Ig8sRomdqzWdXCmQ2ZX8hUlFK7z1AxUlnW2Yd0N598ufBte1");
        state.setStateCode(1);
        state.setStateCapital("sDI3zzwLaatCyJDUz9zbZencgJQFCxEmLG0GLXcwCgqgFUeSrQ");
        state.setStateCodeChar3("NhogEUIr8jnyRJQlpFE9huI8JynOziM6");
        Country country = new Country();
        country.setCapital("7umKrlvKmm4exWVwWXYAiZWcMD61a9eF");
        country.setCountryName("imPP4NMIdmS9Nuj0dPHganIYlCtAxmCBWXjHseZ0Y4lZdMG328");
        country.setCurrencyName("CRnwDGJANuqcqkbVSgrv6XdbbhLkl5AhPxKCd8zuUaG5HYgRCu");
        country.setCountryCode2("1Sy");
        country.setCapitalLatitude(11);
        country.setCountryFlag("w4SoiGMFZy7qcsO89rDu9DyTaMQka7aTprZQ2iRDecAgk9yQKU");
        country.setCurrencySymbol("IVtfTZV5NEZFgF5wlX7LZq219nHhIhv1");
        country.setIsoNumeric(279);
        country.setCurrencyCode("EuO");
        country.setCapitalLongitude(7);
        country.setCountryCode1("m7K");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar2("uMm2XqUNgKzc6CYmtoMc6wRxHAJ7bGGZ");
        state.setStateFlag("6flERQMhy7yN6QHYQZ2UHXGkmFyVXQmrlGw2qWhXPoCbsKOVCT");
        state.setStateName("EyqVomjDIw0LBNuwbXVs4yf9E0EykMu8qzuwjB8Grcl81aJ6cP");
        state.setStateCode(2);
        state.setStateCapital("YqmxyIEWPNKrsqfzQXlM9nFHfVK5uXkJVss9qWVXQQYrYk74cD");
        state.setStateCodeChar3("gIokcgP8bh7qXsJu6HJ0RPopHuUTMlzK");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(9);
        state.setStateDescription("0iLWy9YNYb02TtXaVYFv7AC4mqEPxqw5zyCsovo228i7rCJmAP");
        state.setStateCapitalLongitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("gO63WlBPzngcpMR52YualGJIo4XNolGb6SxWwqprRAVRbHHuHg");
        city.setCityLongitude(8);
        city.setCityLatitude(3);
        city.setCityName("TjoueEx6cSjMZ9m790oyG1EDQseyV0BuKabVZB8FUn7tyrR9um");
        city.setCityFlag("S9AJLXId8jIyOn5QxbdBgbFhpY7DS2w3UL96bJIxIAC1xgXbOd");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("5MSI1fui3UJuFxUOi61OIAp1KsSJaLEE");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityCode(2);
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityDescription("X0Jk2bIg63epgWsOmK3co5pF2P4Z0yTM10Zq7jGLj82aRBaBWo");
            city.setCityLongitude(1);
            city.setVersionId(1);
            city.setCityLatitude(8);
            city.setCityName("tdo5I4q4jCH4YVwktr45oz5SH7uL0gOJTqSTj4ZYym3LgpZW05");
            city.setCityFlag("wORqWLoU3t8jj7hhQw1aGjcnU8BO3aLwA1CZHLQHAALDr2vIIs");
            city.setCityCodeChar2("GRePJjSBdM9ecVc7uceerp3Akxltixht");
            city.setCityCode(2);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "qv4yPjrYjRg0vzRBrpag5PwWm9MyR3g7bH2qoyDXYdvtj72gp2qpf0bSReF4qTXthSbm3hU60omnVsVRvlRNoOKgfnGJeR3481XGFoE0IYfY7RNkQ6XHMrz8XABbDBiwVjrJKHrbaFTmvjwIJL0XRv7qCoRAnhNbT4SeMwOXyB0xadZuMEzvLqqLyU5NLPWsuUzQtE5cQSXka409TnVkj73XO6Uee7CxGcwjMGpxwqs9TxIeJWqsGpQzhXbo8efJk"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "BqSonICulmg0blB4ZwcUzQOtYYbtfXNlq"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 5));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "gFTlh6k2LbeLvnv4qwBZPyClFJQWYjIkhDQOW2ErA47Y8J3JtatjgqjH7tjBAmL9tdlypreGSHXa89wUymxRctlrb7m0X3CSQJCQSqd4idHRZSt0jBuebOK2IzgcHzGhT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "2KKruFSXwbGJQi7bzyvJeIpOZCxkHG9rbtekrPbwKwZg66fu7FtWAqy6fgzCuyPREe5b0GBihyJXGhrvc00WX7J8JYv4PlhA1EJYko5QRNQGt8yg3MsrHlNHvLpW8DjgI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 20));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 19));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
