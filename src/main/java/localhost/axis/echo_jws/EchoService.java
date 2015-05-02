/**
 * EchoService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.echo_jws;

public interface EchoService extends javax.xml.rpc.Service {
    public java.lang.String getechoAddress();

    public localhost.axis.echo_jws.Echo getecho() throws javax.xml.rpc.ServiceException;

    public localhost.axis.echo_jws.Echo getecho(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
