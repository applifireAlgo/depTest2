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
import com.app.server.repository.organization.locationmanagement.CurrencyRepository;
import com.app.shared.organization.locationmanagement.Currency;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CurrencyTestCase extends EntityTestCriteria {

    @Autowired
    private CurrencyRepository<Currency> currencyRepository;

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

    private Currency createCurrency(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(5);
        country.setCountryCode2("6mJ");
        country.setCountryFlag("bREC8lsP4srAA9SQqf8cgzW1Uej6ZyDVKZFpqvx8nvo2OfEKim");
        country.setCountryCode1("1AH");
        country.setCapitalLongitude(7);
        country.setCurrencyName("s7X7mzd60oOtr6xPOL7Zg5fH5kr4B0LYIKGpwiAl9y229IcEPp");
        country.setCapital("aIXoKGrNClZe5ExYvKhbDH1k9ATHQzo2");
        country.setCurrencyCode("2n3");
        country.setCountryName("POKVhzLjDOuP9JFSzOENQuOhplRsJFWNDLEF5nMznaOjYr0WUD");
        country.setCurrencySymbol("vqZSBsUNdKuihhUVfECAkbRFHOysVyGO");
        country.setIsoNumeric(462);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        Currency currency = new Currency();
        currency.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        currency.setCurrencyCode("Jim7T9LPgulfWPwvPqw9IETgM7LgfHt149EhBc5lJ02LREN8Fs");
        currency.setUnicodeDecimal("OLpWfvKqazo53UtCkPVdJiMfExTy6CwZ7n77UZEhIa16h50E6h");
        currency.setUnicodeHex("7B5EuyVmuUBH5fb3MUQ6Jrf4ADrXdPnldblILL75i9un1Gp9mq");
        currency.setEntityValidator(entityValidator);
        return currency;
    }

    @Test
    public void test1Save() {
        try {
            Currency currency = createCurrency(true);
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            currency.isValid();
            currencyRepository.save(currency);
            map.put("CurrencyPrimaryKey", currency._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            Currency currency = currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
            currency.setCurrencyCode("DxangovN945Md4lPDm7n1zXNkvEhGqVjG2jxqEk00JV4BWiR3t");
            currency.setUnicodeDecimal("MdKJDoRxP7sjU9n4pLBzS4Q8BlQaQ9UaQB7K7etWdIIe7pSOHC");
            currency.setUnicodeHex("IqvfMsnAw2dAzbuNBZvQ6A75ZrUXRaiTGyOIqfzlWsZ3lykokP");
            currency.setVersionId(1);
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            currencyRepository.update(currency);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Currency> listofcountryId = currencyRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.delete((java.lang.String) map.get("CurrencyPrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCurrency(EntityTestCriteria contraints, Currency currency) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            currency.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            currency.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            currency.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            currencyRepository.save(currency);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "currencyCode", "56hYUnYhTA6Q1oLN1eiI4ERVeS0l5rVdrUluEm5hUyAuGuMdkBywn9iV7zm6oUe6x37SoQjW0hKOsiOVgtsbWibC2ztnzsm3w0te5abGxgYqiqVDh9NJRnRBZ4GE9fKm4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "unicodeDecimal", "wjmATLIVuiKHiGNTmi2lrHd6uUfDLcgdQ18irEu49uLs96hq09WaUfBXWSqKyIrRMwchnxDSv8o5YUBYldVMbuGIXk8T6gBCSVwUamzWXfXnZ2QRl5F8B5Afbei3BKXA6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "unicodeHex", "LCUSsXe5jLtP72l3CEGuCKfS6knuXxkaWA49ymsIw1QTwF7Ky41Fm0abJ93M8lcf8o3esJ0a7dLjdwjezorFKzao1XYm2MGX5aVOnb2pYamXtUpr4N76k8II2kvGJVOv9"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Currency currency = createCurrency(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = currency.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        currency.setCurrencyCode(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 2:
                        currency.setUnicodeDecimal(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 3:
                        currency.setUnicodeHex(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
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
