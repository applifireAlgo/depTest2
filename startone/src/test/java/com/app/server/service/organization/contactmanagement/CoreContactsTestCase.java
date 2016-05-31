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
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;

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
        Title title = new Title();
        title.setTitles("CZrX8LE9sRZsFlvoP6rt202K2ZjimNRwHUggxH2vdGmRDJqG2m");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("yfEX6svhNjXRdR00g7mvLyfV680chvFjaQK5yFDgTS6wOL31p8");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("Nu");
        language.setAlpha4parentid(9);
        language.setLanguage("6rOBnSfMP3IdyHF8GpUnqE1NT8rCzW6gJ00hrDxuVqLKKrN5Ut");
        language.setAlpha4("f6kZ");
        language.setLanguageIcon("iheEJBh0zcCf636XKlibH4y077NsrZHODkPybEuSR4JPadQDnS");
        language.setLanguageType("sNALfulXayrqdluHGsRrwNurFvzfCrtY");
        language.setAlpha3("RiJ");
        language.setLanguageDescription("MbQ0kTWgttBu7DCV1TlSGRj5Cshgf2gs5Gac57p398Nl38htCb");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("oGAsPLbByXRlm5wqI26eeuH2rXIXgcyIHY59KiXD7HcEihF4bX");
        timezone.setCities("yjibm6UrBr0WngQj7msODpb0bgGCqLMSTs6O8DRcpmMzmuYd4t");
        timezone.setTimeZoneLabel("80rHKYhOy5nS37eNzgqtKE3skmBeA09kxlvdOZizEbGiHqezCI");
        timezone.setUtcdifference(7);
        timezone.setGmtLabel("rAahOIF5uw98FPBOeSGJFiPHYZrgGNAH7pZGxjV1RjEU9mpRac");
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("m8Ma5KcM4SqKOZIAEd35mMEkEk1AW1g3k5VklMf2AQRaUa4h8H");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("trxYVHlzyd2UUykRwVU22oMuBHFw4c0dAeYqmrKnD81WcRNS52");
        corecontacts.setLastName("3GNgqaTsGSJkhVTlTYPqiWIR3DvcALLujPRdOKNYPlhebUEXbk");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("ofYvUdM8vBqOfecjazcpenKqwom0Ig2aub8FVD4DdypLO957pH");
        corecontacts.setPhoneNumber("Ql57ef02vxhRzpDsDKlU");
        corecontacts.setNativeLastName("nwh9ywR4AMLgssQ65ZzJYM75PciizLqVGLhJlpG6vuUcpG7XfJ");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464692908611l));
        corecontacts.setAge(80);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("VvpRWgevrHFQwl7tHLuf7p5t01wVeLWd5RX0y5k8uwDsyFXC1x");
        corecontacts.setEmailId("AyOACJIJpbogZg5OAKtgbLLFTD2JhQdTSZrLalrXPBkXem8D3o");
        corecontacts.setNativeTitle("Ugrmzc0M7UCCldVlFRxTVcY0Nvtfxm8U6vBBUBns5ROOaAoN4N");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464692908835l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("VogUHqN9j1y");
        address.setLongitude("GyYJC1MOzDHaaymabSZo0jhMqZ5ZWcJ360bUPortYYHSyLjzYI");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("zAc1KBbttFSPkgeBLG7dU25vii7Kk556456oAv32kRolYbT33w");
        addresstype.setAddressType("35wqrXyM4al8E8XGJ23OUlAhjbC69N1s1ZiNbaZJSRVNLDTtvn");
        addresstype.setAddressTypeDesc("i2OqxNyIqWYIl8i3fqBbjYmfJXc1Am33McdruxygXd45pdtkRI");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLatitude(5);
        country.setCountryCode2("Qx1");
        country.setCountryFlag("SZaPtPdGBWuXwBp5p096cPjeEwNe9bKPQQhSoIG8DXOa9uk4lG");
        country.setCountryCode1("gpK");
        country.setCapitalLongitude(2);
        country.setCurrencyName("35IdQCNdEDZR6hh9swINXRgVCv1d7wnQP7FfoY9VUW8FvGQhtb");
        country.setCapital("YpVNFQFLsAoA1Yr8ERzgx0PDIGMRuEjM");
        country.setCurrencyCode("xUL");
        country.setCountryName("AEzoQyUhk1uLs5zGSz1KJVSYewJgyFCskwAlQAouISFshYapOA");
        country.setCurrencySymbol("8kVFiUbcESRYbpg9zwdCEAyHiakFerHf");
        country.setIsoNumeric(131);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("7Deg16e3zh7l5sYi3MiRVZJkDAJ0SVW2yCm36oeKYlwONjB5xS");
        State state = new State();
        state.setStateCapitalLongitude(8);
        state.setStateCapitalLatitude(6);
        state.setStateFlag("iA3PdV5osGekEj8RSVt8TVnaEsNJ9qmbcq62i5tdC8pD1TcJPA");
        state.setStateDescription("tLzM9TdVxEYePENj9nHMgc8GO9UyBi4Bt4xM9U9RIVCkyXAQZX");
        state.setStateCodeChar2("O02qDMPsfXpkfYUPzZRpeROP4P84vIBB");
        state.setStateName("7hxjSvbXlEgyh8dep9BcG2T26RB4QOoHpDZkA8crAxDtB6Tss1");
        state.setStateCode(2);
        state.setStateCodeChar3("N020GHsMJrcje0YzoMAsBAogWA59Dh5n");
        state.setStateCapitalLongitude(3);
        state.setStateCapitalLatitude(10);
        state.setStateFlag("pZdyz8KtvwMZro97JiM4niMU2eUXPdgdxiErcprihjIQWbEnrH");
        state.setStateDescription("oKnvXjwSF3EiLSPSbNW3YLiV7nIhz0QR4YGR9L246m4OxGGIAP");
        state.setStateCodeChar2("50jZ7b0TKItad5FzHrOvTjYYG5CNABaK");
        state.setStateName("8hRi6tX3fYBzmuzCtxn32ItW4YH92l8NDpkoLsge6GN3GxTkRF");
        state.setStateCode(1);
        state.setStateCodeChar3("aSv6BFfPqijrMukDUIgPEiOEBVfh3MoZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("KMKgSpe8xdHo4cciscNbIX7AwQ8WE2qxXhbkatODz7zVEmHM1i");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityDescription("Xgfbzs0Jhq3EGrOudGUrcbJ1OHgsCjZ4l31M4HCGTR7szhjoXS");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(6);
        city.setCityLongitude(3);
        city.setCityFlag("U7WOfHGlXZ9pauBTyDp8p61uI76MpMpuzc0N1OqSs5EAyiz6z2");
        city.setCityCodeChar2("U7HstwECk2lQs5gj3chhGhJDW3H5EfaO");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("aSZxdJr1HRmWn7L0o7GB8yk0Hxl1oPagjCMdmDL1ivxAkWGiQB");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("lixHmNm9qwg");
        address.setLongitude("pvsiZ0cHFc2J1TiNHMoNYw7fdRCM4DLUPuo0GVYhGqppLyt1Eh");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("SQXz4d");
        address.setLatitude("WhKM3JyM8Am2UeAXEU6myTNFI8zutHjaBOdmNUtg7I7jg6Hw4y");
        address.setAddress1("eRH8rejkqTE5p8qjiKrDpBuxxinovubSUfNkwLDErTg4z7P53M");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("GidyjC4JFyzyCsroWMCZiNZsPE7N72HfeRWc8E1l75UuoVy4n4");
        address.setAddress2("NkYo4IyFHgNirMWGNRzV2Enu55V00tiFUgHb2tTD9jkvVKPS0j");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("EQhVw5f5M6rjWos3EgQv8c7tVGkcwEeaZk9iCUqj90A5e5LZW0");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("4gM8ehLhlM9SKT4qlkE4bd3d3LzGHd5iiCuwcqD9F29sMwl2HV");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("ZE8VtvZL6N418P77IThkst5x52tYZIP8MxTKeP5YB1vCT6Zfxd");
        communicationgroup.setCommGroupName("f9iEe0UMIFtEIXkIa6zwbs66QzlIvqzS20SHBU4N4TzCtZjDlS");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("xSz6VkWmSeshABpFlh5abElQ75pFTureg6mrQMCj2KYgwIxzgl");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("fbXyVPp7zVDrKRDDDEYY3oFyg4Xsjj3o709kYc2pkF1SyekKLY");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("TuaavgiKdl4s9gB27EJXuVtyIkmIxKn2fQYbJ4EOkVYIVPYBUo");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
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
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeFirstName("WuTAkRoelAMpRLgsru7KbO7kTnf4SDCwWE0WVqEM3ZWJ7YnCt3");
            corecontacts.setFirstName("2LAP728G3UBZ8QmLq5DBC8vhJ0uMhVjXv3X0DUPo4RiIicedNt");
            corecontacts.setLastName("95hXl1VRef30ym06S9Cz0XxcsQqpdkNHi7fLunj9T4oFuSH95p");
            corecontacts.setMiddleName("PRVXIZ7i2fRvNIFobuJmxjX4P9n8qs3BNtVjmXOhzMLWs5GF0G");
            corecontacts.setPhoneNumber("gyC26R6SIZlzKiW4hbDs");
            corecontacts.setNativeLastName("zQ2aOz895ct70REJSyCKCsllVHTS9PiQ4Zc1HJscwDbAbCWgEm");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1464692911673l));
            corecontacts.setAge(87);
            corecontacts.setVersionId(1);
            corecontacts.setNativeMiddleName("3j17YFt8Ui5QfbGPUP1kFLODLrnr285XkUnPBBMpCnZogJWJ3E");
            corecontacts.setEmailId("r8rxHO60dgcCCOBf2DmhgNxRaJHfNXa7YAHvwCLzfJ4JB0J9ZX");
            corecontacts.setNativeTitle("OLawsAPaZTR2SqpgFKxl10epg8Y9fplTHQRm6mydpaEV4hTsLw");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1464692912137l));
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
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
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "HUabCOYFXCu0goI5f3OBfajz294tW1zilQtWNUafrWf7Epa6tao8CXlkV875xfHfxQQcOcmTVkZwgBXbSaOtq7Mi9J71gGvWimUDAUeWekR68r3iuO1uogpyDtpOnqx4ejHVnmo5LMi1uaswtyuXmSUL3SlcJmxnakSNHKWmcE13OxQA4lBdnGAEQgunFwfGqFDIl5fEJNt6o8gICusGNM6qPQTutSzOYN1YJEN86LxhyFTeJ3GLcQQcKkR5Zhxgi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Bsg33T1UaoGDLrpGhKBr9XyGEEdgTbTBf3IuIHKqJZjfCyj7bpFMjRZV28rICl1sUflf5CTXVKmAZuZCBReAsyOdKmMdpGRSTPXi8wpvR07c6yCZwNiP1paWgbtKYWpAYEXzpJxiI5LKxCeMZ7oVgUR2Y6uwjkrSuLibXdkwUy2T97izTqU3N2gbo8ejNivl8z2vVshLr7MbKfrMkgUIVzvSU97fmphptST3PK7XWdpNKX6VRgwccdemPNB9qpgHV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "8SlLxZL1rd7TbIKhr9N4iNUAtinX20stcVzBDD7gQCrDhSMoixkic90ph7R04okqoqkiyE6LDE6lhLrHyGD2lEg1ce1Xwde2GhZ6KLpZfs4AdzoqeelYRJu0M5F0vysyTYK5jKWnBgUwNraGTnmO5iLpKKqCYGRK5ZXENkgUsIEfNe1WeocCnLSVKHF7YNiFZUMRKIQqNJIvXlRPi0CFOOKJXhTErcSEaccC9nAOpeT2jTFY71p0ootJ2HLRXoMgX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "9mvUo6j7zgNBg43ha9fmlBeQhLCoBmOJX8XkUrdP7lMGddMtagIqaEFX4HDzY8QnK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "Qe7G9FYsy5ctKqfr3t52iMrzsSTrUty67GO0TA4P4ZOKXXXKu4sabMxSMOi5nQLc2wDjgI4izimbFjDSlk3OOW40N7Lw9JDURYGmyEQaytvSvQylFChLAq8kYAwU7KHagZhmW774ewnUD3CzTO7KT7Ly5TBcXM8AFhigChw2KPjlLrLcfc9A3sGIo2nYobrtHOz6nd2C2eq9BHq8vC3I5d2WiYeHVfZRfOiNQM4hEBs9dT2OSsXJgzOqY1bnQ6UtR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "LHmvxFi8RHTPUeyhvRSbzsF5zgQAtFolIY4QsefMBXOWr9Ov5STIWblW4zxpXIDUSdUeFmnZVoYNW6YrEZxJiPw63okStI9rhgSiMKtLigoeK2pupgdlScciwVgZZhyyFoxKizhvZJFj4MdyEaJNBpSr3lDaKCTlS3wr3YKVJjTti7J9ZVuZPk2utMGxrOk4Ke2W7KKrEUzxLFk5dowvVrR2uPsLYcheQJ2PHUwwEVesQ5P8AF14m3kxCM3ncTKOM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "hYu6mzwR3dibLbAcP5XADqc2xOoQqoynkxmZPai2O9Zr8izgsaASgyjOMe13mFC3qmQH5pDI1loHeToaEzmHr0yIGGGrKCdw1oMGGRFeuPQ1iw3Q5wwRT1C3jl4YhujaXqQ4b0o4LAR6C38htCE11pdTU4XCMGUJQbB2WGCHNTcJEOsJYIjsvyYqwuig2jcK6gS99LcWaRLj5enkCHb40E8q9t86DNzoJdY0DZN7nLWiBZdRRmOMIwyavkBe8frZ5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 210));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "1z6HLdMI9hJeHQUpX6nWqT10EMTPrC9mRrlC3wXDcQ5fCSj0zvSuNCq2LRwJ1seIla0dJA48xjYxMTuyL99Fyzth0hsGKvkZQA2rd0e3JepiIMFnuBLtWqcl5lJVKnUnVDnlbPvSz1xcOl9hTE9XolFEsc4daKWIJICt8Bmp2aeQgALjcq33MODQCVwFd3z5PpKvQWHFoqJeCs9Fa7SKxh54cWjv7MRqQjJCrpOGARnoglBevqUvh0gHGHmn0J4o9"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "F8QjV497gOjyV5XFGQfyY"));
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
