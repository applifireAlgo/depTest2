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
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(11);
        state.setStateFlag("c4z8Za43jtjDL7VGqmT4n7TIG9Nv6IhIwvVKgaWIADbNQ9NZmA");
        state.setStateDescription("CHjx19c0CerW1t70P6jfKv2P6ymTJs73HozQSToJ89AiWpJx00");
        state.setStateCodeChar2("jEg7e8ecIvPvY5e1sYy0jlagaby1J0o6");
        state.setStateName("RagIsE81JM2GJovtnpj3lycuxxLTDwKgoz6Ec6I5wnkz8SZhGY");
        state.setStateCode(2);
        state.setStateCodeChar3("53OvzYAl3r7djYneKaMYLAcxlBeuG3Eq");
        Country country = new Country();
        country.setCapitalLatitude(4);
        country.setCountryCode2("IAE");
        country.setCountryFlag("KmhFsgvWk7eJ9yqvrKCdRSTAB2oinzC0gHFe7WV21xSqwQkt6a");
        country.setCountryCode1("pnb");
        country.setCapitalLongitude(8);
        country.setCurrencyName("27zDrRxl9WhfjJKU546mJxhmPlSILDEdSViu5oehsUzTcjmhsz");
        country.setCapital("xcn6JZcsEDmiNxRMPURaLSwb0OfdEefa");
        country.setCurrencyCode("pCE");
        country.setCountryName("NSDx2fvVodBE7E0f78voCSxtHlKBpFudFFcxOtjyJuBJN8PdmC");
        country.setCurrencySymbol("ug2k063iADdP3Vgvo4M98UpXXfOPGijS");
        country.setIsoNumeric(495);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLongitude(9);
        state.setStateCapitalLatitude(5);
        state.setStateFlag("og98T0MCY8yW4OcPtO29cstfyG3SxPryjIMOErHEQSmv0FSPdq");
        state.setStateDescription("DozsdypwVhvt0Ve03EFT8uTuchn4lE0VYd94PRVUXVrdSoF8oi");
        state.setStateCodeChar2("TqeQga0C6giPCULiRBmMzMxfcV3GU3gM");
        state.setStateName("i2XkBiSVKdW7btmLwUXkZ4T3G8yt6s1El7Gzch1puNvDgapmnr");
        state.setStateCode(2);
        state.setStateCodeChar3("dTzOAJeVSmT0XmJjCY0T46rW7idC07r9");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("jgyKRsRhqratXin4mPtPurV53N2PKaltZ4LCrlhog2Yb2imKqt");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("jERBNHnPVWT4pI7PZa99SLKqxP0ZpBjiWbRESBqpnaM8WzAq8a");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(6);
        city.setCityLongitude(10);
        city.setCityFlag("5Z7myXcu05brwYbYaMBA6e17EuVdgqVl5fWfka0WSZLcMe5kOe");
        city.setCityCodeChar2("vBYBOpB4TSX0i8jpvQO9yRlnbdG6ygOF");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityName("LMmiwKVsM6f55MoQUYcvPLk5nGiAPE3viM5iFYgNkio476SUL5");
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
            city.setCityDescription("0MnHBbVMTr41Ck1LNPfVnIioi9NUUdMBPVf5f3UZNoNtyOof1c");
            city.setCityLatitude(4);
            city.setCityLongitude(10);
            city.setCityFlag("xOG8uWf7ptGJeERNIPc58tyU9u8Fnuw7M7DpwoHZBaRPaoB4cm");
            city.setCityCodeChar2("vqnXmeP9srDFMM4aRaLn8SID6HhsFZHb");
            city.setVersionId(1);
            city.setCityCode(3);
            city.setCityName("TeXxW8lpzJIYvpF2qz0x0fVF2saHzEcMP7GB81m8jxCJZKAeTI");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
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
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "zUnJ43CfH63jAKW7WTvqfZjpEdYMC3CqsdnddqLaEUBE9cpoMJDSSBflPuIFadw5eu0NpiuRoGDTzznVWOImNhA3VeTOKFFQrjlgf41ld1LFLkYma7oOHBVgAuvE02oUXQ8hEJfu9Cg2qRZ9lOQrjA7CereG3iChH0qSAzEa952bOfgKVV2i1ZHJqAlviUSv6F4gX9zfdg0DSiOpgKDgs4WzT9WV9tvbURSlCC4ZVeENLFVBVzC610F6t3CtuA4j9"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "d9VqmCgXPqY3xsWTRthK01n22L6FZQV29"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 5));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "GTUK5RPjZGw2xiSlmk13ran9OF8uUt7C3aOloghrLwbj758DjRVHgjl8zdVYljBCciudD9EJ9f5vgJg3YV5CNWKBmeBDvZiXHPCLnqGZ6E4RtHTBfHFf45wbdrfRvRWvk"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "PH4f7z0a6FVnzJXI10B7l7IOT7qDGeAbfqhYfumD714jBaCvKs9NAI86VzlAqxXeoGONgZJ7iABgVpGKJhMwkUPv2hHIrtZsSY5TA88cgeCXlQliT7IB7zwBGx3iO0eod"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 13));
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
