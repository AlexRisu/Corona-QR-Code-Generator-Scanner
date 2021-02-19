package com.example.coronaqrcodegenerator.models.persistence

import android.provider.BaseColumns

class PersonEntity {
    object PersonEntry : BaseColumns {
        const val TABLE_NAME = "persons"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PHONE_NUMBER = "phone_number"
    }
}