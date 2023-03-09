package com.papirus.androidbase.core.model.utils

class AppConstants {
    companion object {
        const val COST_1: Float = 5.0F
        const val COST_2: Float = 8.0F
        const val COST_3: Float = 10.0F

        /**
         * DATE FORMATS
         */
        const val DATE_DEFAULT: String = "dd/MM/yyyy"

        /**
         * NUMBERS
         */
        const val ZERO_INT: Int = 0
        const val ONE_INT: Int = 1
        const val TWO_INT: Int = 2
        const val THREE_INT: Int = 3

        /**
         * STRINGS
         */
        const val STRING_EMPTY: String = ""

        /**
         * DATABASE CONFIGS
         */
        const val KEYSET_NAME = "master_keyset"
        const val PREFERENCE_FILE = "master_key_preference"
        const val MASTER_KEY_URI = "android-keystore://master_key"

        const val DATASTORE_FILE = "app_db.preferences_pb"
        const val ENCRYPTION_TYPE = "AES256_GCM"

    }
}