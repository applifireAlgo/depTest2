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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("RRChMeqBFGtr2hepMio5Vz4lhjN96CymggM3AvpFuYHXEQGoVa");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("Tyin0mVRL9ELlkIrSOvipmqzCd9hqwcFW3XWUofLCQ1JqmV3rZ");
        useraccessdomain.setDomainName("PnXAwP1nUFyjBF9fEq1Hu6dN5if0Y6CLmqzngwvqHyMjTZ3iXh");
        useraccessdomain.setDomainDescription("DFzBUTFIcohrwvWHLTrsKAoOCkfbPFO0ktrCZBdb6agPnsTV5y");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainHelp("6BnCxQmWNP0az377hc0CNebCIi4FmDKMWgRYUPc3W7r84JQY1p");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setUserAccessDomain(82674);
            useraccessdomain.setDomainIcon("ihOkBG62CJsDYGkI3NKbe5xHqPUnJY0DUwnVcGM8tENvTs4liW");
            useraccessdomain.setDomainName("KwPav3V6xftBZI3wShHG7jWFCmK9FLyic9uLdK1EvhSYqanT0b");
            useraccessdomain.setDomainDescription("fOODOGGDHS7QnedIW9elpGwfa9I3X8J0NKkNmWZY6P4QiroRig");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 189573));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "CbgZAu4PP9yb2gB9mKGp0FSVG5Aqd5vbaknYwegBnu071b2H1kiqIGnhj8awfTkK9KGEoLSj9SmKPjXrj0V3twBd0tx4vXeMWWaQk3ONG6zxhJGMLkGVpg311fJZx7EtesmjYugf6iK4rsVEMMAaWGFhPr00RWUEn9shR4dR57P4xot8LokJkyPplthgjSqNMOog9MmB5Q4SwNKH5UifIMdTh2qQRIJSgV9fnGrNxVrB5e4joOvl4tMjejbwGaJHy"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "DNsWG8zYM0mMklg3D6aW6T7b6YRsAZshQMivrFDMqW0qlz0svhjodlNRrw4tLfzP8Uo73UEZs2UeLX7D9X3NroYBFMA1btaDSxoU2w5uEKl8s57W0XvrWfbAdDlGdTT9LLdTghmp9rAX3x0LuLMdaJKh1dcDaDgZW2TiNtSIr9HfV6VLgq4qI2guB1fgqW9XzQ5BB7hEFlLlKwZGdxEzqQP2kCr13r9zwfw0qVrNiS0Y2msN3m4fT77ir2ZyRW0on"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "Ljwh4lekn3xKwpdeOHIdTh5woKTh1L60cbhlsPLs5eo6UYWH4PpspFYs3zPwO2y7FfudXgbEQKIWbIfdlfW1bCVIzUmAXOfDIawGCGVgNjV9R3tYT2RhKkpGVrm5RvBsPAK5DvjXwwRIAWFlKnK227vOwn3BuuNNtp5eEXuUWdUJo3WY4A6W5dzxzc3DzsGRjgRelS7eibw4bAP7GXdAoWoMqiT8ID5Op1xXM2XPyfIWIDRCCfHo30QDDOt4HnRhGMXNIBxX7EePe8ejKbcjOsQ7SCWyFQ9oHEtbc6WNWdMu1llA7QwR12UL2ZpUUgQhzLIn15TYEPWcB49EI1sepBDHhBfAA7EKNSSA2Zob5ZGNKzKQaA6bQv2X3SBr5Hg9liYWWGGgKTjPw6XJu5pT2Ev8th7xTx1U9cndGAy5KURSG1KJ6JG46Nt32SqJV0yWwTz1bMaLxGmvQiREwTMkS03ynly5ooYqxYCOb07Xyk8O469PnGKUe4uauBhVs297NkFL6N7T2k7vz33UWYwMhU3gExSMJ3UHFeentKVJpRRcrcv6C0L0nku2s0jT7lESnVVZaRtaWLtJJLvooxTRgaEIwthsY3uZQQ0f1kjxi5pCn1d2maOA1CEHpoJvNTXCNGr2D7SMltzqza0VlTzJ0WnKKGJHRt986IAhYMukwefp9QBJ6KP8ZWENu3PaX5NsQ3HH4jPIkhF9VbCrNCImO82r1qcDFRw8L5RLYQUKiXAv6m57rtd1e2G4YxWm7Xr7Utmmi9ycCp4xP4nwvnkdbio2EtzJPhtnUzWze6jB4KXrKFqdZs9UnsfJP0ZZGvg8h2eHpe1iyd3YSDMaa8bQN4S7VrQ11B31zgK3bYBbtFOh6jLC1OilbDL8vq2jDPnPA6ABG4DNthEVjKqhMCIXsfV8lII1mpz7ixCshFTbMBK7XgfXiEjeefI5r6WEovcZvuUKKNSbXq2j4uvb45FK77KVblasNtjp8uYJnUURLsuH6J1gQzWvfoQFDkM3JSwhxics87ecZM5dYba3rPihw0DJjU2WlMKnARSsM8c3KUmTXnuSST471DjCPafmcIybM0zVXcZ04lxei12tdyyzunTHpprf1KX26wi3nvI8XzxSfwgwfaaHKMkmb4UBSrec42oz3x3JOWszL8gf3Slmf8FfUGarFxXid53xDSrs5ViaryEc09jCP95HLCJJ8S7ZSxCj3UTYyySWFSesckzJXJrhSQ37kGNHQ9d4CzgjnDAdRezBJKqdXSLtWrfI8TUpb96nGVTNdzpBR4Bit0YuJ2GuFVnjfd7jgCrQr4LZppmTVc1I7rXCl1HHSFDKVq3vOioBgBjEsHaHeKeLLYeqKNl2o1bHQ8cF94DjRmHwoTMR3ylfByP2md9XCmtlyZH56FL8IiOmmCwOqGbl0W6Ih5C3YVMwyVXpAwcGgpdqDS3D02SedJ99cIELLxITekeELzcwboKdt1bCEp3fd56XzyuTgjB4oxHfEosLnJEMrW453bJcv7xcy8vKpgdfndCFqwLtuHzPG7ipHvBt5f6dwmXod7itVcc8XeTigL2poFnAC7gxkioLfyms3pJj25jG2YRRD79oAYMLIy4fvm16X4bNpJcjV09WyITi9DUGLtH4sMSBGMbsFdmlEmXPWB1OKRfi3XGHZsiPwYxhJbXqNWWNn7YArkPrIAkrcfdrP8slEFuPZ4wfvJ6dCmRGf6Km5QU9xPpUGGEDDNQlwE4bDQw7IeDYmdurQbfjwTvr7R4ddqflUqYKu8x84r2EwQGhdsBQh56VqmFzYY2Mj3KNeQBXtaY0p1ZSN5Y2eBccZQfuPrkNTKpOZyilbbzjyIjwRBEpHC3sS2gAG1Is2In7MV08nxZEh7L2pArsBHQ7wVEF18LbsKb3PeHcMeydoFGbodsVkmXFDq2GBEbfltyrL9akiyxyGyJaZ5LRWQCGt1aaAVDFRsGVmJhjY22uf1dxeL0KcxjvJRovW6ieSY91Q2r4F6HRjIWYs8bkAu9ywCwFNqtGyVwBDz9Jn6K7dlVlO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "bJk5JqwBns16LDzXCIi5Wu4NaJH71mE01ijDhzEVqlKrQpaTdFPjUrTKE5InOTRTRc1845Ew6FHilUEyUAidvqj6tWOwVwFKYPcbGdLqjTHSasJBMnPcZkAIboRaGX6fPThG7l66dCpn4oOAftROUiGfi1UBW3XLT3Qz1yXRvlnrRz39PWFPCnJVzjVP3OD7Y4JmUs0SpXRJoXW5jZ5E3m8hBgObFXEGlMtJovuYdmvHzBb1OtXrFBBEHZOPwLv9m"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
