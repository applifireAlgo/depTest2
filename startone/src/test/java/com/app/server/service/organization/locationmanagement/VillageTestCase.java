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
import com.app.server.repository.organization.locationmanagement.VillageRepository;
import com.app.shared.organization.locationmanagement.Village;
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
import com.app.shared.organization.locationmanagement.District;
import com.app.server.repository.organization.locationmanagement.DistrictRepository;
import com.app.shared.organization.locationmanagement.Region;
import com.app.server.repository.organization.locationmanagement.RegionRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Taluka;
import com.app.server.repository.organization.locationmanagement.TalukaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class VillageTestCase extends EntityTestCriteria {

    @Autowired
    private VillageRepository<Village> villageRepository;

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

    private Village createVillage(Boolean isSave) throws Exception {
        District district = new District();
        district.setDistrictDescription("TMHEN6fymJg4Q0mPH4HH6zPdj0XGq9uPsXgyMDtQfUjYrQSmbC");
        district.setCode2("1yPpyiHdQeC6JF4PzeVdkDO9A2V9smRD");
        Region region = new Region();
        region.setRegionCodeChar2("tcERBlZMTnaHnvrZHk8Bl1edLuAxfHfn");
        region.setRegionCode1(1);
        Country country = new Country();
        country.setCapitalLatitude(6);
        country.setCountryCode2("QLV");
        country.setCountryFlag("zQ77PIHVhVrtBPXEJGPOLoYqf0GfpupmZ6Ncug8jGtQ22nWmox");
        country.setCountryCode1("unz");
        country.setCapitalLongitude(3);
        country.setCurrencyName("17L7I0xaoyzO8ry6SWOp93w98lsxlhBbAez0W0FIMVfpbu81Wj");
        country.setCapital("LrWxVwUivvqsIXbYllkKN686XqyFfayr");
        country.setCurrencyCode("dRX");
        country.setCountryName("oT4ZcI8tg4PjvaXH9Vau4EwaXk0Cn2fLWwfppB5bJNS8Q6TqT4");
        country.setCurrencySymbol("akQVif9IlTLdHbCB9Wk5BG6PMqXYao7y");
        country.setIsoNumeric(74);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLongitude(4);
        state.setStateCapitalLatitude(2);
        state.setStateFlag("4xdwCBO2GyFTcYm66LW7LWtZoMi3uGKitDlTn8AIh4qBtEvQ6t");
        state.setStateDescription("StqK28h9fZ5uE0cyMqR8GFEULoEga2Ip9iVZ8wG9A6H1eoKdUK");
        state.setStateCodeChar2("51caFm5YMmfkqZCGYxzychGMVbCQWLMv");
        state.setStateName("kB6cQAY9XSw81WEJSK3dSzgDZ75hxCULKBov5xDjXEUNu9fDz3");
        state.setStateCode(1);
        state.setStateCodeChar3("E01EpUah6AvCRI9X482dzDHBAZ1kdq2t");
        state.setStateCapitalLongitude(9);
        state.setStateCapitalLatitude(3);
        state.setStateFlag("EgrDqtuxdWgm9lENcPoqzbVVWlHuXlrrrVqIKrxdJKZsYkdwqo");
        state.setStateDescription("MkFjfgFAL74oGBziCszaktpyXYLSOys3yTbHMtJHxs51qXHuCG");
        state.setStateCodeChar2("0kzeV5BM1EZ1iYuyQnDI0I9sG4RNWo5G");
        state.setStateName("PMBLY6ruHLygZq7nrJGU7SkwAfkIGPGhtgEiYDzeusffpjgEZg");
        state.setStateCode(2);
        state.setStateCodeChar3("lpEKjlRvenGyf4u7mHdX2WDfemLSKozs");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("bxCJcX4ZBpko8vDmMUNmMgffrockjcNjr7b8zI3f9uNN2TUcFt");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionCodeChar2("nc8nJUBHgFlWBgvNX9nk7Rq8JPw7OLhs");
        region.setRegionCode1(2);
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionFlag("OP4TC5Y7VdQpxXHqRoTLerv5Kpp0fY9Mz9sWKbpGdp6ld8V3hl");
        region.setRegionDescription("CsLRPFYdmNHMahUSMBZg3VEjqUlZ9AUon0kkzhGvh3ylOs7TkX");
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLongitude(6);
        region.setRegionLatitude(3);
        region.setRegionName("6qahGyiHylrG9dRdfM1B9w8u9a4vVuzjIC3FyOjFk21f4oLRuv");
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        district.setDistrictDescription("ANAeEB9XBzMxEC0BImVeNIHo5t12uRN3uX0gP3l5A7MEm1wWEI");
        district.setCode2("sjeBcKFv9EXnWpujLpvnwcuYGTlGEzE4");
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLatitude(9);
        district.setName("xij3PVjCLkLOiYd51IEjciTI6KdPHZE0kV2e4IhL9OQb3eNRLD");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLongitude(7);
        district.setDistrictFlag("vYw91EOkZW2bnKc0LOARnYcz654w0mRB2pZp05jpAPyIbMlIlc");
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaFlag("YMXjgN36lFAWVEweaeGaMhtjGMDffBYntFU6SR6VdSpG1Lh43Z");
        taluka.setTalukaLongitude(2);
        taluka.setTalukaCode("ClMwY91i3F3ecKBhDlPbJ2Bl0PJjCxOM");
        taluka.setTalukaLatitude(7);
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaName("A7o4Z5wAOIWXOgRNrag0KSOs8OkPdFTqDzxIFlPfGAYBQcobEY");
        taluka.setTalukaDescription("f1kuMuufupZVYtMNqc708XWWjE0bcCjT8rK4dS9JxreSh4urba");
        Taluka TalukaTest = new Taluka();
        if (isSave) {
            TalukaTest = talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        }
        Village village = new Village();
        village.setVillageName("BUL6olQOri7lIZ3HGinMFv1kV8cDo2urmaWP54PGx4WrZWNKPl");
        village.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageDescription("42PKr9NoOyMaaCPm97iS2mvA4xRf3KbQjADkbrD8fX5yAjBnPh");
        village.setVillageFlag("XcICGTnHd7aBaiKdXH6lrHHvXZ03dU2M15u7zbyiYZkJh3gzDi");
        village.setVillageCode("mDnK7GxhhzOosBI4eQFmECOoeDdczqVW");
        village.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setTalukaaId((java.lang.String) TalukaTest._getPrimarykey());
        village.setVillageLongtitude(1);
        village.setVillageLatitude("nDK3YSDNBZF");
        village.setEntityValidator(entityValidator);
        return village;
    }

    @Test
    public void test1Save() {
        try {
            Village village = createVillage(true);
            village.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            village.isValid();
            villageRepository.save(village);
            map.put("VillagePrimaryKey", village._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            Village village = villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
            village.setVillageName("T2LkEyTpp7GIHRD2WSTKyHvWYf8rm1nSktUcc0yMBQ7WfsrJ0P");
            village.setVillageDescription("xLf11ic0gwNNwaL5YCMmwkmBbdVRG3Gzx1LVBbmaWULQNBxAgQ");
            village.setVillageFlag("YiItTXXpzYfbJ5z5IbIUfQ33fyPaewcxbm9z55XSseHJMP2NJR");
            village.setVillageCode("1fyxkOBd6qGeclj2bM35MN0ZnVTAwrUr");
            village.setVersionId(1);
            village.setVillageLongtitude(9);
            village.setVillageLatitude("JWVeFOBTvXz");
            village.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            villageRepository.update(village);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Village> listofdistrictId = villageRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
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
            java.util.List<Village> listofcountryId = villageRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Village> listofregionId = villageRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Village> listofstateId = villageRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytalukaaId() {
        try {
            java.util.List<Village> listoftalukaaId = villageRepository.findByTalukaaId((java.lang.String) map.get("TalukaPrimaryKey"));
            if (listoftalukaaId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.delete((java.lang.String) map.get("VillagePrimaryKey")); /* Deleting refrenced data */
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateVillage(EntityTestCriteria contraints, Village village) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            village.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            village.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            village.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            villageRepository.save(village);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "villageName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "villageName", "Vqs261pwigj4mkJrgVe1jzqomXtdoj7r80KVUAMEIfouduP72OnJYT0jKmpVKt9MqkXeVLi0M3iwhcpjoiGW3OOvBtKtNUegnON5e5s3ousMwnUi4Fjq8VluUOzlhzsHqFIgJoCTlrNMBKplxiMIWHSMCa5S4i41HHgffDP7gI93Aoy6pMma9x3em6ASXkPkNURFILJI4ZwYzsR8IlaI7He16yYYku3CpRzPmnMOYeQVSi3i9sMk1kWihkpaawQdj"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "villageDescription", "ksz8M4kLLcKAHLAhDC1VDSenJe0vwlZoRb18hpNGHlB3gStoVR5oJQTBpg1ydXK4YeHVkB7ASd6SCGyIIRkGLHlXAHFLRKjVTTJ1qFReEmqia9FG8ytTc8fWYmEw5eYOGDUMoIqWHgfBsszOW2OxQHvprLSei47sL6K8jrcNhm8mZEhegTLQeiDJxM9hCLVeVYTNUyatHHTCGj4b2B9qf4bdZgOkW2R5o3KJLpBJljOFm7FFI6mxC6emX3MlEl8uK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "villageFlag", "hVhvUyfOg4DUlINUMma8qdtLYmoO66kBYHHNA94Jxn8P0bjatcNfAJP1MRJNOsIFZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "villageCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "villageCode", "Gw6WBqXvnGRdxUutqNCnc9XGiC4sxh3SY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "villageLatitude", "oTTPiNUcG5cN"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "villageLongtitude", 14));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Village village = createVillage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = village.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 2:
                        village.setVillageName(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 3:
                        village.setVillageDescription(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 4:
                        village.setVillageFlag(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 6:
                        village.setVillageCode(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 7:
                        village.setVillageLatitude(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 8:
                        village.setVillageLongtitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateVillage(contraints, village);
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
