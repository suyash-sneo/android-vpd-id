package com.device.id.virtual.services;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import com.device.id.virtual.constants.Constants;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;

public class SecretKeyService {

    private static final String TAG = "SecretKeyService";

    private static final String secretKey = "vpd-id-key-2354";

    private static void generateSecret() {

        System.out.println("GENERATING SECRET");

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_HMAC_SHA256, Constants.KEY_GEN_PROVIDER_ANDROID_KEYSTORE);
            keyGenerator.init(
                    new KeyGenParameterSpec.Builder(secretKey, KeyProperties.PURPOSE_SIGN).
                            setDigests(KeyProperties.DIGEST_SHA256).
                            build()
            );
            keyGenerator.generateKey();

        } catch (NoSuchAlgorithmException ex1) {
            Log.e(TAG, "Error getting the algorithm for key generator", ex1);
        } catch (NoSuchProviderException ex2) {
            Log.e(TAG, "Error getting the provider for key generator", ex2);
        } catch (InvalidAlgorithmParameterException ex3) {
            Log.e(TAG, "Error initializing key generator", ex3);
        } catch (Exception ex) {
            Log.e(TAG, "Unexpected exception while generating key", ex);
        }
    }

    public static Key getSecret() {

        Key key = null;

        try {
            KeyStore keyStore = KeyStore.getInstance(Constants.KEY_GEN_PROVIDER_ANDROID_KEYSTORE);
            keyStore.load(null);

            if(!keyStore.containsAlias(secretKey)) {
                generateSecret();
            }

            key = keyStore.getKey(secretKey, null);

        } catch (KeyStoreException e) {
            Log.e(TAG, "Error getting keystore instance", e);
        } catch (IllegalArgumentException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            Log.e(TAG, "Error loading keystore", ex);
        } catch (NullPointerException | UnrecoverableEntryException ex2) {
            Log.e(TAG, "Error getting Entry", ex2);
        }

        return key;
    }
}
