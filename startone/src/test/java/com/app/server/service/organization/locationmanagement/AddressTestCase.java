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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("b6YDBiCMUk0zfOhkCdQ21Ztyz1dVGQQWtJLNI4D9KNWawMnniT");
        addresstype.setAddressType("KGroOwhTUQ3GWqo6g6heD530Q5oFnun2Rz1f4Knv4zMfd58ufD");
        addresstype.setAddressTypeDesc("mPuhN7W4wZAvk9XZReRx4jBsCYPgqtMMnODV64mBIFrUH8AZLE");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Country country = new Country();
        country.setCapitalLatitude(8);
        country.setCountryCode2("Dlb");
        country.setCountryFlag("t3Kr3hNrwoiodTmthu3wO7ioezku87GfEPPlGvZO7BX5l95MzO");
        country.setCountryCode1("4sn");
        country.setCapitalLongitude(3);
        country.setCurrencyName("iLaBV4JPm99wErk0vKEUFNrZfDgko30fsvnBWbeqOdiVARMzTj");
        country.setCapital("0ljDXSpqlaPs2wN86DmdBvxiZnB7t1Kr");
        country.setCurrencyCode("jpU");
        country.setCountryName("HJH2TerStKj6aoYQcYXxvBuRhvXKqwiGfqZFbTkAMqh4bUcOGM");
        country.setCurrencySymbol("5KfFt1Kw1bssv9fn3qWpXih0DiFGO1ZR");
        country.setIsoNumeric(831);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("jdY94dDYDBZFHVe6fI97IcxYU6rtj9lgC60tfG5YpVEVdxEO9E");
        State state = new State();
        state.setStateCapitalLongitude(2);
        state.setStateCapitalLatitude(11);
        state.setStateFlag("D4c8HfFskem99kLOB7OtDWxCKoTR7l0Whi9kxJvo28d1ytm2zw");
        state.setStateDescription("WTtjdWsmBQj1LS2oQHEzKjMPrery34cowtv0GokU3Mag3jOuYZ");
        state.setStateCodeChar2("L97t2ZlO4UMdEFjo8VijfQuHXQ4L0rlW");
        state.setStateName("v4sOaiEgsXBSPjunAHo3YIa27kcUgZYQjWf4ZOBw6COjNmpwpf");
        state.setStateCode(2);
        state.setStateCodeChar3("KL2MwgRLrCT8xp9qkUZ5fqrpxOGE3A21");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(6);
        state.setStateFlag("akHSfrBzCHTgTc8eJw4dGsaZY7bakxfLNqZsqbHIuW21jIlXT0");
        state.setStateDescription("wi5K3omJbOUdIcOHPf01yPLmAO1ZKqH4APv2ujJHQxzAXqTLZB");
        state.setStateCodeChar2("zMRIkuLnoN8MnsGNCp21L1p9zYORBPJt");
        state.setStateName("c2b1K7GVXwriOQgsvAZTUGP3nldyaniLa8nttMMDtKE59V1ZOa");
        state.setStateCode(1);
        state.setStateCodeChar3("luWU1Df4StXZIdeXTC8J7BHBkWMBDd83");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapital("ClqdNaG6dTMJdkstev6zrMdoYQ0uYoV33HmCRv3Gw8aN7S56mI");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityDescription("YkOcTgm1rC5fFPFvcdm2BT664m9Q8ZrTYC5nuDqdB2S1PzT00r");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(2);
        city.setCityLongitude(9);
        city.setCityFlag("ZGu3UPDmVpiFJ61HGtp25ohfWl5SbXs3NsssFpjfcaYYNRpGuZ");
        city.setCityCodeChar2("ePDU8umSbZslRVmUAJ7JbCMLjrxJRUt0");
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("YmDZdQSe9LjCTiqCwEtt0vwCWmEhA8jcl14PLYNDWvHHCwmfBJ");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("gHjc8a5HOZv");
        address.setLongitude("iUP0YQ3RoeE5S23MEGYCcSyQSvaFynVaeyVCgHAdVQLFckOoeq");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("6mHnIy");
        address.setLatitude("MUs3K79yrODHjdrHegaqac4L0oVOEkdOH8OnOQx9CciP5QDPH1");
        address.setAddress1("DmItrYPLS9q8kThHs6G9QNXPPV2rSjxRO002RJPlYdxCtqbZt1");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("KYon0xFNUABcbGKo5rbP6Am2S3GUCY5KhM9Et8kKTe8nMvvzLi");
        address.setAddress2("vKpHhKTy3zWvmJmAaD9gXhZz2JiPIRtA0O7gh7TsyYCsFVsDf8");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("kTDGpXJBRu4");
            address.setLongitude("VJQk4cqdzPoeuzjWNUTQYydVDzZu56nBN28S7LCA0EVEFPx7R0");
            address.setZipcode("o5Hx1A");
            address.setLatitude("WSc36p8rezJvsGSo7sVMkhpukt1exi7ydJrkIOCx23P6DKMVZw");
            address.setVersionId(1);
            address.setAddress1("zLDMrtitFljrzTmj8bMT914jgmJc3xyv8GJFftEysU0wEhtCB9");
            address.setAddress3("NKTh8hGBAIMWHMKqdp9dI9XTvLPefjYtqTRjViO5bK7v7OpZeO");
            address.setAddress2("gvxrGkxokxcnT1EffgRCm38FaGU6ITaJfXXWOvLgFt5VquGOWY");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "i7sMMdCDhjo8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "e8GhSVCQUZ16QMoLl6PpqrCvJSz9E3bTrtJfCFCpIrNKwHNIr8SAn8pjF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "QdeNBXUk8bippawRaTjvDEW1XVJdraU9x36kAD3sED4lgzfi6Hhvyk0PR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "esSMmo7PYE96UjCTBfAnW94EynYnrj8RYMCITMHboAnK3903FcG21ekIS"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "Fn1wZcJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "cvpm9qOn5IcbUzKS3LVyn2MLdxxIDXwpIRbaBsD3ONvkpI2QOlriNivKSglNm6W9K"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "LINSMYtvtPE778yjkdRhNii0aU4hsT0qbnx1GtJQW4CBzfju2HejzEw7Z2pkAPYdr"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
