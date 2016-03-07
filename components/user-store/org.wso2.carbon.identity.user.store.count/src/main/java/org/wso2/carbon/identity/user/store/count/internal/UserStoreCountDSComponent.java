/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.identity.user.store.count.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * @scr.component name="org.wso2.carbon.identity.user.store.configuration.component" immediate="true"
 * @scr.reference name="user.realmservice.default"
 * interface="org.wso2.carbon.user.core.service.RealmService"
 * cardinality="1..1" policy="dynamic" bind="setRealmService"
 * unbind="unsetRealmService"
 */
public class UserStoreCountDSComponent {
    private static Log log = LogFactory.getLog(UserStoreCountDSComponent.class);
    private static RealmService realmService = null;
    private static RealmConfiguration realmConfiguration = null;

    public UserStoreCountDSComponent() {
    }

    public static RealmService getRealmService() {
        return realmService;
    }

    protected void setRealmService(RealmService realmService) {
        UserStoreCountDSComponent.realmService = realmService;
        if (log.isDebugEnabled()) {
            log.debug("Set the Realm Service");
        }
    }

    public static RealmConfiguration getRealmConfiguration() {
        realmConfiguration = UserStoreCountDSComponent.getRealmService().getBootstrapRealmConfiguration();
        return realmConfiguration;
    }

    /**
     * @param ctxt
     */
    protected void activate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("Identity User Store bundle is activated.");
        }
        try {
        } catch (Throwable e) {
            log.error("Failed to load user store org.wso2.carbon.identity.user.store.configuration details.", e);
        }
        if (log.isDebugEnabled()) {
            log.debug("Identity User Store-Config bundle is activated.");

        }
    }

    /**
     * @param ctxt
     */
    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("Identity User Store-Config bundle is deactivated");
        }
    }

    protected void unsetRealmService(RealmService realmService) {
        UserStoreCountDSComponent.realmService = null;
        if (log.isDebugEnabled()) {
            log.debug("Unset the Realm Service");
        }
    }


}

