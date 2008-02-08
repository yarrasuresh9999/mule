/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.test.integration.management;

import org.mule.management.agents.JmxAgent;
import org.mule.api.MuleException;
import org.mule.tck.FunctionalTestCase;

public class ManagementStartupTestCase extends FunctionalTestCase
{

    protected String getConfigResources()
    {
        return "org/mule/test/integration/management/management-startup-test.xml";
    }

    public void testAgentConfiguration() throws MuleException
    {
        JmxAgent agent = (JmxAgent)muleContext.getRegistry().lookupAgent("jmxAgent");
         assertNotNull(agent);
        //TODO RM* Add this back in. Currently failing because of a JMX issue where the AllStatistics MBean is registered twice
        assertNotNull(agent.getConnectorServerUrl());
        assertEquals("service:jmx:rmi:///jndi/rmi://localhost:1100/server", agent.getConnectorServerUrl());
        assertNotNull(agent.getConnectorServerProperties());
        assertEquals("true", agent.getConnectorServerProperties().get("jmx.remote.jndi.rebind"));
    }

}