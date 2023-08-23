#Silverpeas-Spnego-Undertow

This project depends on the [Silverpeas-Spnego](http://github.com/Silverpeas/Silverpeas-Spnego)
project.
It allows to install dynamically, at Silverpeas's starting, the HTTP Filter which handles the SSO
authentication with Kerberos / SPNEGO protocol.

This library must be put into WEB-INF/lib folder of the application server, which is working
with [Undertow](http://undertow.io), in order to be taken into account.