/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanKN.userDTO;

import java.io.Serializable;

/**
 *
 * @author winnh
 */
public class CheckOutOrderError implements Serializable{
    private String clientnameLengthError;

    public String getClientnameLengthError() {
        return clientnameLengthError;
    }

    public void setClientnameLengthError(String clientnameLengthError) {
        this.clientnameLengthError = clientnameLengthError;
    }
    
}
