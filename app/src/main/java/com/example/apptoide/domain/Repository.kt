package com.example.apptoide.domain

import com.example.apptoide.data.remote.ServiceATI
import com.example.apptoide.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class Repository @Inject constructor(
    private val remoteDataSource: ServiceATI
) {
    suspend fun getAllData(loadedList: List<com.example.apptoide.data.structure.List>): Flow<Resource<List<com.example.apptoide.data.structure.List>>> =
        flow {
            emit(Resource.Loading(loadedList))
            try {
                val response = remoteDataSource.getAllNews()
                if (response.isSuccessful)
                    emit(Resource.Success(loadedList.plus(response.body()!!.responses.listApps.datasets.all.data.list)))
                else
                    emit(Resource.Error(response.code().toString()))
            } catch (e: HttpException) {
                emit(Resource.Error("Could not load data"))
            } catch (e: IOException) {
                emit(Resource.Error("Check internet"))
            }
        }
}
