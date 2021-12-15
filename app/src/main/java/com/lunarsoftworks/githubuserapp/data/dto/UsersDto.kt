// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json     = Json(JsonConfiguration.Stable)
// val usersDto = json.parse(UsersDto.serializer(), jsonString)

package com.lunarsoftworks.githubuserapp.data.dto

import kotlinx.serialization.*

@Serializable
data class UsersListDto (
    val login: String,
    val id: Long,

    @SerialName("node_id")
    val nodeID: String,

    @SerialName("avatar_url")
    val avatarURL: String,

    @SerialName("gravatar_id")
    val gravatarID: String,

    val url: String,

    @SerialName("html_url")
    val htmlURL: String,

    @SerialName("followers_url")
    val followersURL: String,

    @SerialName("following_url")
    val followingURL: String,

    @SerialName("gists_url")
    val gistsURL: String,

    @SerialName("starred_url")
    val starredURL: String,

    @SerialName("subscriptions_url")
    val subscriptionsURL: String,

    @SerialName("organizations_url")
    val organizationsURL: String,

    @SerialName("repos_url")
    val reposURL: String,

    @SerialName("events_url")
    val eventsURL: String,

    @SerialName("received_events_url")
    val receivedEventsURL: String,

    val type: String,

    @SerialName("site_admin")
    val siteAdmin: Boolean
)
