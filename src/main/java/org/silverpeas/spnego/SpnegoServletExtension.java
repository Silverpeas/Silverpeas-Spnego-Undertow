package org.silverpeas.spnego;

import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.FilterInfo;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import java.util.StringTokenizer;

/**
 * This implementation of {@link ServletExtension} the Silverpeas HTTP Filter which performs SSO
 * authentications by using KERBEROS and SPNEGO protocols.
 * @author Yohann Chastagnier
 */
public class SpnegoServletExtension implements ServletExtension {

  public void handleDeployment(final DeploymentInfo deploymentInfo,
      final ServletContext servletContext) {

    // Adding the filter
    deploymentInfo.addFilter(new FilterInfo("SpnegoHttpFilter", SpnegoHttpFilter.class)
        .addInitParam("spnego.throw.typedRuntimeException", "true")
        .addInitParam("spnego.allow.basic", "false")
        .addInitParam("spnego.allow.localhost", "false")
        .addInitParam("spnego.allow.unsecure.basic", "false")
        .addInitParam("spnego.login.client.module", "spnego-client")
        .addInitParam("spnego.krb5.conf", "krb5.conf")
        .addInitParam("spnego.login.conf", "login.conf")
        .addInitParam("spnego.login.server.module", "spnego-server")
        .addInitParam("spnego.prompt.ntlm", "false")
        .addInitParam("spnego.logger.level", System.getProperty("spnego.logger.level", "3")));

    // Specifying the url filter mapping
    StringTokenizer urlMappingTokenizer =
        new StringTokenizer(System.getProperty("spnego.filter.mapping.url", "/sso"), ",");
    while (urlMappingTokenizer.hasMoreTokens()) {
      deploymentInfo.addFilterUrlMapping("SpnegoHttpFilter", urlMappingTokenizer.nextToken(),
          DispatcherType.REQUEST);
    }
  }
}
