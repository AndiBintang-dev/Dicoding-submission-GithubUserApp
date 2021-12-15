package com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.UserDto
import com.lunarsoftworks.githubuserapp.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: GithubRepository
) {

    operator fun invoke(username : String) : Flow<Resource<UserDto>> = flow {
        try {
            emit( Resource.Loading<UserDto>() )
            val users = repository.getUserDetails(username = username)
            emit( Resource.Success<UserDto>(users) )
        }
        catch (error: ClassCastException) {
            emit( Resource.Error<UserDto>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<UserDto>("Couldn't reach server. Check your internet connection.") )
        }
    }

}