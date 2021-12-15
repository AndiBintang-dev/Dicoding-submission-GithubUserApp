package com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.domain.repositories.FavoriteUserRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteUserRepository
) {

    operator fun invoke(userId : Long) : Flow<Resource<Boolean>> = flow {
        try {
            emit( Resource.Loading<Boolean>() )
            val exist : Boolean = repository.isFavorite(userId = userId)
            emit( Resource.Success<Boolean>(exist) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<Boolean>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<Boolean>("Couldn't reach server. Check your internet connection.") )
        }
    }


}