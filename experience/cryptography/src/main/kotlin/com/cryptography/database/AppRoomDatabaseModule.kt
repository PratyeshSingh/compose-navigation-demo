package com.cryptography.database

import android.content.Context
import androidx.room.Room
import com.cryptography.CryptoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.io.File
import java.io.FileInputStream
import javax.inject.Singleton

private const val DATABASE_NAME = "my_encrypted_database.db"
@Module
@InstallIn(SingletonComponent::class)
object AppRoomDatabaseModule {

//    @Provides
//    @Singleton
//    fun provideRoom(
//        @ApplicationContext applicationContext: Context
//    ): AppDB {
//        val builder = Room.databaseBuilder(
//            applicationContext,
//            AppDB::class.java,
//            "my_database.db"
//        )
//
//        val userPassphrase = charArrayOf('j', 'a', 'i') // Replace with your passphrase
//        val passphrase = SQLiteDatabase.getBytes(userPassphrase)
//        val state = SQLCipherUtils.getDatabaseState(applicationContext, DATABASE_NAME)
//
//
//        if (state == SQLCipherUtils.State.UNENCRYPTED) {
//            SQLCipherUtils.encrypt(
//                applicationContext,
//                DATABASE_NAME,
//                passphrase
//            )
//        }
//
//        val factory = SupportFactory(passphrase)
//
//
//        builder.openHelperFactory(factory) // Add this line to add encryption to the database
////        builder.addMigrations(*MIGRATIONS.invoke())
//
//
//
//        return builder.build()
//    }

    @Provides
    @Singleton
    fun provideRoom(
        @ApplicationContext applicationContext: Context,
        cryptoManager: CryptoManager
    ): AppDB {
//        val userPassphrase = charArrayOf('j', 'a', 'i') // Replace with your passphrase

        val file = File(applicationContext.filesDir, "secret.txt")
        val userPassphrase = cryptoManager.decrypt(
            inputStream = FileInputStream(file)
        ).decodeToString().toCharArray()

        val passphrase = SQLiteDatabase.getBytes(userPassphrase)

        val factory = SupportFactory(passphrase)


        val builder = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java, DATABASE_NAME
        )
        builder.openHelperFactory(factory)

//        builder.fallbackToDestructiveMigration()
//        builder.addMigrations(*MIGRATIONS.invoke())

        return builder.build()
    }

}