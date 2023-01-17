package com.corewillsoft.template.oss_so_configurator

import com.corewillsoft.template.oss_so_configurator.cache.AppDatabase
import com.corewillsoft.template.osssoconfigurator.cache.Rocket
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML

interface Repository {

    companion object {
        fun create(driverFactory: DriverFactory): Repository = RepositoryImpl(driverFactory)
    }

    fun insertRocket(name: String): Flow<Unit>
    fun removeAllRockets(): Flow<Unit>
    fun getAllRockets(): Flow<List<Rocket>>
    fun generateAllRocketsXml(): Flow<String>

    interface DriverFactory {
        fun createDriver(): SqlDriver
    }
}

internal class RepositoryImpl(private val driverFactory: Repository.DriverFactory) : Repository {

    private val database by lazy { AppDatabase(driverFactory.createDriver()) }
    private val xmlGenerator by lazy {
        XML {
            autoPolymorphic = true
        }
    }

    override fun insertRocket(name: String) = flow {
        emit(database.appDatabaseQueries.insertRocket(name))
    }

    override fun removeAllRockets() = flow {
        emit(database.appDatabaseQueries.removeAllRockets())
    }

    override fun getAllRockets(): Flow<List<Rocket>> =
        database.appDatabaseQueries.getAllRockets().asFlow().mapToList()

    override fun generateAllRocketsXml(): Flow<String> = getAllRockets()
        .map { it.map { XmlRocket(name = it.name) } }
        .map { xmlGenerator.encodeToString(it) }


    @Serializable
    private data class XmlRocket(val name: String)
}



