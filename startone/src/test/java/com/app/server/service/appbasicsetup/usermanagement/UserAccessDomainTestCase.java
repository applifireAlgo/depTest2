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
        useraccessdomain.setDomainHelp("BiB99jbxEmh3qb9fshSojLj1CGMHYgVzAqogu6rFKWodLoNJGl");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("xbtji2T0pmo06asDwNABmnDpoJPNAA7d6DaIarjSvk8Q4Ynfdw");
        useraccessdomain.setDomainDescription("Et7GcoHPOOogvCVN3Bbi8P0uWHpXagK4ukBmFPTYJwqvThq2dE");
        useraccessdomain.setDomainIcon("ZZgh0TjOsTAV8d0WMiHgW8Oiit2ONcuCxPS2tkahG3RO1Q9Pg0");
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
            useraccessdomain.setDomainHelp("2qUH2QqRarwIIUmkMndZXG8z5RP6C2UUSTtdMxTbL4veMBHqjW");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setUserAccessDomain(5711);
            useraccessdomain.setDomainName("ITZ1cMyZCei7qkWhxSGOSv9xsQagZFb4ghHzZ9Z7QRLofkuLQF");
            useraccessdomain.setDomainDescription("5wLZDltVPfjzmhHyFGIDCsDN1kEM9DAEXfOXcrsw2oK1XvgBvI");
            useraccessdomain.setDomainIcon("2h19ZrByDTPD6uauOOOPFHhIwT1sJiWwpBydHmpDclSn0eWohG");
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
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 173133));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "6aoz5mQ3isRkB9habXeEaGcUQ41XlNb56L1Dil4HsMq8k4FOUwWaTrvhr5Ro3erdOzGQnEPrcnXPWp4nyiwuoAlTCJTrSSDK5IgXpFDk0L28Jt767xFjwEwaqoP1I9OwNMtziMbmCE8Z4xnDDTdbnxQHkjXLZA6IBzO8y4xZJsmlKXPDPuTcctYSCVlbqMOcXgslwDc1o3KWTyAtuLZn6YKjRMUIdFsq9ZvlDRKOrImGm5KuOuhQ3h2FakhYXubs4"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "ey1TIcbIdGl5bDNjJvKT5nEoh3KT2iaVmUM1fnQCfGLcmK6BBv3x54ESLDW9QafgNPy01CdTktJCx3atSXzSzO5FLD69isXpnxaCReIpcchWYjRgXzXeq7n6KxNcW7kD9MDbxEvi8jQzWEdGEJrWLEqQY3HJm6kIxMmeWbSuQPdXm56l1vsS0AUsHMdKhu30NR6wzGgnA7J8eiAUclCrQskcP3bSdYZE8fytQeFRKXG7MMSP1vii0e6x0gAfmYT1w"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "7rVPhj452qnHKOqqUBnBZxMyhyDhWqcZ7Rn8suFUvCVMvPzxtbTJwmBXoS3XCJJa3IaWegIv6sEVqomd5yuDMLVvaDLLG6qIEGuI8VOpVWnbyFtnEsisCUA5osVzeElGPgkCoXms9RBBJqM3J0RhD4cHjZCCXVZ0vLRBe81SUutGhcsOdfct9cloixd9Xh8cUfHLYMw7KHJVUW9wIsyUaY3Sg7o7OCQptwnnWoixsmDwUf5Yjfpv16Fnnhag0BvFp"));
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
