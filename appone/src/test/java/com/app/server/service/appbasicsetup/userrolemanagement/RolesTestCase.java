package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleIcon("hCanlx7bvQxd3yx7iWlSx7fiZSAoqzSlkmS5Rf9tmjVTxMiivN");
        roles.setRoleName("zjoh37zEiGpJpnZIIhRW7EoS2DjcfzjFR6AdG8oR0HKhMsHgp6");
        roles.setRoleHelp("5zd0ziLJufgPAf0j6SgdM4N9KoXUbuJMIZfTVFHRklH36kLmO2");
        roles.setRoleDescription("6lHrOnnR2XHzDT9BdOgIQGj3qdsG1rX4YzMrWvkTDCxF27UiMe");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setRefObjectId("z95Pk34PYsf7wS01lIcAc5cGPJnriXghtN9IKeziCYRtmZSRa8");
        appmenus.setUiType("GbP");
        appmenus.setAutoSave(true);
        appmenus.setMenuHead(true);
        appmenus.setMenuCommands("ogZ9kjoqDpJfY8EOC7wBPc85FM1VVlgGNNvWchdlJUnZsGO7j6");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuTreeId("5nfHNVPqozz05mgJS2GYop0qrf3KmXGmEurHWM28sLsGPllMzG");
        appmenus.setAppId("1WnC4rZrDCQrmT6hTL8LFZpUIUTiP5JulWx8isIv4hTtejjmTQ");
        appmenus.setMenuLabel("POvFzg7mXk0oEgw9NgiZfW00AszLJFf02RRqWfjJO88xF1m3sI");
        appmenus.setAppType(1);
        appmenus.setMenuAction("DoOFerJnaz4zbsExuAq1wMxWJSedZla2955k2zGcaj5sPrztdH");
        appmenus.setMenuAccessRights(3);
        appmenus.setMenuIcon("BrRoyZQNpIrhdX9x2fXUKLrSbgJre0leeeOmDQLRnyY7gi2wA2");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsExecute(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleIcon("HCy8tF4Pvz9jbR8XasnPI5UPKsR2zKc3OEAjlQRQGr8MqimczD");
            roles.setRoleName("pbCzt8h5vixdebZbjIEaUCUBVxOXFneOFu8M4oO4pI5x92Yfof");
            roles.setRoleHelp("FVNcaScJO8skf6MdVtpzLWfRQnVm2xBmKs98afVraCDBBexrI8");
            roles.setVersionId(1);
            roles.setRoleDescription("bHHOspBt66YirPQpOKYcBc1phJDEQzAipuc7mDjZ5iGjOcnZ1A");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "XpBn1palz5bOcqPez6i21zqsO9glBP8BvCJoTkyUnFLAvqpCCsVeY5eZGFJq3NxqxONoWuSO5K1sEyIonTXQBFBwmPDHDntumcJcToSX32TRT0ga4WCT3X3U06OaPcfJ2e9y5m5NRRJBzKApTDV2UAFXglZ3Etdbnsjb5OKfrySmer29jOdh94gMAxn1Fg1MTMgSCO98XPfJPJuXQV854hYj8RMKFGQgKfVRBm0p9f1m2CBHoMPh0KjJvNO6jmOyy"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "A4XTidOWxq3iU83PxmrR7EsIOvoVKxoD3rj1rp73amQgs7EEBew0zXwD3NzZ5WsA4qzSznSGSM2XUyx3PWt0Cl4l1skEFWFWyrsUMHq8aTMzVARc3ukacISANpD8pVULZCbmURf7Ikvy25R55DUVpiC6C5htW1HeOwa07PfHuLzByXa8OPCaoC4ksolcztvkGvGPKWPJA479ErfY4oEOfmtcggDjSL9ZLgoLRP7H7kSfYAc3kTdsZP1jLfatwLk8s"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "OiRsivIBlHkDEdCpryJ8C2bdNzJJzubfFF1Db58g6GqzB3CL720qbFo2AF4TD9I8i"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "IiwhqhVYVp087XHN2GkMKMT9Gc42p6LLYYfP5LN5g62vf4J5sPWluB0auhGfK9IJBAZBf4soVDWykcryeeUVBxo2A6G1vayH9Vq10aSxl58w7vk7HVMveysYW22pHvXcauyXYMxB9WzKdNzzUdarkzyCmCCVa8UTF67kxQSJRBOkM2WA0OafYQh9GfgJOb4AY7bXI7A9I4OE0EgEjtY5SaPH99KYpoyn5px2ehtpuMk5hamGMbHG0xKUIK5w1g3kd"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
