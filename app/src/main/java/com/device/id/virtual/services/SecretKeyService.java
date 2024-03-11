package com.device.id.virtual.services;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import com.device.id.virtual.constants.Constants;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;

public class SecretKeyService {

    private static final String TAG = "SecretKeyService";

    private static final String secretKey = "vpd-id-key-2354";

    private static void generateSecret() {

        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_HMAC_SHA256, Constants.KEY_GEN_PROVIDER_ANDROID_KEYSTORE);
            keyGenerator.init(
                    new KeyGenParameterSpec.Builder(secretKey, KeyProperties.PURPOSE_SIGN).build()
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
}
