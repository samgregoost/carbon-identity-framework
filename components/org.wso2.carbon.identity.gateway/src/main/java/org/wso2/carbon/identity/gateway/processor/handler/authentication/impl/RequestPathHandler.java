package org.wso2.carbon.identity.gateway.processor.handler.authentication.impl;

import org.wso2.carbon.identity.common.base.message.MessageContext;
import org.wso2.carbon.identity.gateway.common.model.idp.RequestPathAuthenticatorConfig;
import org.wso2.carbon.identity.gateway.context.AuthenticationContext;
import org.wso2.carbon.identity.gateway.processor.authenticator.RequestPathApplicationAuthenticator;
import org.wso2.carbon.identity.gateway.processor.handler.FrameworkHandler;
import org.wso2.carbon.identity.gateway.processor.handler.authentication.AuthenticationHandlerException;
import org.wso2.carbon.identity.gateway.processor.handler.authentication.impl.model.AbstractSequence;
import org.wso2.carbon.identity.gateway.processor.handler.authentication.impl.util.Utility;

import java.util.List;

public class RequestPathHandler extends FrameworkHandler {
    @Override
    public String getName() {
        return null;
    }

    public AuthenticationResponse handleRequestPathAuthentication(AuthenticationContext authenticationContext)
            throws AuthenticationHandlerException {
        AuthenticationResponse authenticationResponse = null;
        AbstractSequence sequence = authenticationContext.getSequence();

        List<RequestPathAuthenticatorConfig>
                requestPathAuthenticatorConfigs = sequence.getRequestPathAuthenticatorConfig();
        sequence.getRequestPathAuthenticatorConfig();
        for (RequestPathAuthenticatorConfig requestPathAuthenticatorConfig : requestPathAuthenticatorConfigs) {
            RequestPathApplicationAuthenticator requestPathApplicationAuthenticator =
                    Utility.getRequestPathApplicationAuthenticator(requestPathAuthenticatorConfig.getAuthenticatorName());
            if (requestPathApplicationAuthenticator.canHandle(authenticationContext)) {
                authenticationResponse = requestPathApplicationAuthenticator.process(authenticationContext);
            }
        }
        return authenticationResponse;
    }

    @Override
    public boolean canHandle(MessageContext messageContext) {
        return true ;
    }
}