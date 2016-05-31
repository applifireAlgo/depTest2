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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
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
        Language language = new Language();
        language.setLanguageDescription("BsPzZLQjyVvLlsgXOHdwqRyf36trusK9iPFf8qFlYwREW4Siy3");
        language.setLanguageIcon("8d1QYftlmL7HT0JAVyFr4Uon7poa6Z9W56XdJDUaU3F556tHHH");
        language.setAlpha4("2haT");
        language.setLanguageType("UIXiS7BbSWz6DFStgm3i3NK9uwFW2EVA");
        language.setLanguage("8KZ8yrlidqViPw2aM7dSyKJ8iSkQ90nYyZZU1qxRmSVHDzkAPH");
        language.setAlpha2("oq");
        language.setAlpha3("i2X");
        language.setAlpha4parentid(5);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setGmtLabel("9GX92kyjNpObQHSFpwqPIUhFjFBULXJFSypETAs17tbTKD1EZ6");
        timezone.setTimeZoneLabel("Cdf35Ix4fdaIlWIklpEKIcMyMrBVn6tgf0XwiU0YDhIoKOtDhA");
        timezone.setCountry("GzmxwAbhxZwQNWkbdUMmqAmvxHES9WFI6pNbesm1G30lLKTQGk");
        timezone.setUtcdifference(2);
        timezone.setCities("PceJyA0Ajaqls5YAqDg1yMgyBkzbuQheyowfxsnlZL6AwRHmI8");
        Gender gender = new Gender();
        gender.setGender("DINeoJRhYrWIv9Y4yMVlFMqjZmWFWtgxb2U0Bl2q2PWGfgCCnr");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("gzx5g9rCQ7bwSmNZmvxWbsqHRPz6pREF38jgoBz9eDK4Hkaieq");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(112);
        corecontacts.setNativeLastName("KtYm81NgV6NPMQK5oU3ECOmAf9j588pLovyA8likRXsRGzQHTF");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1464686059213l));
        corecontacts.setDateofbirth(new java.sql.Timestamp(1464686059213l));
        corecontacts.setNativeTitle("1h3NLQLr0fMewpCDGE5X2nM8TwFG7JcAfpwSYP9jf2gnSRP9ku");
        corecontacts.setEmailId("ER3guErKXdFqPrh7uBfmxNmAqgKARe4LyEuAZlTSGXYQdBcHvc");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("9qHEAh5hJYqrtFWUYDbYEEaGrWCDzjPrlpKYjuZwBdm5dXFBo3");
        corecontacts.setFirstName("Oj4yLfk0nSXOvGdhZTyQR4WByXN8CarENkkMiyiAaMGpSc71qY");
        corecontacts.setLastName("V2JQeu2oA2vkcsVxRtQ9TqG1hOBkPnCbbuAwZEPjLokbzjCfNU");
        corecontacts.setNativeMiddleName("wPpB8wpwAGsWoKmhJqfpcPsamHm9QxjzEetHsfKWnuhsm4DIqe");
        corecontacts.setPhoneNumber("gegB3edRzhjfMRVKLtFO");
        corecontacts.setNativeFirstName("NDkhqlt8vsqoL8GXHP7NpFB2OofO1VEDrPPcNac03c705b1nDO");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("9Pw2Rfg1g8Ud8WBFmFovmNCXZZQMNtkC8qY97UzksKkN2zFbvm");
        communicationgroup.setCommGroupName("3x4m6AAlWttwjcCHsYiq7muKFCBDJEjDDdEHnEXgLHugUl8kuo");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("eIYp5jJkkEC3xIf6iI8hHiXFyQD4YKDhc4mlZ9Os8xJ9bhpOOl");
        communicationtype.setCommTypeName("9Ap2NQc5yTdDIH9DOvjPSIwwEIbSUdQmBgcKDMkvq3RbN1T4n3");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("57dVwQzyrp");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLatitude("bnc0b0RAN7VYekzUY4OTPhM8InF5vZ16rjj6HMJ74uPttyqOLk");
        address.setAddress3("XuVe6dYU1JIIhgGV8pTIGKI9Z5hV2mEB9spD70PYb0deLtkUtm");
        address.setLongitude("cviomKsOWdhRuu3rklb73l09ay0GPfDaush1cdsbqbvhRjJvbb");
        address.setAddress1("0Jps3i0Cu06WDXsyffq0KcvEEy6JcyS2aTay6DGMkP9KlBVfaD");
        address.setAddress2("TZyDR1xyM7NAutCSFB9CiLJRMsEFxk17rk8YIgGJoHFFYma9SL");
        State state = new State();
        state.setStateCodeChar2("kIr6W2QfZRTFDcjcsMJ7ay0rzDBiyVAJ");
        state.setStateFlag("FonzM1VUtPCVktE7egMWhfikdiNRZWCzV00xkcnTbG4rvFlm5a");
        state.setStateName("PKi32bc0td5P4ZiL1sG3oQHZDwimHwJgyLGwAQjOef0DQFJaAH");
        state.setStateCode(2);
        state.setStateCapital("JKF1QzTnxCjyVjX4TiTCpCUD4ML29wHIVPXabTplCzucExOydR");
        state.setStateCodeChar3("ZyxQmSKMzi0C7xaYCBrUfIghzoT89VgR");
        Country country = new Country();
        country.setCapital("aKcRCdXsIBlrkOSY05U9tuastsGpL2XN");
        country.setCountryName("tEy7PKH5GpuitLleeonQEUjQvXISgEwUciXIFCj14FsqGUU7rb");
        country.setCurrencyName("gOJX89fcfsVQzDlkGufPzq7iwmU9CYdeMzNiwQVKUiedrVKU7b");
        country.setCountryCode2("R8s");
        country.setCapitalLatitude(9);
        country.setCountryFlag("Mne5aOGVDP56IAYEnGjtB0yOnSqF1d12EY2obQ8jKdTjeBkgob");
        country.setCurrencySymbol("hN9yKDp92wxPXB1MgOZP12y7BYBHVJEo");
        country.setIsoNumeric(250);
        country.setCurrencyCode("gYQ");
        country.setCapitalLongitude(10);
        country.setCountryCode1("SAp");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar2("VBFZGlSp2hBA1Jy5xkj45MmbxVvV1xlO");
        state.setStateFlag("WmDnBkxEQ3wDx7y4OWXPPlKruAS6YOOsv12is1VJzIW7m1epMR");
        state.setStateName("NeDnmAx9MJsx1axYSb28Cz8iS3ARK41yMHlD4N3fyBtdnpU9ZR");
        state.setStateCode(1);
        state.setStateCapital("pAp3d68rYwjefgRCLFAscqJrfQqldTjHqvKPT9i6kOnXzZd4Ow");
        state.setStateCodeChar3("dG2EaetQ8rHe3KT51wjIdyfQ7yJvhvuo");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(10);
        state.setStateDescription("pvzUNmOR9VsjdU8QLjDtCh229b2jDxf0rxar4nb0imbO1RKQRo");
        state.setStateCapitalLongitude(11);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("sHZDcpCEHvfc8fER15H7sU3EvCtZAiOfnrCp875RBqcLy7WDZP");
        city.setCityLongitude(3);
        city.setCityLatitude(7);
        city.setCityName("saYrC3VEtrq68HwoeZmokpLPbfXOFemLYts4F1zHmBOcRoktc0");
        city.setCityFlag("nDGEgrmjoyRm3cfFlDWMNgpcb4rEVsRYfpDRohJ7pGohYdxM0Q");
        city.setCityDescription("fuTXHVVcTrjIqfHBceqGem7NcSYvuB28EgMZVsOdsCjrWsJ9gZ");
        city.setCityLongitude(10);
        city.setCityLatitude(1);
        city.setCityName("Bu7fiVMh1Tw3mDnte1NwI3nSRNsXwSlB5sek1l2ErbfePc8LAU");
        city.setCityFlag("E0LQ3E2Ifgc0V9x0dUqOUMG0O9AbpADdsMKqLI2epL6tqs1qua");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("YenbW1wZwYW0zied1ZhCJmF1YRH1Ze9f");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("szqf1hFS942IWCJEan36M914tnmKFv5jmzqn52UGzCKH4qOpAM");
        addresstype.setAddressTypeDesc("41rJV9O1i9WSSV27DMUHYrRl1A9tRWpqMua2dh1uCadV40mTvT");
        addresstype.setAddressType("Oo1S8YAVkw6TwSk7lHTXF0nqbBbVFK372KjGKub4dTyBG0UIwY");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setLatitude("l3xYBj8UiinXA7YlMUuP1N4YB0S6m8JGg4MgqdHRFf2XRGIiky");
        address.setAddress3("ZbKxma9kJrK7Kv3QxpVQTnweGVelrAXPkFkHmJOjaiCYaDJV1m");
        address.setLongitude("L3jaa94whkoz9eGiTPpF6pFPpLsmsK116jJKTXL9Dvw8r9nPrB");
        address.setAddress1("s5hpj7xp93P6mXTxj5YCfaKpyhSvQYAe0VvMDIyMtiZ3cOYqQj");
        address.setAddress2("qxvbQLwFsUlxgxhssHHTmm2J1GTBAPsaIjqxJbO8oZfBgiaENQ");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("t6EvFX");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("z6e8HDpNod6");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464686062137l));
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("s7XzRCPRtzwnU1c4HODkLFDzw4A9Avna5ERLR4SHCc9KZxM28b");
        useraccesslevel.setLevelDescription("BnMXtU5J9JthxGSRimpd4LuhJSW1vWX3ahLDcVammKIQmpAl0S");
        useraccesslevel.setLevelIcon("tKMlTNoRWP3VMesJa1JyybNS3nzSyBTap4OXCtqnnevl999m3i");
        useraccesslevel.setLevelName("9BgpK5Qc12WubJQxVJY2ypr1p9OgZw7gnwyma1PS5DJ7MpkUbI");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("zDty78Ig3ykh2CUWBDzwrGJ6Mx0sXPGyi7JDBupsgNzbwcoukI");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("g2BQW2FLcfwm0u0x8SaGN0LMKLcxyMHdMtyW0hlkA5Zs86EGym");
        useraccessdomain.setDomainName("06tQSxXckfzySTsdRyjXNz6IyPsfgCgiBg1vWdqk4nOMycIIHQ");
        useraccessdomain.setDomainDescription("lR0ojUWA981QQf3ew6V9bhvEYabObQjOIgWS9PfVVnUgZskcpr");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1464686062258l));
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setMultiFactorAuthEnabled(1);
        user.setAllowMultipleLogin(1);
        user.setIsLocked(1);
        user.setSessionTimeout(1713);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1464686062682l));
        user.setPasswordAlgo("Dmr0NItQpoXfJKNFiRlihqlvrrP2nZtiNZq2YbeDmTM2yucG3m");
        user.setUserAccessCode(16760);
        user.setGenTempOneTimePassword(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("D8e9mqnTU05Y9akzytn3IisEsxVwm1nZWXvYs5XxbL8ZLwoLHx");
        Question question = new Question();
        question.setQuestionDetails("7hUuydRLtZ");
        question.setQuestion("Zn4ZlrZR2Ldui1zVAE40TFKuXKxR9JDw28tl6S9qyztmCFvtV8");
        question.setQuestionIcon("svkvOOcmmOr4bLCesiWqEB7dGQ9EmWwUopES3pynUAhGkKlX9Y");
        question.setLevelid(6);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("6IaFpquqMTipsF5v2peCXRpCBHsgUA3CxCTmv9KeTJeb14w1vq");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464686063796l));
        userdata.setPassword("nzrZxd1T10bDbOuHmnYFAgbObte2PCLJIwJUWSs1jphYIMlviq");
        userdata.setLast5Passwords("tisuMXYoi6jbT0Rr3hnu8SHz04DIIYeqeRDYmxwxDQO73gKREp");
        userdata.setOneTimePasswordExpiry(8);
        userdata.setOneTimePassword("9IyldzMYQTnoFz97CnVenlZ1jUubUYkU");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1464686063917l));
        userdata.setPassword("aDPxr37uxB7dpA2wBd1N6iqxb0AUn5QP9EqwUKSrLesZ0GMrtT");
        userdata.setLast5Passwords("FEHCz8acXrcyDo2Vp0BoERvtpDytzLPpBhR5eObg4Yppfly0VS");
        userdata.setOneTimePasswordExpiry(7);
        userdata.setOneTimePassword("lU1MT5K46qSMOApHm7YRlWLGKKnyCeUa");
        userdata.setUser(user);
        user.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthImage("FqiCFDizAFTcZ7L84XIAdfZGq8obSN9b");
        login.setIsAuthenticated(true);
        login.setLoginId("C5AXHT86brpEUbiWGo5lZDJxld0qMvwtRWYE7nnXGLojYCegS8");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(11);
        login.setServerAuthText("I8hycPuetsQ9VmTV");
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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("2REpUNjDTFbNnOIPmts7sX01G1CpEc1N");
            login.setLoginId("tLW8CSPmiWLQZq7PX7Fxw8miXT24XSnLPETq2JOcMnZrDsHecg");
            login.setFailedLoginAttempts(8);
            login.setVersionId(1);
            login.setServerAuthText("V97F2sPnji3jHqXQ");
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
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "3nG8FMTqZ8CDxrsKG8eh9rbwXvPxK2XPq0ecoj4jotsMd1Y0qINUVBhWeocbiNWy97Kbao9ikbCyl2AuRV50MfjV8RrcTM2jhZ8JJAEue6lPHWWVioX4c9dx46MZvsRLkj2P34ijlpUa768PcmvP5NfRQNTBo5kT5T4bQHb2MZVRmkw3LaqO7Mej053fTZpH1DQ6THJ7M"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "0oDuAxTz2LKMxBOJ8sQc9OOdY6ae1WHjq"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "yYdnojx8eGbwkU8Pc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 22));
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
