package com.device.id.virtual.models;

import com.device.id.virtual.constants.Constants;

import java.nio.charset.StandardCharsets;

public class CreateDeviceIdReq {

    private String Email;
    private String Phone;
    private int DerivationMode;

    public CreateDeviceIdReq(String email, String phone, int derivationMode) {
        this.Email = email;
        this.Phone = phone;
        this.DerivationMode = derivationMode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getDerivationMode() {
        return DerivationMode;
    }

    public void setDerivationMode(int derivationMode) {
        DerivationMode = derivationMode;
    }

    public byte[] GetData() {

        String dataStr;
        if(this.DerivationMode == Constants.ID_DERIVATION_MODE_EMAIL) {
            dataStr = this.Email;
        } else {
            dataStr = this.Phone;
        }

        return dataStr.getBytes(StandardCharsets.UTF_8);
    }
}
