package com.lunarsoftworks.githubuserapp.domain.use_cases.theme_repository

import com.lunarsoftworks.githubuserapp.common.Resource
import com.lunarsoftworks.githubuserapp.domain.repositories.ThemeRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SetSelectedThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {

    operator fun invoke(themeValue : String) : Flow<Resource<String>> = flow {
        try {
            emit( Resource.Loading<String>() )
            val theme : String = repository.setSelectedTheme(themeValue = themeValue)
            emit( Resource.Success<String>(theme) )
        }
        catch (error: HttpRequestTimeoutException) {
            emit( Resource.Error<String>("An unexpected error occured.") )
        }
        catch (error: IOException) {
            emit( Resource.Error<String>("Couldn't reach server. Check your internet connection.") )
        }
    }

}