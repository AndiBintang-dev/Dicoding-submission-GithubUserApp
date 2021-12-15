package com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.domain.repositories.FavoriteUserRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class InsertFavoriteUseCase @Inject constructor(
    private val repository: FavoriteUserRepository
) {

    operator fun invoke(user : UsersListDto) : Flow<Resource<Int>> = flow {
        try {
            emit( Resource.Loading<Int>() )
            val status : Int = repository.insertFavorite(user)
            emit( Resource.Success<Int>(status) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<Int>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<Int>("Couldn't reach server. Check your internet connection.") )
        }
    }


}