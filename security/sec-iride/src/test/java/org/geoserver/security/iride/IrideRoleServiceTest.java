/*
 *  GeoServer Security Provider plugin with which doing authentication and authorization operations using CSI-Piemonte IRIDE Service.
 *  Copyright (C) 2016  Regione Piemonte (www.regione.piemonte.it)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.geoserver.security.iride;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.SortedSet;

import org.geoserver.config.GeoServerDataDirectory;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geoserver.security.GeoServerSecurityManager;
import org.geoserver.security.impl.GeoServerRole;
import org.geoserver.security.iride.config.IrideRoleServiceConfig;
import org.geoserver.security.iride.util.factory.security.IrideAuthenticationProviderFactory;
import org.geoserver.security.iride.util.factory.security.IrideRoleServiceFactory;
import org.geoserver.security.iride.util.factory.security.IrideUserGroupServiceFactory;
import org.geoserver.security.iride.util.logging.LoggerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * <code>GeoServer</code> roles security service, backed by <a href="http://www.csipiemonte.it/">CSI</a> <code>IRIDE</code> service <code>JUnit</code> Test.
 *
 * @author "Simone Cornacchia - seancrow76@gmail.com, simone.cornacchia@consulenti.csi.it (CSI:71740)"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/testContext.xml",
})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public final class IrideRoleServiceTest {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerProvider.getLogger(IrideRoleServiceTest.class);

    private static final String SAMPLE_USER_WITH_NO_ROLES = "AAAAAA00A11M000U/CSI PIEMONTE/DEMO 32/IPA/20161027103359/2/uQ4hHIMEEruA6DGThS3EuA==";

    /**
     * A "dummy" {@link GeoServerRole}.
     */
    private static final GeoServerRole DUMMY_ROLE = new GeoServerRole("dummy");

    private File tempFolder;

    /**
     * Factory that creates a new, configured, {@link IrideAuthenticationProviderFactory} instance.
     */
    @Autowired
    private IrideAuthenticationProviderFactory irideAuthenticationProviderFactory;

    /**
     * Factory that creates a new, configured, {@link IrideRoleService} instance.
     */
    @Autowired
    private IrideRoleServiceFactory irideRoleServiceFactory;

    /**
     * Factory that creates a new, configured, {@link IrideRoleService} instance.
     */
    @Autowired
    private IrideUserGroupServiceFactory irideUserGroupServiceFactory;

    private IrideSecurityProvider securityProvider;

    private IrideRoleServiceConfig config;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.tempFolder = File.createTempFile("iride", "test");
        this.tempFolder.delete();
        this.tempFolder.mkdirs();

        this.irideRoleServiceFactory.setSecurityManager(
            new GeoServerSecurityManager(
                new GeoServerDataDirectory(
                    new GeoServerResourceLoader(this.tempFolder)
                )
            )
        );
        this.securityProvider = new IrideSecurityProvider(
            this.irideAuthenticationProviderFactory,
            this.irideRoleServiceFactory,
            this.irideUserGroupServiceFactory
        );

        this.config = new IrideRoleServiceConfig();
        this.config.setName("iride");
        this.config.setClassName(IrideRoleService.class.getName());
        this.config.setServerURL("http://local-applogic-nmsf2e.csi.it/pep_wsfad_nmsf_policy/services/PolicyEnforcerBase");
        this.config.setApplicationName("DECSIRA");
        this.config.setAdminRole("SUPUSR_DECSIRA");
        this.config.setFallbackRoleService("default");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        this.tempFolder.delete();
    }

    /**
     * Test method for {@link org.geoserver.security.iride.IrideRoleService#canCreateStore()}.
     *
     * @throws IOException
     */
    @Test
    public void testCannotCreateStore() throws IOException {
    	LOGGER.trace("BEGIN {}::testCannotCreateStore", this.getClass().getName());

        assertThat(false, is(this.createRoleService().canCreateStore()));

        LOGGER.trace("END {}::testCannotCreateStore", this.getClass().getName());
    }

    /**
     * Test method for {@link org.geoserver.security.iride.IrideRoleService#canCreateStore()}.
     *
     * @throws IOException
     */
    @Test
    public void testCreateStoreReturnsNull() throws IOException {
    	LOGGER.trace("BEGIN {}::testCreateStoreReturnsNull", this.getClass().getName());

        assertThat(this.createRoleService().createStore(), is(nullValue()));

        LOGGER.trace("END {}::testCreateStoreReturnsNull", this.getClass().getName());
    }

    /**
     * Test method for {@link org.geoserver.security.iride.IrideRoleService#getRolesForUser(java.lang.String)}.
     *
     * @throws IOException
     */
    @Test(expected = IllegalStateException.class)
    public void testGetRolesForSampleUserWithInvalidServerURL() throws IOException {
        LOGGER.trace("BEGIN {}::testGetRolesForSampleUserWithInvalidServerURL", this.getClass().getName(), new Object[] { SAMPLE_USER_WITH_NO_ROLES, this.config });

        this.config.setServerURL(null);

        try {
            this.createRoleService().getRolesForUser(SAMPLE_USER_WITH_NO_ROLES);
        } finally {
        	LOGGER.trace("END {}::testGetRolesForSampleUserWithInvalidServerURL", this.getClass().getName());
        }
    }

    /**
     * Test method for {@link org.geoserver.security.iride.IrideRoleService#getRolesForUser(java.lang.String)}.
     *
     * @throws IOException
     */
    @Test
    public void testGetRolesForSampleUserWithNoRoles() throws IOException {
    	LOGGER.trace("BEGIN {}::testGetRolesForSampleUserWithNoRoles", this.getClass().getName(), new Object[] { SAMPLE_USER_WITH_NO_ROLES, this.config });

        try {
            final SortedSet<GeoServerRole> roles = this.createRoleService().getRolesForUser(SAMPLE_USER_WITH_NO_ROLES);

            assertThat(roles, not(nullValue()));
            assertThat(roles.size(), is(0));
        } finally {
        	LOGGER.trace("END {}::testGetRolesForSampleUserWithNoRoles", this.getClass().getName());
        }
    }

    /**
     * Test method for {@link org.geoserver.security.iride.IrideRoleService#getRolesForUser(java.lang.String)}.
     *
     * @throws IOException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetRolesForSampleUserNotModifiable() throws IOException {
    	LOGGER.trace("BEGIN {}::testGetRolesForSampleUserNotModifiable", this.getClass().getName(), new Object[] { SAMPLE_USER_WITH_NO_ROLES, this.config });

        try {
            final SortedSet<GeoServerRole> roles = this.createRoleService().getRolesForUser(SAMPLE_USER_WITH_NO_ROLES);

            assertThat(roles, not(nullValue()));
            assertThat(roles.size(), is(0));

            // throws UnsupportedOperationException
            roles.add(DUMMY_ROLE);
        } finally {
        	LOGGER.trace("END {}::testGetRolesForSampleUserNotModifiable", this.getClass().getName());
        }
    }

    /**
     * @param string
     * @return
     * @throws IOException
     */
    private IrideRoleService createRoleService() throws IOException {
        return (IrideRoleService) this.securityProvider.createRoleService(this.config);
    }

}
