package com.device.id.virtual.services;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.util.Log;

import com.device.id.virtual.constants.Constants;
import com.device.id.virtual.models.CreateDeviceIdReq;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

import javax.crypto.Mac;

public class CryptoService {

    private static final String TAG = "CryptoService";

    private static final KeyStore keyStore = null;

    private static void InitializeKeyStore() {
        try {
            KeyStore.getInstance(Constants.KEY_GEN_PROVIDER_ANDROID_KEYSTORE);
        } catch (KeyStoreException e) {
            Log.e(TAG, "Error initializing keystore", e);
        }
    }

    private String getMacAddress(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }

    public static byte[] GenerateVirtualId(CreateDeviceIdReq req) {

        Key key = SecretKeyService.getSecret();

        byte[] signature = null;
        byte[] id = null;

        try {

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            id = mac.doFinal(req.GetData());


//            Signature s = Signature.getInstance("SHA256withECDSA");
//            s.initSign(((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
//            s.update(req.GetData());
//
//            System.out.println("DATA: " + Arrays.toString();
//
//            signature = s.sign();
//
//            System.out.println("SIGNATURE: " + Arrays.toString(signature));
//
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.reset();
//            md.update(signature);
//            id = md.digest();
//
//            System.out.println("ID: " + Arrays.toString(id));

        } catch (NoSuchAlgorithmException ex1) {
            Log.e(TAG, "Can't find algorithm HmacSHA256", ex1);
        } catch (InvalidKeyException ex2) {
            Log.e(TAG, "Invalid key from PrivateKeyEntry", ex2);
        }
//        catch(SignatureException ex3) {
//            Log.e(TAG, "Error updating data", ex3);
//        }

        return id;
    }
}
