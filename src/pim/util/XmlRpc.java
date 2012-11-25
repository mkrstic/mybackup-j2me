/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pim.util;
import org.kxmlrpc.XmlRpcClient;


/**
 *
 * @author mladen
 */
public class XmlRpc {
    private static XmlRpc instance;
    private XmlRpcClient rpc;

    private XmlRpc() {
        //rpc = new XmlRpcClient("http://free.hostingjava.it/-mycontacts/xmlrpc");
        rpc = new XmlRpcClient("http://localhost:8080/mybackup_hibernate/xmlrpc");
    }


    public static XmlRpc get() {
        if (instance == null) {
            instance = new XmlRpc();
        }
        return instance;
    }
    public XmlRpcClient getRpc() {
        return rpc;
    }

}
