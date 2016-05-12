package org.wso2.carbon.identity.application.authentication.framework.inbound.processor.handler.extension;

import org.wso2.carbon.identity.application.authentication.framework.inbound.FrameworkHandlerResponse;
import org.wso2.carbon.identity.application.authentication.framework.inbound.IdentityMessageContext;
import org.wso2.carbon.identity.application.authentication.framework.inbound.processor.handler.FrameworkHandler;
import org.wso2.carbon.identity.application.authentication.framework.inbound.processor.handler
        .FrameworkHandlerException;

public abstract class AbstractPreHandler extends FrameworkHandler {

    private ExtensionHandlerPoints extensionHandlerPoints;

    protected AbstractPreHandler(ExtensionHandlerPoints extensionHandlerPoints) {
        this.extensionHandlerPoints = extensionHandlerPoints;
    }

    public ExtensionHandlerPoints getExtensionHandlerPoints() {
        return extensionHandlerPoints;
    }

    public abstract FrameworkHandlerResponse handle(IdentityMessageContext identityMessageContext)
            throws FrameworkHandlerException;
}