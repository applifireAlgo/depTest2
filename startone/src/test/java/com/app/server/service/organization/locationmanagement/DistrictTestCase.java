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
import com.app.server.repository.organization.locationmanagement.DistrictRepository;
import com.app.shared.organization.locationmanagement.District;
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
import com.app.shared.organization.locationmanagement.Region;
import com.app.server.repository.organization.locationmanagement.RegionRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class DistrictTestCase extends EntityTestCriteria {

    @Autowired
    private DistrictRepository<District> districtRepository;

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

    private District createDistrict(Boolean isSave) throws Exception {
        Region region = new Region();
        region.setRegionCodeChar2("h3QaQPyDJYkpGlXWBTYROEBjD38dx404");
        region.setRegionCode1(1);
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryCode2("pPQ");
        country.setCountryFlag("KDHwR5ovxeOBLo2elycDtz9MdXlWgWepZa7QjPUknbTZQBdeT1");
        country.setCountryCode1("9ZW");
        country.setCapitalLongitude(5);
        country.setCurrencyName("Wzea9CKMKa6sww7hhqCQ8HYsoicsmg3lM7BKkZ2tPX1p31whgJ");
        country.setCapital("TVBeSJXP63j8cCLUZgPvLCcH87hxNHjB");
        country.setCurrencyCode("1Ye");
        country.setCountryName("3rahyustrrpxqjpsrZi9llLHyPKC0EdNDOfNI0ulH6nFT5k3Rt");
        country.setCurrencySymbol("36Jocqpvcr8tD9tcCq7eYvV48GzHyiST");
        country.setIsoNumeric(229);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(10);
        state.setStateCapitalLatitude(5);
        state.setStateFlag("COEkN0suyDMj7oL5q4jBBJa4GT27OCIc1MndmRYJ5xev9L4j2H");
        state.setStateDescription("pDbwaCb88KyIL4imC0zr87vv44Y3YBDVVgFWvykcl4HxI3H68w");
        state.setStateCodeChar2("yqC3pGg1AQzS6tfNC2UYFLYYDGZOVF00");
        state.setStateName("dj1v28RLkEGkUNvJhm9Y3EFNRCTN6sXspwy8GYawMobToCBHiQ");
        state.setStateCode(2);
        state.setStateCodeChar3("WBrOVOG9G3sT1fRTuQAlkRykOrun9q4B");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(11);
        state.setStateFlag("W3OpbcSrSaxhYEtOveQXFV8cswBg9zHcz9lpSER1waBV3a2b1M");
        state.setStateDescription("UfFpQBpoitbmlZ5oQZmTUFCnFT1aNME6wmqAxZjVMf7uyYYeaC");
        state.setStateCodeChar2("dzTVgrLDJxgLWAo4Fn1ktpG9CPxGzPiI");
        state.setStateName("pjZ2xwMkc9yQ2i3NgGTMH0anGuY1t71v7RUlvuZfgyqjZyDQbb");
        state.setStateCode(1);
        state.setStateCodeChar3("Ed5WdklLAfnmqkjmWEIM0uiaAwvmbxFW");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("zPDHAdKxMlzaq0DoPproEv5T0DiKuP7JLnKFGWw2dMOjJJGKqf");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionCodeChar2("7iXGEGTxRxsTHq1kY7kZiwuKxXa7nnbh");
        region.setRegionCode1(3);
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionFlag("1xHnFstqffTXG1LYDxDNpdVvecnDpZt2hFU3cBX1zCG3UnADkH");
        region.setRegionDescription("9W2sbtrZhr82awMzmxwvwgeU2qrTdVgsxL4VprCS9BC2ssUBVV");
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLongitude(9);
        region.setRegionLatitude(3);
        region.setRegionName("g5pTRGOn6ApVGjYZgL0VHlWgFTCYtQcshl7pBeeGdeD3ZTn71z");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        District district = new District();
        district.setDistrictDescription("IZXADxJos5bRfjNy8iRdNoaiY4S8EWg5E5pYkyzTQzICNrt5Gz");
        district.setCode2("USl5Kr0CcebDE6BmpocYua9DOCogPsfm");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLatitude(2);
        district.setName("4x6kJZiwIc16vMsw0i2etAaCq0EikQue7nmoR3JJ7pfrj2qjmW");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setStateId((java.lang.String) StateTest._getPrimarykey());
        district.setDistrictLongitude(1);
        district.setDistrictFlag("O5PWvO3QlGvSr5kNgWiREdsm5JcXyGiPOAYdsknyJc7ZaocE5f");
        district.setEntityValidator(entityValidator);
        return district;
    }

    @Test
    public void test1Save() {
        try {
            District district = createDistrict(true);
            district.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            district.isValid();
            districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            District district = districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
            district.setDistrictDescription("YQj0LNUCiLDoKY1HsrKDZBnsqISu2bbma0BFVaWuuYJe3Attwa");
            district.setCode2("bXuJ6LfIJQgeGUCL9EKPIZMtLYMznz44");
            district.setDistrictLatitude(6);
            district.setName("AHikr7dVVqlf7qDENaQAN60MAymPO4qkbi4RwJfXBEDnUgoiYz");
            district.setDistrictLongitude(4);
            district.setVersionId(1);
            district.setDistrictFlag("WbHzkK2m4yQ6eLqCjTuDqL1UcrnlKHDsyllaQ03gCOo3q9DkWi");
            district.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            districtRepository.update(district);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<District> listofregionId = districtRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<District> listofcountryId = districtRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            java.util.List<District> listofstateId = districtRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDistrict(EntityTestCriteria contraints, District district) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            district.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            district.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            district.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            districtRepository.save(district);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "Name", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "name", "SLoynM6Zo1U95f11qGHZoElNhu5WwtC5sy2ivIbymZI4qcNlPUbVoU4eNhEDiZt5D1IJQAQKqsbVUz9BgPegIEaod6H8Giiaw7meu6JvFdH1duKzCtcoby4Ocf5xEgKol5cBNqRh3ko2eZejsDFB2TKqreAwC5qAFptAQLxULAw8u4iPCO6KMqHeY2ppixzjjBDinmrIAsFQaXtofrnTVPmOSPwmW45NfgjC056E0LCQoEIke2cJL7yzmPkS9TnbW"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "code2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "code2", "rauWho5gUxEDPUG59DSJU2eiDCLBPKpzh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "districtDescription", "xurk70FASAmVBlhfys9ckK75qlDORisiZ2JWGdrsfYBAyn7dlYiyJlYOUaRfJjCnUDdacGn5m0dBxLh65pcwvxbS3LvaZmu0p2FyXkmye2tm0EsLIxHnmIl237Lmc64Aa"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "districtFlag", "TVvB2oWRsiMVENs0INRo0Lem2jzA5LFtjQvYao81sspQLBPyz2mnXv9s1Pikl6OIcUGKklhyAPWqkzB14QWwyycDorZVlyGqkteLZF2ASOOxsX79vh1NP5O1lIQH6dQFK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "districtLatitude", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "districtLongitude", 20));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                District district = createDistrict(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = district.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 2:
                        district.setName(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 4:
                        district.setCode2(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 5:
                        district.setDistrictDescription(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 6:
                        district.setDistrictFlag(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 7:
                        district.setDistrictLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 8:
                        district.setDistrictLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
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
