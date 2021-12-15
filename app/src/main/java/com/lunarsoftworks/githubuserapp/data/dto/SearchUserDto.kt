// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json          = Json(JsonConfiguration.Stable)
// val searchUserDAO = json.parse(SearchUserDAO.serializer(), jsonString)

package com.lunarsoftworks.githubuserapp.data.dto

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class SearchUserDto (
    @SerialName("total_count")
    val totalCount: Long,

    @SerialName("incomplete_results")
    val incompleteResults: Boolean,

    val items: List<UsersListDto>
)
