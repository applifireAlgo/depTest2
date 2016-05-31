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
import com.app.server.repository.organization.locationmanagement.RegionRepository;
import com.app.shared.organization.locationmanagement.Region;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RegionTestCase extends EntityTestCriteria {

    @Autowired
    private RegionRepository<Region> regionRepository;

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

    private Region createRegion(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryCode2("8cb");
        country.setCountryFlag("JfDK8xrmUYeDzamvYRpWcP9yDbxrn87USqk71yFXcRQOe1IpTT");
        country.setCountryCode1("pUs");
        country.setCapitalLongitude(3);
        country.setCurrencyName("9yKLWdBsirRuZUHrS7CTjyljn9hTMcjoPAJwvu7Ji1LCmtF5gL");
        country.setCapital("bjC40tMMUA6mk6rn7q4SeKpxf2jCqCt5");
        country.setCurrencyCode("Gu7");
        country.setCountryName("6oXoCr7xujnDIXA2wagQ6uR2lqzPQUYih50xLUQkgKzZgljtAa");
        country.setCurrencySymbol("a7LdPPkYXrL1Vp7WJvSurIi52fv6rbUw");
        country.setIsoNumeric(440);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(3);
        state.setStateCapitalLatitude(3);
        state.setStateFlag("oMoRbpMSD3md2CoxwJBgt2wLPNZa4ET5cgltEPZqYLhVh59Pj5");
        state.setStateDescription("xSfpxdzo2reqyTCgdWoKkeDkn1VdisoiFZcW6hWYtkD4kzyOcm");
        state.setStateCodeChar2("ChXDINQ7f2uIQ85T9THkr52fPnvUPQrQ");
        state.setStateName("F29wsOmtnDGXVMU0ikUt4jQUxdBE9KrYyH8cBlYjJ1ACntDUOE");
        state.setStateCode(1);
        state.setStateCodeChar3("WzxF4zl5xMmx1w8SM3ZFae4EUqipknrU");
        state.setStateCapitalLongitude(11);
        state.setStateCapitalLatitude(11);
        state.setStateFlag("5jSpr0A5PW8posBJ5SJrtXetwP6ONzI4T6wpBMdglHRqNgauVI");
        state.setStateDescription("73By8EVbnWYmYoUvtWBoW1BnlQ9YkIGcCvmagELlBE3qTK29wD");
        state.setStateCodeChar2("06YmdqF4IzEVKKEVT79TsaNbhdac7yYO");
        state.setStateName("aB5anP7zKbRIpU9qC8w1XVfTsHU48fCFL925ttpb9jg0XZd95N");
        state.setStateCode(2);
        state.setStateCodeChar3("h4FgHGVx892QtKfS1tzFPdZ9gx0Cv2GV");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("BrziZ5q7VtHsDU0UoK5CtL1hdISKBTcw9i2FbmlY4p0R32xIwk");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Region region = new Region();
        region.setRegionCodeChar2("i5MsCF8iXkgGw89IS14jVcFnYKSPMWxd");
        region.setRegionCode1(3);
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionFlag("314jyTMNA5hBSP2DQ12N4hrSmh8KbZNPYykRGELZLPjO17vRAU");
        region.setRegionDescription("dEQvJyso4AQ0NunS8MoarjRBRgOroTMbOhxYHdSDwIm2KnBN0T");
        region.setStateId((java.lang.String) StateTest._getPrimarykey());
        region.setRegionLongitude(5);
        region.setRegionLatitude(5);
        region.setRegionName("HHoRu4U2SCitxoIpyowBn83u1Sd4rGr35vswECkp4eeCnzBKhz");
        region.setEntityValidator(entityValidator);
        return region;
    }

    @Test
    public void test1Save() {
        try {
            Region region = createRegion(true);
            region.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            region.isValid();
            regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            Region region = regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
            region.setRegionCodeChar2("AJhEBYfLt6QV7S1MfmXQpvw6ruHJSC6X");
            region.setVersionId(1);
            region.setRegionCode1(3);
            region.setRegionFlag("5vuyF2rGrnz9FcpYfGPfWZzBysFweQopBnFMCsvmo0ZcG0J62b");
            region.setRegionDescription("IZEsugpZGmf3QiaVjs3TQSeDZcQYkzsthoMy5KqhQ3dBt15wb8");
            region.setRegionLongitude(11);
            region.setRegionLatitude(1);
            region.setRegionName("Sotr9eVIHYoKGIk8Npf57If7OKXr0GM2sOfu9LJonyF3ssDn4E");
            region.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            regionRepository.update(region);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Region> listofcountryId = regionRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Region> listofstateId = regionRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRegion(EntityTestCriteria contraints, Region region) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            region.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            region.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            region.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            regionRepository.save(region);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "regionName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "regionName", "xiIXVCgN3hlczsQUbcopTNPxsT6Q1VMEiTtdWQUx6ieHc2T4dotLsXOWQxkskuT11mqS1tUhAVtXjawYVkxDUwgorIiyESkOAoIeQ2RPYrP40W42GQSTrStG8GnKk7QCdfb4IheX7dW4Ko3Jlf5YEgWToxNmE4MfbpXmVziMx5GcSAWXNGpTZgry84m8UOnoNXqUPfoaVw7INi2V5cIyIPaaVI0D9rnT8QwZ1tceEMjWR3AUFTf1mINppKHNbczYv"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "regionCode1", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "regionCode1", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "regionCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "regionCodeChar2", "tmLHApBIMaQwTR2cEzad9ziIHA6k7gtg3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "regionDescription", "G49V2XkqTUUlVD2KGUjeYwnVBLnTdEoAQC16RP1hkSqkhKIEYW93R9koGqget7WeMDtBlDnEZDgWOslC3yZOpbvNZvjvSZkmvzeu8u5WwvmctRDJLylrHBCh6qxk1h3g5TTHzRkEbCpnHuJbFYhyxUcLo5KTNJsE51uQ6YGNr91daqQd7XSVuTWxRIy6DRrFGzbjjT4aG0GovprUnsrUiAPgpcDB6bYVtyKTbfhEuPzC7Fqyb1ryJjTE21WQE74tF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "regionFlag", "UV38Vuu5vYjB5SzUlscTrbCoNPTUPDjq9d1ZHi7HhTzAqae88jQAVwktb6ujuh7J1fWOsnCTP1a8YsBcUJtRbI5rLIjoMYAu8QTOnRCvdm5IQPWrqAYYVe0ZrXMkUienW"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "regionLatitude", 18));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "regionLongitude", 15));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Region region = createRegion(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = region.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 2:
                        region.setRegionName(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 4:
                        region.setRegionCode1(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 6:
                        region.setRegionCodeChar2(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 7:
                        region.setRegionDescription(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 8:
                        region.setRegionFlag(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 9:
                        region.setRegionLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 10:
                        region.setRegionLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
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
