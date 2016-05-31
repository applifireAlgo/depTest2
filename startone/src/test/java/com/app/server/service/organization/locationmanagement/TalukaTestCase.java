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
import com.app.server.repository.organization.locationmanagement.TalukaRepository;
import com.app.shared.organization.locationmanagement.Taluka;
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
import com.app.shared.organization.locationmanagement.District;
import com.app.server.repository.organization.locationmanagement.DistrictRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TalukaTestCase extends EntityTestCriteria {

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

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

    private Taluka createTaluka(Boolean isSave) throws Exception {
        Region region = new Region();
        region.setRegionCodeChar2("ES5FqAIKTGD0ywrU5HagOnOfWFvG5mzo");
        region.setRegionCode1(2);
        Country country = new Country();
        country.setCapitalLatitude(11);
        country.setCountryCode2("AWD");
        country.setCountryFlag("DIujw7u6bfwrBhWhJ50zchFi74QZ544gw1owxR4ogmPT25NZ1m");
        country.setCountryCode1("KiY");
        country.setCapitalLongitude(8);
        country.setCurrencyName("T5bqWvA08IhvXBRs5AnCic9GtnEa1nF1qVdWyUD1KQlMZa1nde");
        country.setCapital("yWXy4f4QUz54KLbwdsEn3gmQvPDWSoTM");
        country.setCurrencyCode("eho");
        country.setCountryName("2wq1MEj9c1pjJxiS5KTsKLIuvAo5qdryNVMEp5TDQ2VnuWkjaU");
        country.setCurrencySymbol("7Xit1DEigj8dcYoDn9j2FGJXnVCFTChF");
        country.setIsoNumeric(359);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(10);
        state.setStateCapitalLatitude(11);
        state.setStateFlag("kYeZhwF2tJzFuWzfFWCb7WX5a4ZvaSstYR3Mau2IyTHi8bDOyS");
        state.setStateDescription("dlT9NjQLwOzELDf48ofz0Kqbn9w8zVDFr3iOvocOYZu8l0cv0A");
        state.setStateCodeChar2("qGrgvrdfyztomwi7y1cM9a66mvH7qbE7");
        state.setStateName("SKhzakm5joimBvR5IA64qdHsXpLbxdmHfosU05hxjJGxTPZpzh");
        state.setStateCode(2);
        state.setStateCodeChar3("pRD2aX9W21dRugTKwroKP2SYmWLHNjBz");
        state.setStateCapitalLongitude(2);
        state.setStateCapitalLatitude(7);
        state.setStateFlag("hC56z5nAYXfaPfL2h3s0e3lF8qAgP2Y3MomQTg1xd6W9KyuSRD");
        state.setStateDescription("GoSg7X4NSaZcPmoge4YJbE3LWPi6iAKa1lI84dcsTG88oaJAXO");
        state.setStateCodeChar2("x2Qd3b5bFojlkV2lNqmXyHgzfvAFpVrA");
        state.setStateName("kp72iha1wz8O1VzbCqeJUIkBb8ltdM6jymYaYleDMkql5LOCyA");
        state.setStateCode(1);
        state.setStateCodeChar3("84u0V9jVjhPCrFpb3Fv0eWAVxuK7a2hu");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("GHsQ38VZ9EueGq0jppS9sdOhXBbceLzK1Esc521OLdaVYLfBi9");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionCodeChar2("PekVt0xIeyn1K9rfb7ZO6IPh6rlO6YEt");
        region.setRegionCode1(3);
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionFlag("A8yKS805GsVPW6RQs4mYDHEjuZOWSuEAXComqE8XGlRRX7EjHT");
        region.setRegionDescription("SPLSEednEQ13OLBVctaSYRGvdzXmkyU7qCBoLSIe3rwo2JKLyD");
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLongitude(8);
        region.setRegionLatitude(3);
        region.setRegionName("YJwTLWvuILd2bhyg2ZSACbUc2TwHtvNxt9fLtoJUg0XZS9dSdc");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        District district = new District();
        district.setDistrictDescription("vT5P54v0aupY7VhK7hK9LF3fFoVVejd9zrMdQ7DAPWwM47SFTe");
        district.setCode2("253AoSNd7UdKKPIL1NDQDzHi1yEN7WBq");
        district.setDistrictDescription("yY1d8oSI3jEe5b6Pw2vUL9jigeWgnwrHiIfrqUfkOASllp2nOl");
        district.setCode2("1Vr1L0u2vZMdVV475T0hyCUWZcJWagOh");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLatitude(9);
        district.setName("Qx24z74t1FF4cblNXbzjemOZW0vRuWssnRvIYwoNQVTsc5Hxfo");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLongitude(4);
        district.setDistrictFlag("Z1tFOvT4Tmnb2VAORAjPCugfyhJTkYkYigK7wwKHTaFhLsV4Dp");
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaFlag("g5BLT7StwxXoitsYMBRN9B9kcgbAaXRqsH9AesjAdcMMf24mTx");
        taluka.setTalukaLongitude(1);
        taluka.setTalukaCode("aTeBqfLhmCipjcVbUvPbmwgapibqpsiQ");
        taluka.setTalukaLatitude(11);
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey());
        taluka.setTalukaName("5PvrL00p1FJnXr8mJOxLHj3CbioicLphFIZcHlYCggPKUJZ7Pk");
        taluka.setTalukaDescription("YuEtDCY577ugbJNVydKyFidUgTCMDahtxtTtG95oitYD3gbepj");
        taluka.setEntityValidator(entityValidator);
        return taluka;
    }

    @Test
    public void test1Save() {
        try {
            Taluka taluka = createTaluka(true);
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            taluka.isValid();
            talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
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

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            Taluka taluka = talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
            taluka.setTalukaFlag("077DUiciQtA90kqT711ycFnhegef083Mlzfp9Lu4M8kM6zC0ex");
            taluka.setTalukaLongitude(11);
            taluka.setTalukaCode("8DRoVDViL3UgmSIi0gfCerDwRTvY5EX3");
            taluka.setVersionId(1);
            taluka.setTalukaLatitude(6);
            taluka.setTalukaName("vhZ1nP1nTWvuoj4DMIuattP6a6UPJGeMfJTqZpQaEYgUiuFNPA");
            taluka.setTalukaDescription("jxej0LBl4GxixqJ3ACbqkBmwtNgJoo64Yoa9QG1I3WYQvKO4zj");
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            talukaRepository.update(taluka);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Taluka> listofregionId = talukaRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Taluka> listofdistrictId = talukaRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
            if (listofdistrictId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Taluka> listofcountryId = talukaRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            java.util.List<Taluka> listofstateId = talukaRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTaluka(EntityTestCriteria contraints, Taluka taluka) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            taluka.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            talukaRepository.save(taluka);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "talukaName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "talukaName", "LslTjNVpgOvZQWA9WHjtvftDYHCTcLlf2nsHFe3GWYTBN7lKnRBBjr1J1p9Tp9hNTEN5oqm8K16hDmWbuoCFtaUoquN54ytWspLK9J4Hc0ByjzCzQnaW2Ip670G5aM9ukoFsAKrl2jGHe5MgEJXPH6cb2ci7hTgWfAFf62GretnnVPfomiJ4A7A26ST5VK6a19wNCTmbqDoY2Ie3dW4ukP3ZUixjlVC2nluyEJ0TywJVbNiFKrWiyLeA8niwC60CJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "talukaCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "talukaCode", "7fTBb9jGeUTvOSvMoHpI0PqidwIJexjmr"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "talukaDescription", "hka1PrZHRmhNH6SSFuobRYP5H2WWrlHnSKfReWj46bdRaUzcvfJuhQhvDXnT7AyKZGYjJmSpMoVpiYiURJs0MtbJlAf88K3yS3Q5NpnMVgZevXcsehhmoHOL8YT7PtCpQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "talukaFlag", "XtNZvBsqBbnyV6ataFs74TRCLN4B26KkBCDveEXnz1kdRMymJXNihbWCGNwhX3f2lexIwybiB4756rnsavwm8nJOmFVocNUKrzT9ldumRDZoKDe8utW1s0QX29HXZyP0h"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "talukaLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "talukaLongitude", 22));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Taluka taluka = createTaluka(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = taluka.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 2:
                        taluka.setTalukaName(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 4:
                        taluka.setTalukaCode(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 5:
                        taluka.setTalukaDescription(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 6:
                        taluka.setTalukaFlag(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 7:
                        taluka.setTalukaLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 8:
                        taluka.setTalukaLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
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
