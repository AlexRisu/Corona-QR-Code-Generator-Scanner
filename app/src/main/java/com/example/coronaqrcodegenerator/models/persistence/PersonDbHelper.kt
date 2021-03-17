package com.example.coronaqrcodegenerator.models.persistence

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.coronaqrcodegenerator.models.Person

class PersonDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Companion.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(Companion.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertAll(people: List<Person>) {
        var personsToPersist = people
        for (persistedPerson in findAll()) {
            personsToPersist =
                personsToPersist.filter { personToProve: Any -> personToProve != persistedPerson }
        }
        for (personToPersist in personsToPersist) {
            insertSingle(personToPersist)
        }
    }

    fun insertSingle(person: Person) {
        val contentValues = ContentValues()
        contentValues.put(PersonEntity.PersonEntry.COLUMN_NAME_NAME, person.name)
        contentValues.put(PersonEntity.PersonEntry.COLUMN_NAME_PHONE_NUMBER, person.phoneNumber)

        this.writableDatabase.insert(PersonEntity.PersonEntry.TABLE_NAME, null, contentValues)
    }

    fun findAll(): List<Person> {
        val personList = ArrayList<Person>()
        try {
            val cursor = this.readableDatabase.rawQuery(Companion.SQL_FIND_ALL_ENTRIES, null)
            if (cursor.moveToFirst()) {
                do {
                    val person = Person(
                        name = cursor.getString(cursor.getColumnIndex(PersonEntity.PersonEntry.COLUMN_NAME_NAME)),
                        phoneNumber = cursor.getString(cursor.getColumnIndex(PersonEntity.PersonEntry.COLUMN_NAME_PHONE_NUMBER))
                    )
                    personList.add(person)
                } while (cursor.moveToNext())
            }
        } catch (error: SQLiteException) {
            Log.e(TAG, "findAll: while trying to exec query", error)
        }
        return personList;
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "CoronaChecker.db"
        private const val SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS ${PersonEntity.PersonEntry.TABLE_NAME}"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${PersonEntity.PersonEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${PersonEntity.PersonEntry.COLUMN_NAME_NAME} TEXT," +
                    "${PersonEntity.PersonEntry.COLUMN_NAME_PHONE_NUMBER} TEXT)"
        const val SQL_FIND_ALL_ENTRIES = "SELECT * FROM ${PersonEntity.PersonEntry.TABLE_NAME}"
    }
}