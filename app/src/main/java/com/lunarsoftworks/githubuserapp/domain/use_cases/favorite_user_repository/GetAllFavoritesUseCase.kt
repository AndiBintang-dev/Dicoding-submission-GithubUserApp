package com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.domain.repositories.FavoriteUserRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
private val repository: FavoriteUserRepository
) {

    operator fun invoke() : Flow<Resource<List<UsersListDto>>> = flow {
        try {
            emit( Resource.Loading<List<UsersListDto>>() )
            val users : List<UsersListDto> = repository.getAllFavorites()
            emit( Resource.Success<List<UsersListDto>>(users) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<List<UsersListDto>>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<List<UsersListDto>>("Couldn't reach server. Check your internet connection.") )
        }
    }


}