package com.lunarsoftworks.githubuserapp.domain.use_cases.favorite_user_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UsersListDto
import com.lunarsoftworks.githubuserapp.domain.repositories.FavoriteUserRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteUserRepository
) {

    operator fun invoke(user : UsersListDto) : Flow<Resource<UsersListDto>> = flow {
        try {
            emit( Resource.Loading<UsersListDto>() )
            val status : Int = repository.delete(user)
            emit( Resource.Success<UsersListDto>(user) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<UsersListDto>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<UsersListDto>("Couldn't reach server. Check your internet connection.") )
        }
    }


}