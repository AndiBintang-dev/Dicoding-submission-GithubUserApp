package com.lunarsoftworks.githubuserapp.domain.use_cases.github_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.data.dto.SearchUserDto
import com.lunarsoftworks.githubuserapp.domain.repositories.GithubRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val repository: GithubRepository
) {

    operator fun invoke(username : String) : Flow<Resource<SearchUserDto>> = flow {
        try {
            emit( Resource.Loading<SearchUserDto>() )
            val users : SearchUserDto = repository.searchUser(username = username)
            emit( Resource.Success<SearchUserDto>(users) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<SearchUserDto>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<SearchUserDto>("Couldn't reach server. Check your internet connection.") )
        }
    }

}