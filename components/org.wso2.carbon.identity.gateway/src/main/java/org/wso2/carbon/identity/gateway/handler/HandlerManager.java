/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.gateway.handler;


import org.slf4j.Logger;
import org.wso2.carbon.identity.gateway.api.exception.GatewayException;
import org.wso2.carbon.identity.gateway.api.exception.GatewayRuntimeException;
import org.wso2.carbon.identity.gateway.api.context.GatewayMessageContext;
import org.wso2.carbon.identity.gateway.context.AuthenticationContext;
import org.wso2.carbon.identity.gateway.internal.GatewayServiceHolder;
import org.wso2.carbon.identity.gateway.handler.authentication.AuthenticationHandler;
import org.wso2.carbon.identity.gateway.handler.validator.AbstractRequestValidator;
import org.wso2.carbon.identity.gateway.handler.response.AbstractResponseHandler;
import org.wso2.carbon.identity.gateway.handler.session.AbstractSessionHandler;

import java.util.List;

public class HandlerManager {


    private static Logger log = org.slf4j.LoggerFactory.getLogger(HandlerManager.class);
    private static volatile HandlerManager instance = new HandlerManager();

    private HandlerManager() {

    }
    public static HandlerManager getInstance() {
        return instance;
    }

    /**
     * Get Authentication Handler.
     *
     * @param messageContext
     * @return
     */
    public AuthenticationHandler getAuthenticationHandler(GatewayMessageContext messageContext) {
        List<AuthenticationHandler> authenticationHandlers =
                GatewayServiceHolder.getInstance().getAuthenticationHandlers();
        if(authenticationHandlers != null) {
            for (AuthenticationHandler authenticationHandler : authenticationHandlers) {
                try {
                    if (authenticationHandler.canHandle(messageContext)) {
                        return authenticationHandler;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get Authentication Handler. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Cannot find AuthenticationHandler to handle this request.");
    }


    /**
     * Get Response Handler.
     *
     * @param authenticationContext
     * @return
     */
    public AbstractResponseHandler getResponseHandler(AuthenticationContext authenticationContext) {

        List<AbstractResponseHandler> responseBuilderHandlers =
                GatewayServiceHolder.getInstance().getResponseHandlers();
        if(responseBuilderHandlers != null) {
            for (AbstractResponseHandler responseBuilderHandler : responseBuilderHandlers) {
                try {
                    if (responseBuilderHandler.canHandle(authenticationContext)) {
                        return responseBuilderHandler;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get response handler. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Can not find a ResponseHandler to handle this request.");
    }


    /**
     * Get Response Handler.
     *
     * @param authenticationContext
     * @return
     */
    public AbstractResponseHandler getResponseHandler(AuthenticationContext authenticationContext, GatewayException e) {

        List<AbstractResponseHandler> responseBuilderHandlers =
                GatewayServiceHolder.getInstance().getResponseHandlers();
        if(responseBuilderHandlers != null) {
            for (AbstractResponseHandler responseBuilderHandler : responseBuilderHandlers) {
                try {
                    if (responseBuilderHandler.canHandle(authenticationContext, e)) {
                        return responseBuilderHandler;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get response handler. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Can not find a ResponseHandler to handle this request.");
    }


    /**
     * Get Response Handler.
     *
     * @param authenticationContext
     * @return
     */
    public AbstractResponseHandler getResponseHandler(AuthenticationContext authenticationContext, GatewayRuntimeException e) {

        List<AbstractResponseHandler> responseBuilderHandlers =
                GatewayServiceHolder.getInstance().getResponseHandlers();
        if(responseBuilderHandlers != null) {
            for (AbstractResponseHandler responseBuilderHandler : responseBuilderHandlers) {
                try {
                    if (responseBuilderHandler.canHandle(authenticationContext, e)) {
                        return responseBuilderHandler;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get response handler. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Can not find a ResponseHandler to handle this request.");
    }


    /**
     * Get RequestValidator.
     *
     * @param messageContext
     * @return
     */
    public AbstractRequestValidator getRequestValidator(GatewayMessageContext messageContext) {

        List<AbstractRequestValidator> requestHandlers =
                GatewayServiceHolder.getInstance().getRequestHandlers();
        if(requestHandlers != null) {
            for (AbstractRequestValidator requestValidator : requestHandlers) {
                try {
                    if (requestValidator.canHandle(messageContext)) {
                        return requestValidator;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get request validator. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Can not find AbstractRequestValidator to handle this request.");
    }

    /**
     * Get RequestValidator.
     *
     * @param messageContext
     * @return
     */
    public AbstractSessionHandler getSessionHandler(GatewayMessageContext messageContext) {

        List<AbstractSessionHandler> sessionHandlers =
                GatewayServiceHolder.getInstance().getSessionHandlers();
        if(sessionHandlers != null) {
            for (AbstractSessionHandler sessionHandler  : sessionHandlers) {
                try {
                    if (sessionHandler.canHandle(messageContext)) {
                        return sessionHandler;
                    }
                } catch (Throwable throwable) {
                    String errorMessage = "Error occurred while calling can handle to get session validator. " +
                                          throwable.getMessage() ;
                    log.error(errorMessage);
                }
            }
        }
        throw new GatewayRuntimeException("Can not find AbstractSessionHandler to handle this request.");
    }


}