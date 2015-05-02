/**
 * EchoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.echo_jws;

public class EchoServiceLocator extends org.apache.axis.client.Service implements localhost.axis.echo_jws.EchoService {

    public EchoServiceLocator() {
    }


    public EchoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EchoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for echo
    private java.lang.String echo_address = "http://localhost:8080/axis/echo.jws";

    public java.lang.String getechoAddress() {
        return echo_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String echoWSDDServiceName = "echo";

    public java.lang.String getechoWSDDServiceName() {
        return echoWSDDServiceName;
    }

    public void setechoWSDDServiceName(java.lang.String name) {
        echoWSDDServiceName = name;
    }

    public localhost.axis.echo_jws.Echo getecho() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(echo_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getecho(endpoint);
    }

    public localhost.axis.echo_jws.Echo getecho(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.axis.echo_jws.EchoSoapBindingStub _stub = new localhost.axis.echo_jws.EchoSoapBindingStub(portAddress, this);
            _stub.setPortName(getechoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setechoEndpointAddress(java.lang.String address) {
        echo_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.axis.echo_jws.Echo.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.axis.echo_jws.EchoSoapBindingStub _stub = new localhost.axis.echo_jws.EchoSoapBindingStub(new java.net.URL(echo_address), this);
                _stub.setPortName(getechoWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("echo".equals(inputPortName)) {
            return getecho();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/axis/echo.jws", "echoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost:8080/axis/echo.jws", "echo"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("echo".equals(portName)) {
            setechoEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
