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
        State state = new State();
        state.setStateCodeChar2("TubCWR5FOHN8uSuO4UTnKuk5k6lps4Lp");
        state.setStateFlag("Dpu1mkzVcZMJUSomIxMYdyb3yiMlCJH5AuWcASGSb22xzgeXHF");
        state.setStateName("zn6nwCFOwr75btOj09K3Tij7E7ztdeRrCDglJONPe0qWTbTfkD");
        state.setStateCode(2);
        state.setStateCapital("4zWvXxSQ0X8qqEexqxWkEt7jTlWpDIEPLClqZbh2FtBSUSYL4l");
        state.setStateCodeChar3("2sUNl95k2qUkT4E0cdJkOgF06Sb56zL6");
        Country country = new Country();
        country.setCapital("cQcuUtC2ARn6bvP3yr5ht1wdRj97YWxp");
        country.setCountryName("cTTF3J9R6B8Nxg0f7SDVshl6t5TT6hSnTcJ7eDwV3gKHEBFEqe");
        country.setCurrencyName("0X38Pjfmi7SoCS0AsCVvf4zQhSbTybWSlveF0Rdmm3Vxnlpt2m");
        country.setCountryCode2("Qc2");
        country.setCapitalLatitude(1);
        country.setCountryFlag("FBCgWo9ltc4UvvvIqUWWi1q8IjHzkddIpOkUcH5sNz9Yf4zLCj");
        country.setCurrencySymbol("WHr9eGwc15O9FynBkJGqrOVmlcQRRTw3");
        country.setIsoNumeric(282);
        country.setCurrencyCode("Qpr");
        country.setCapitalLongitude(9);
        country.setCountryCode1("k2p");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar2("P0jEoRzL7aMTAKCWxAbIen1wXwczfdfB");
        state.setStateFlag("FtHuTH1emFpPTUeOyYvKkJKFZUF3ocF0NmIQtZuP5OTtGrZkVl");
        state.setStateName("T3EfZeHTHUDq5LUIYS0Umy7NQ5gpEdLXtDwajJ5jSEyBqX4sdM");
        state.setStateCode(2);
        state.setStateCapital("LdbCEkFse9TtgAlFg4iKrSmpOsZcjbfVlLeliY6NWvcFel1D9w");
        state.setStateCodeChar3("xFibMIe0BIdWW22DOf8ju1dOEjqJqBBt");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(3);
        state.setStateDescription("IlMU8jX6w1rP225wwknRTieCPbL2COEKwDIKiosgiTwIzxqcPJ");
        state.setStateCapitalLongitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("eKITb7norBqNYksh4zb5EXiBBR9Ilko2EKZYDnh3ufzxfPrkKc");
        city.setCityLongitude(10);
        city.setCityLatitude(9);
        city.setCityName("ceM6xkh7nLOKapjSUpbsotWf1AVdd5Xnqpj6Xwmqd47mMcRXVu");
        city.setCityFlag("f4rzVuDQ25CfFc1MMpoNkm59LGYWsu3COBAolb5fOgsNNkm08J");
        city.setCityDescription("PIU4ttG3Tj3x3hRFJ1sMTruEZoIsWHRFgTUwXMsvOtEboQbk8S");
        city.setCityLongitude(2);
        city.setCityLatitude(11);
        city.setCityName("5M6OoopiSKL04kbsYWUPl79zN0YdZbClcVy1VWsEF0XlGlY68i");
        city.setCityFlag("NgkcNyewDDPZX9FZCxYOYAU9o9YUPKCs6lb6lhGUJEYMb6aqeG");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("OIUhY9S49NORuCj1fmVjP9RkByjNHHCP");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("8cKvPqcEWsDJANflOTLzsZpRZEGI8LMxVHdQ0YjlTl2TRiKQjv");
        addresstype.setAddressTypeDesc("dtZ8qkpr14L3NFdVvNoo9C9BdqNfGHkAMYTPQfaN4EUJ1O5Ypd");
        addresstype.setAddressType("rE482mgJi7mc7S4Xq65GkEz4PEM0y6cW5zoMLR8XKE5H8B4Ypa");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setLatitude("z6eTGtJ9BmUu6uKIyYBKmhd7lFepoTIPMxMpOmoPPGMdFt1Inu");
        address.setAddress3("TrPWGVlZbTr7U7ybsjcnxUcQYVjz8Sm4DXVYY3x4tx72eP2Ub3");
        address.setLongitude("AGR6S1E51sQTt5Rn88JDG5po3Xh4tsHuymhpXn5xMNVCCddAXW");
        address.setAddress1("GRjqz9fm4jNNbM0g8rN8b2EXF3jNfWFTWztTsUPuzygTIsBMAX");
        address.setAddress2("IsUfWCDUobssusY76Gu6OUJZtHKD9fuwchLYU9AqTOeb3fbYsi");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("f9mNTW");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddressLabel("tFBBO5V6r4T");
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setLatitude("dJTd7u7qJGM5fPzBf5AfqF9QQUPPBxaOn4eoZ0rXux6o4xbzOg");
            address.setAddress3("uY1dKm31varqnPasSUDCITEQgLIgNH88GX45JXe6kv8mT0aaQw");
            address.setLongitude("sMV4lPhy9a7xNxzyGl617ksGl0KKnDGpGQ3gfhd0g2SnlwpAW3");
            address.setAddress1("fGwi2xw6tbYUa2u5gjm9Xc6Q0phaGcE0UObZQ9BkOZBpTbuM2A");
            address.setAddress2("kCyjA1Nt7Q44r47U7z4Nt6sVpbSzdL2FAdiEHEU2KX3SJB9G4x");
            address.setVersionId(1);
            address.setZipcode("qobYs1");
            address.setAddressLabel("iTz9c9SsXza");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
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
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "VwmSGym1spPf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "GhY6fE4epfMzv0vyAf2eu4VrtIdJujYetQV2O4wSsCTa6Ep9TRH4aUuTM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "AZsMiichQDfxSzIotlYFXKarD7GZRbdXijzI4Y4vZkS3eVLdAabrSdPwf"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "HG3EGpiYcEO6YPvgFh82IWeIp4A9uy1CisiiV0qonRc20e1yDOoRZDbC1"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "2kn3Mjs"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "ytPKMCXOcfJh2OLXJxVN06GwBkE0EX5g4yzZaZZSGCViImlcuSoegY9OarFlnbnWR"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "lZ7lIdsGfYgdnCYKbIDxvbd0nEvzfDSmYRTrG1spvLilEnNmNUlaDv8IRU7AmFm09"));
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
