/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDTO;

/**
 *
 * @author winnh
 */
public class ClientDTO {
    private String clientID;
    private String clientname;

    public ClientDTO(String clientID, String clientname) {
        this.clientID = clientID;
        this.clientname = clientname;
    }

    public ClientDTO() {
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
    
    
}
