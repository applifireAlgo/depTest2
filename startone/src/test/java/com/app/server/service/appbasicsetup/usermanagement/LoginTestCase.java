package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("nT6Ko1Bw09mHxpmA9PzzAEldTDbeXj4rLe61rnHFEEwFZ9t4Vr");
        Title title = new Title();
        title.setTitles("fbCJ6O6yAqaH3HKPcGg8aD9zKtd9AfLKzWVVYM4EkQ412AE53O");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("TIosdZFToZ8ZuuJCJAUBTOfK6f1ug3pnv5G8YF2vTORACC5c93");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("pk");
        language.setAlpha4parentid(5);
        language.setLanguage("CnL0IgNUdxqC9kX1BBY6Qxz5NtksUgHOWFft0T6S1PCj73Hx8G");
        language.setAlpha4("Obsa");
        language.setLanguageIcon("83IERuyki46Yy4kTxaN8orWO6I9gqUa603GxIGiIM8sZhZsEsE");
        language.setLanguageType("cHQJBzcL9yBRCNVEzku4ar9dYtE08Sxt");
        language.setAlpha3("o2S");
        language.setLanguageDescription("2XNOXBXQGwpVqdspVDTCVL8jg5jfEvv7KEGq86uKFhclmnmRZQ");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCountry("9fdqtTed0wbduqNSOMXkS7xVF8oqI31hUtcMEr04vOFSO5nk2s");
        timezone.setCities("lnEX7WNBDMpHdnwf6xlyl68ajarPgl95iZ1YIauQBBJzfK3bXC");
        timezone.setTimeZoneLabel("Xrz5rq4nFlhAewnHUjZTLPTZaoTVoBW2YjGjTuxP4J0yIW78MA");
        timezone.setUtcdifference(9);
        timezone.setGmtLabel("b6gqkcSA1P2ux9TxF0OSSWPAlnwdFqHneFlDzLapVceU1nTg49");
        corecontacts.setNativeFirstName("cVHOC1hJPSfbycdBVc4uQdr7gJZiU3cvYHa6aEtaEXrd5V9ghP");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("aGgK7B2migH1c9gxM6AHahBXgPo8iWgR2a0DpPtqIWcSWyhecr");
        corecontacts.setLastName("JrB0JZlR5DCKVftHnQYAFtS0S8nAHngDr8UxYyGLpJon2zgig3");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("QPsWOVuZl8zeP0wOazEASx5TwAXELFi3SDbDC2qOPvHeFmCXm9");
        corecontacts.setPhoneNumber("gDntW74oVcq8KMjUP7gu");
        corecontacts.setNativeLastName("b9DsthsLu5O9wnfL8ON4pJTfbrrTcFCGpxjHwEh8Rxy3dsRNRT");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464692960276l));
        corecontacts.setAge(106);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("OMuwLlJVI1m61zbv2EgNMuAtqk6tRsGMmCiAUrYf9AWNKTqpWL");
        corecontacts.setEmailId("ipoFAaz2mmzuXnY2LbF7dXfAUbukFlHwHPQJdDq8UyA0HFk9GZ");
        corecontacts.setNativeTitle("re5Zv5MSIV0djbB1VQjDzeIoxwn0hk1US5VZF9oFjeddj3ZK6h");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464692960459l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("yR0uZ9OLIa7");
        address.setLongitude("THChHAZNoMVuPvEV5ktY0WhkKVbIlQRi11tTrO1sQ8Iv5e46RQ");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("nQnniL7AElwVtJxQdAXGiSMPbrTa8IVm3TEhnKQNYJQugHJE04");
        addresstype.setAddressType("Jszo8D9liieUZ00PABgE2mQbFOqeNbopRbSiIB4ao4VMvEbBIQ");
        addresstype.setAddressTypeDesc("2qnQFaWGxXgS11BcOtBwtMCzrjvWarIVfTxRKCKdDZuyzOFSmT");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLatitude(5);
        country.setCountryCode2("ruQ");
        country.setCountryFlag("6jRKp3uWeLuMeCHPyMAuzbG3we0Z2GMmc4kFZRVTeQuyNElcr1");
        country.setCountryCode1("ld2");
        country.setCapitalLongitude(3);
        country.setCurrencyName("UYvT6YlBgIc60hHqqKnbgdVg5rpfF6MGbrDngyN8XeGC2PSFSn");
        country.setCapital("BTC74gtTD6FGjo0JsWbb6fmLW8Jnl3Rq");
        country.setCurrencyCode("tv0");
        country.setCountryName("kQzPub46uhCVgscqkwlFjsUPlCbv5ZbT0yp7FirWrRdsZdV4Rj");
        country.setCurrencySymbol("RmhggdByscBvWiVTAnmCHE78jtqabL5A");
        country.setIsoNumeric(485);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("quTdrn5mTd4AAbNfsdAXXuDt4jS1ImiNc736dnxLpN4v8nzqMD");
        State state = new State();
        state.setStateCapitalLongitude(5);
        state.setStateCapitalLatitude(3);
        state.setStateFlag("5UJAaCsHdLzmnCxAZFpZOlddQnPVetglmdPZl1HjLebyu1kFXs");
        state.setStateDescription("USPxZoOezwOdpPtUTaOEfbHNtubvlJBT41yJqrjjpKxZerlcSO");
        state.setStateCodeChar2("BdAbk54UHn6fPOSczrCFNT2LTt7yjQbY");
        state.setStateName("QmvVs6trf2hjoo0HHnGJchj9cPgbjneDWkYfpuDNmZEQibeEBy");
        state.setStateCode(1);
        state.setStateCodeChar3("vjYF5zw1x1uHMTEzJumvI7hYHhUlH6Lg");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(4);
        state.setStateFlag("HL2aBvFEIqktjzptQdqGVIxLiDbzM9ZheSXNYBcE5BCfEZqSHe");
        state.setStateDescription("32ma56wj1kg9iGyKWwtp3193RE3pDci76SWZi5czRSUKqHHpSo");
        state.setStateCodeChar2("1E0oj3Vp9JYmJ94W7mP9CATEI86qj5X6");
        state.setStateName("9mYnrciuPA1vmizXyfUulyFpWlMSSh3wo6KDu5rNUWOjz7NICw");
        state.setStateCode(2);
        state.setStateCodeChar3("uew1cyxa61DbYlYl0OfY3WwMMmUfjyeB");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("3E17byRhHHXgPeNmoesmkvzCXIiwkjxaR41utjqkT6Cx7dMyXh");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityDescription("63knecdwkvjYaG1SGySNs7KUJwOI8S0a8YvOXANAfeWLqPvoxm");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(11);
        city.setCityLongitude(10);
        city.setCityFlag("sOt0SEU4FZexcxXD4vJxBKnHjfVlTVF3tQe9O3HAC1PE47QHua");
        city.setCityCodeChar2("OvmgMyEeFRxOw7fFCywA8aPRcKwKYaLF");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("rO2YQxapLVnndzGfqttvudtMiDL0H7xOJrqhzBr0wEZcTEQHUm");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressLabel("OPkhd1TdG4A");
        address.setLongitude("4jBtanJL2yUhp5dZcQuQKLCqOAt9IlipCumX51Y0cMDrzJjqTL");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("VVZhG4");
        address.setLatitude("AsfTZ2uqGBjtnx2a8K7Awr08G3bMDTwSJ3VdLaVyKm6RnjkmjC");
        address.setAddress1("KaJs7BYGoI6BKSbUboBj3qXbZc4FHLFPVjJpWWLHehnFYoidYJ");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("JFLPy5gRGsee6aoFfh3OmZ9XonK0PTI94h6EnWw1svtpazXuPw");
        address.setAddress2("V0nmJjGr8fvBrMNfyWTS0p5D4O1QVp0Ba42tuswA8EcWC43SSG");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("SvDp2DY9rgMHPO2EnTGB7OoT0EZD7eXc2Z8Cp7cFm04qhqX6C8");
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("Yv0YxDbwefBjydJV8F4TWqR4DkW14vnLNWZGjZcEOcSYnHaN9O");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("FhKMmjHO945xJImsetBFGyH49fcmhzvT87w6AM6hoAgnLPeSxj");
        communicationgroup.setCommGroupName("WCkEan5ZHjRCvStedoUCylCnbYqdw6u0kizf6jPCUPMJCFyyMg");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("XGJNHPB3fs6nTtY29jJvfOnvWnMwbevmdyIXU8wBOteqID3hEf");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("MjTcLURbYdavdv4v8ZD8qFSjvyw22lugX0F0EuKPIgsxKOMUw0");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("yRsp76bkBpteE8Jy4p1hwHEPmcbsBh3BXImtDHVKVFM6vDvXNl");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        User user = new User();
        user.setAllowMultipleLogin(1);
        user.setUserAccessCode(6);
        user.setGenTempOneTimePassword(1);
        user.setChangePasswordNextLogin(1);
        user.setSessionTimeout(2978);
        user.setPasswordAlgo("XMKznjyz9oBFhG14O0OwzTnMqvJs3LnbmMjY5z3mznkVZdh2yo");
        user.setIsDeleted(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("KbuMj6GgLXTXOdjnolTpq4jYJe4zS92dDerMs9oJORNyWUoWOo");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("Htch1xDwDTSNStnX3jhK2tkMo8ewuA0Aq3O605mvpQhOfirsFp");
        useraccessdomain.setDomainDescription("2gNylAWGiW0T3aeE2KXE3JeBpVMOWagDcmN9YwZQICXtrUmMbJ");
        useraccessdomain.setDomainIcon("MFY9e5PVOlIDaxCz6lDczc4zrazl4zLWIFrJSXCmTTBwxMmlRG");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("1d3R8w4Cybjkj8wTDKjLiT4uemcgJXFzRmshWbTTXubzlnEgKV");
        useraccesslevel.setLevelIcon("p9h5Yy3kNThiFt6HQ0Qq7NgQsSyryUcYEtT1P9MrPrMDXStaYL");
        useraccesslevel.setLevelHelp("t844zixnGDNbCXZcjITEKF7QlrJNhno8xDsSimLH5lsqU3MLTl");
        useraccesslevel.setLevelName("l9hHpv6honNkIpNIUiHJUSPGhul2rxNxwowdzkGtWAOa3jjWd8");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setAllowMultipleLogin(1);
        user.setUserAccessCode(11);
        user.setGenTempOneTimePassword(1);
        user.setChangePasswordNextLogin(1);
        user.setSessionTimeout(2100);
        user.setPasswordAlgo("lCL0DHqUpcgKw42YrFDAWT6mBFuVndUI1NKfYTmyZvLRea3tBQ");
        user.setIsDeleted(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464692963232l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464692963232l));
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setMultiFactorAuthEnabled(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setLevelid(9);
        question.setQuestion("Z7SoSthAycFQ6iQcKLXINisdj5dEC3e0ZiqWaCu1mdh4GT3M8k");
        question.setQuestionIcon("MrZxx1MP9agOXxg7M8VkeRfHd3A5oeOm8XIdlQwperTJiITweK");
        question.setQuestionDetails("NIzsRC7sOY");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("cdkgLjkxPJJVmcdqnjLpndARvVj6A7yBGSlBRTvX1HAWnKPPCx");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("GwGLEm8yqKMMQessWZSb84gLBt5EiYp7PjBAlsu1VQU8cZmYSU");
        userdata.setOneTimePassword("OZH5XzbmjMbYNWJ4G9XU54LEH18ubYgz");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464692964549l));
        userdata.setPassword("N6VNWNStnBjVwfbR2QuSHTwD99glGkOeaeC9BZtfVQog1K7XhD");
        userdata.setOneTimePassword("fFbiYaCQ2DM1qDmXC5fIE4LR4A3xo4PW");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464692964670l));
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(9);
        userdata.setLast5Passwords("4qvRmrEFwcjiNuiuqEHsK0v4nKIH8YtIMJ2DRxoCTbvi21vTgX");
        user.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthText("uzFWSqYnDoJPxdVa");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setIsAuthenticated(true);
        login.setLoginId("7WO3v7D5y1YV70pbOt39m7aiHUF8c3DdncQ12MPw2iKY8WgblL");
        login.setFailedLoginAttempts(10);
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthImage("1JMHdRgHuxymq0pzbFggYvZf8kG2myNR");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("mO7DKuSByo9BMpa0");
            login.setLoginId("UqPFQ9ll3W5TnSGxI2iwgYHZDjaTNdtYxnbY4wN47mWjrNlkzQ");
            login.setVersionId(1);
            login.setFailedLoginAttempts(3);
            login.setServerAuthImage("mEVmIKtOol0qIetO9UUlgwbPKAADzxfX");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "wkkkzDLQPftI7L3ZawQGWCCRyeuJr2YXyaiouNamShbcmlVeIqJd69YACiUr7nsHEMbcxqyGHwk1jFu3cmpS9O3ocHrRl4T5X4LK51H532SOualcWr0b5GhghN9TQQdIbY2bajM9RGSMxFCBx95BipHr4HEEZFcDiLNXrt4hcrPSyf1Vw5mnyoO8Mx1ww6vq7ZlQgAZCo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "kzBrhZKOcBr7q9ds9UH7cKvasQXxYKUcQ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "4ASsxEYfP3voRf9yF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 12));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
