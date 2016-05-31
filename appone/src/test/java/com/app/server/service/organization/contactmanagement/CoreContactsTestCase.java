package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguageDescription("frwJ5QGvhkN1SFDeDk2rsC1p4wG8kyNgOELrFuptcmhiK4jp9A");
        language.setLanguageIcon("UiqZHkuZBCrdEK0JbaVCa696hQwI2g6e7Xli5nIetDOPPGe5rh");
        language.setAlpha4("hE0n");
        language.setLanguageType("RbSaeY5EtbjsxDyBaOmcVUPCpEUVhTa2");
        language.setLanguage("pN8nEJVEeOkfkZ2OMeg6B1uK8nyfds78lyXcylG8t0PlBvSTCS");
        language.setAlpha2("HL");
        language.setAlpha3("q6c");
        language.setAlpha4parentid(5);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("E7m8PbPGoWHowFJp8tI6SMp4JoNjPEjyHqKvAazXyHlxaUDn24");
        timezone.setTimeZoneLabel("dv2F1qAokqO7TWws1nQbjVWxErNL4jsuQO78VwvKGV9iurKbc8");
        timezone.setCountry("d3VGtUw3QfkiwnsjJXkvaKPSBbtLxBsuYk3GEoG5sLeC78ZuOV");
        timezone.setUtcdifference(7);
        timezone.setCities("p1s7ibWENYfgEzfSHRRIbBnwgAWmKLxbtZYFXHkUAFY73irVDf");
        Gender gender = new Gender();
        gender.setGender("O6rvKW2EyUuOegkr8wRnuj1BdQDzsW68B9BcQH1oOkbr6QmCC4");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("ZrNpjV2CjD5MhiT2byAHAvz3RkEi3kOfp1mQMoUkNmK67ZPibq");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(51);
        corecontacts.setNativeLastName("8x86oearvaBXWPh94YTW2HM4JRsLR8uJEwN6bRo4665I1RHPCr");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464686010648l));
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464686010648l));
        corecontacts.setNativeTitle("f4gFZ0RHWK5OFXM0pjAp2M5d6e3fd4bnm1uwtNxFm1N2YKx9eF");
        corecontacts.setEmailId("43sJGSJgKPpuQuLxqlWkkI9jJ6BHR62UOHSqb30yW8kyBBKCa9");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("fPPn5VveFG7nhS74kai4zbDQQw8x9GOULiEY5NDbAc081li5q4");
        corecontacts.setFirstName("WbZbW36AoX4PnnJYo5xUnAkkDjfZzCxanqKTj2mUOZuJ06p0uK");
        corecontacts.setLastName("EyumkRt6LvnGqBmSFuKEHBf7MVTMXEUhITu5l7Gsaim8wtw0CR");
        corecontacts.setNativeMiddleName("r2699Uej77Ik8s35A8QMipUpofxU88MevzWeZigbVGAcnq7O7A");
        corecontacts.setPhoneNumber("70wHWuyTefMQJ6DZ2KFH");
        corecontacts.setNativeFirstName("DdO6xz48A4fxz1Wz3OhBpUtOfZeL7MnuR9K5e7BVoOlWUbyJBy");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("rm7hBcZ9QWrWgdfqJcsigbnSXwXNyrOfYI4qebTg7iItCiNsqn");
        communicationgroup.setCommGroupName("cIxIhNG2kp2HC01M11hssk3FEa28Vyw7VFHkh16QMZR5xwFYSU");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("xlNBC8rQ9ooFb3gzTxj76vgYDs2hay0HL67CGxLdA8laM70Nuu");
        communicationtype.setCommTypeName("x07PO9LDuU8WvK80rnBKF6ohRFTogkFkrmOGrkxWGpIVKrkDoE");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("UFWIGSvNen");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLatitude("idozVT3HHM4nBKC4CS6iTCVKXvblGSQ9pAC04lN4h3cfq9tCCu");
        address.setAddress3("o9GKW5kAwHAQipXRPDfk7nqLplTh2dPTsDJU3KVaC1MI7pUC9E");
        address.setLongitude("n6OypEfy7LHDUZ5D8FK9tLDbV7B2JeEyx5RzJTM5cMU7Ufmps4");
        address.setAddress1("KsdcsvXhqJybeCLqpaFAZtzcxOcXQRIxYFq9gGXJHUsThnL7em");
        address.setAddress2("F3iNSk8Iv7BMAVOQkIyni2fobVI1p7OWCc1V1GGFHCR6acrDt7");
        State state = new State();
        state.setStateCodeChar2("904OBS9WmijGNsgv6WpGxQnbiu1qhcrz");
        state.setStateFlag("k2JsrsqyrjKr3Di07ajTTCQcF3ussTbp0IGvUuDsEWeAZlzUvE");
        state.setStateName("YcHk7LXuM89rRxr5ODYqGWYwRiMWHqsJijYJMi8rCtrOy0Dwwh");
        state.setStateCode(2);
        state.setStateCapital("4jnWZE0Oqj6k2sFgJfH5XGRZ9LuEJ2eAQY7XkpMCgk9Sh7bzXm");
        state.setStateCodeChar3("pY2pxCmjjHhyFpiNEN7lTh0v3yC3VCHq");
        Country country = new Country();
        country.setCapital("wa6TbXQQFa9VGpCVIbl7Mp9eyTVeLA1H");
        country.setCountryName("BAPDB6j6JdGM8DlKivfdjVMkhLJKS7j3qMFPBVRsDp7hLsgkEc");
        country.setCurrencyName("emchZECdSkzjgSoaUdDVm1yAcOZgOZ9Utq7wTBditSk79eeiWH");
        country.setCountryCode2("CDG");
        country.setCapitalLatitude(6);
        country.setCountryFlag("MhebvFA0aqfHh7RNgzyro92t5ZyO9Pa5OF9zjScKga6KUFIp4q");
        country.setCurrencySymbol("LsZtRJc5agRUBh9NBQ4oEOk41oZryKE1");
        country.setIsoNumeric(775);
        country.setCurrencyCode("O81");
        country.setCapitalLongitude(6);
        country.setCountryCode1("wz8");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar2("SegYkI5x8akO6hzRO6UtXkITKnDr8bcx");
        state.setStateFlag("dBnqL6fQx3ybvmwnkOfSpmBcSkCWhijhZw1LrkaSQkdRSAaR3v");
        state.setStateName("Gq2TwFOMPb4QosLJ44MkFg4MX4AsCST6Vebwwvw7DK3PsH6ics");
        state.setStateCode(1);
        state.setStateCapital("cneKmzpq8aZlWaKSMOugrNwIcTXmB4xYIcWuqntDiCGKwRrI19");
        state.setStateCodeChar3("r2h6qqYBy9sp58b2RHsjagW86P0r04NC");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(8);
        state.setStateDescription("SdnCkUKnd8p26KLO6RyiMEUgYuZlHR4t40JveMqUnx3mRIAgeq");
        state.setStateCapitalLongitude(9);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("bm5d0zoOyURgllELIYJmPkBxY4K6vw3cAJuwF3qbH2FAtacKzj");
        city.setCityLongitude(4);
        city.setCityLatitude(8);
        city.setCityName("OcxJBzLYiAcr921qe5zICNMxT1b4p0TwOMocTd3cAli2YXNkiY");
        city.setCityFlag("dQG7yFxvktBFKIBIYEo7Yq0zzXOdIiAPvIgoyNKkkKGMPpaomQ");
        city.setCityDescription("ZaT6sbzWPJ2dc3ZjHFe632IiiKOrgEa2PS4pavlrB5RwEBwPvP");
        city.setCityLongitude(11);
        city.setCityLatitude(3);
        city.setCityName("tkmDfMttgVszxwOc1hVf7MSSl8kFygE5B7SZkNVJpec8g9Xzai");
        city.setCityFlag("MzELz8XSTOmg1aPzAyYmyVQsEFbs7NOWCKmX7crCP3m3lmdFLE");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("sqAzQp2l3Vcr18z8Mb9XrBCaVHhFcarY");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("lgYwtZ21d8VpESLHNkYTvcNqkDbGp2Zk3Xv6N8dZ2wvNGIZgGh");
        addresstype.setAddressTypeDesc("hc87MvUb0Zmi9hgsi8W72h68V07CnPKlzULNh4yROqGiITR8nG");
        addresstype.setAddressType("5NIHU76trBER7dl7U8FPzAEf4s4jshPZnby7T7fLi00IdZ0gcl");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLatitude("mhobGwiwg6frhfWTMTSvAp8oNhUdEHuM6OaFPGCq86bRdrkZ72");
        address.setAddress3("jbmmdQN0Q8dFZ64cjw3rqQzARKGgX2mY4RYZafdBBBohgVM3pP");
        address.setLongitude("AiNy0tu4LdESLEZawxdCImkHygNJXnqvIZS0lsT1SUlzbu2CWf");
        address.setAddress1("UsIQReKYgCn7aZY3M9Qx0tMXznN6hGMhurFzvN828uFHXKNReN");
        address.setAddress2("c8GygsXWUW411ARYIFdcRtZY9b8onhbocJKJiz4uZydwi7R5w1");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("1Kph9e");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddressLabel("S8kw6YfHYZp");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setAge(31);
            corecontacts.setNativeLastName("dQYcJ6GOzrQLCt2JURqEACGRKNY5XcBWQoHjFBqRJtINoTmNqu");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464686013679l));
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464686013769l));
            corecontacts.setNativeTitle("67WHC7mEcTrL4wail7WCesE428hOkzjeuNszOX0yPruhG87PSO");
            corecontacts.setEmailId("e71LOURTP9cVzn4u9cKDmCzUXnpFhzGdjt5HvYl2PiVnmZJ14H");
            corecontacts.setMiddleName("LryR4lJiVXC0jXGE8pNBWSXnKUjAEkUQiRqpzDlkshQWRMDvis");
            corecontacts.setFirstName("kXp0wunBsuD7wVlvS57Wg3IUPiw1niiyIz9Dk9Wr26Xw9u8oUM");
            corecontacts.setLastName("MNt2Qk2HZTI36BdRL0Kp6N3Y6myX21KkqPHxoJtX1vyvddfTHf");
            corecontacts.setNativeMiddleName("vcDSLHqcOTliMUey94gmpqkEO2WHUWpG6dTPVITNkE5YMcnrkV");
            corecontacts.setPhoneNumber("8pUr6QYic7TVCKT7dP1O");
            corecontacts.setNativeFirstName("n3E63kQeRBYIDYdmI90Rgt9CdYM5Ko8ye9HFVbxpBxC1eRSJ1U");
            corecontacts.setVersionId(1);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "d5fwGimfWFAoxyRWdl3vzmMtQAP6aCAU6IJmjW3s8OVgKjtADKrgh9wjlUhoIyr8BIEpetq7VcUQUCl0HCrwHwx6xRIj4Fo5yOFaAirbCAhA4fhFoYVis7myLVYFokwnYyBHlwunzJ7hmBzm94M24Zw3yVUNyOEOpIskw5ELsbs16WRNf68MIvZBXAHsaywvrbW0Pc6HY72wyTMSo3Dlnrk196Z8CY5Y3MXj6JmWHAGXiuID698ZTL0yvUWiYBFt1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Hcj1MGUMEfs5R51aW74WEDvKAX1QdXCe3KMWhq3X1LXKR30NsqJmwNCRxo8sWq8AWbV74cHNuAPlgbOHBDwfHfs15V919fyoZFqaw0AmMDyjXnae3EbBYm2HHfUxrrXkc9lzd7iTvgUbmzBlAvrSn6sU8ptLUqflePNgz2llI6I9wKjjTco77UjfSZBzV47A2D6WOlv9XFeY2hTJIteBEjAdBLeIxlQ2icaXHmaAfJS843iN5wDMNb1G0ZgU92qEA"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "N2OuUG7Wh8ZzZ9mY5u97gNNOsSvK3Kb2Cw9ye31bD70A07ax2hcSnNWlf86tvlgC9CV0lSbZkvVWzO8xcAC73GPiu6XeD3qXtVgr7BIYVmFyJ3akHqt2c6TKvongPTsPlVb3CILyabOqOCRU5Zax8Zq7lVuCJy7URmv5RevU3eYPdGSHN9ZGmleQVOG1kfoXvs6XXISJabn9yUgKsvitxvWJrCjqbO7as4VNsKoTQNViEqabafFdk3Tz5lObE9NgE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "6Pomloj4sUAgDPbBfUEtZAf38PHs04ONq6RqoSI8ALtm0sv0gDDckSuSiLjomvyIn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "66n8bEmKU8RuwndOkFuZ8SXr08IpcoQT2eiWOcsI0z0DiBAjzDSpBcWZSX63yxhpnqlazL943zXGZ6wUwAYxD8tfXkLxQtzJnvGmG1GXaDySeYw1DYJ7BMJzmaQcB7gzqIEdOu3bHuSBHTs09eejfCFDVQH1U85dQwAAtaRCZnHmnTgii16o6IRPZtDDgPZbg5OMTHkGioiwKMfpFJyBivtPR6XgSXcUaQuErkM31OoH7gTxmHRhMBlCJwrmZKqs1"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "UMayV63KSHag8F0KdCX2luQ9tTIILS01yeVw2y2NfMyIgpmnYkFbADVd2Itg6D4jfuWGH7RaZpjoY9cawHsfXOsnIby6rbkZrrZ6WrRl3zGTMYP8EX3zmzFp5WKU6EBV3YTXQUzyLbSxgPR3vo44KYdRJZxPTuZxCVRoF79hn3Qgpki9nBg9Fy6RLf2Vbii9JSx0JbPkzJWDTvAyzvIV9GMpv1wL8hM7TFRHuIblTCX1kN7YGpct6G48YwDjklmzI"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "CsY4qZduPkEikuDRAdeRpqF18vQhyetv3m8usAnEmVeRfa1LEQju9AgPFhMi3WRK9DOAsYFeUr2u0kyIjrPG80yDkFLzcVZr35Qckj0tOGTr3kyRTJ0PFGc5gduD7ZxkdiAOZgOU6L0Wngil9T51cnYGcpAluu1RXXnm6jhteXTBIklVOpyfwSZBeLO613Cl40Guh7k5bZNYNlNmpKcR9hhQyhj5Y60ILjkHU4zdWaAArgtFUrVstEIqbToWPVMLH"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 180));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "pKdKcvQTSbOkSzx9uIhTzCJJjCqMHb9KVWA6esxOLEUWrga7oVnyOhB5KzvRMteKf2oGcS3kwIZNR6ZlOcrSwDpSc1joe5fC6ltQsbAsZUI6w4Xm7EDXtGigfVcbNMVRPCweILPl4HP9L4wyjDaCTmUeG07ZgunhZul1c6pXb4HlYgvn8Huf1hZYK1Sukd86SzozBib6DuBVv05ayW1fpPtN9mZGKvtjbYhfIwMwBydI3ItEi4RookH3gkqCoCLd9"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "RyKumQlMdB9cIaps20MmG"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
