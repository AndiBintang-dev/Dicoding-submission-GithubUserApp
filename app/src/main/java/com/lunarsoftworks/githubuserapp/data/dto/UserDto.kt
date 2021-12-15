// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json              = Json(JsonConfiguration.Stable)
// val getHotelDetailDAO = json.parse(GetHotelDetailDAO.serializer(), jsonString?)

package com.lunarsoftworks.githubuserapp.data.dto

import kotlinx.serialization.*

@Serializable
data class UserDto (
    val login: String? = "",
    val id: Long? = 0,

    @SerialName("node_id")
    val nodeID: String? = "",

    @SerialName("avatar_url")
    val avatarURL: String? = "",

    @SerialName("gravatar_id")
    val gravatarID: String? = "",

    val url: String? = "",

    @SerialName("html_url")
    val htmlURL: String? = "",
    @SerialName("followers_url")
    val followersURL: String? = "",

    @SerialName("following_url")
    val followingURL: String? = "",

    @SerialName("gists_url")
    val gistsURL: String? = "",

    @SerialName("starred_url")
    val starredURL: String? = "",

    @SerialName("subscriptions_url")
    val subscriptionsURL: String? = "",

    @SerialName("organizations_url")
    val organizationsURL: String? = "",

    @SerialName("repos_url")
    val reposURL: String? = "",

    @SerialName("events_url")
    val eventsURL: String? = "",

    @SerialName("received_events_url")
    val receivedEventsURL: String? = "",

    val type: String? = "",

    @SerialName("site_admin")
    val siteAdmin: Boolean? = true,

    val name: String? = "",
    val company: String? = "",
    val blog: String? = "",
    val location: String? = "",
    val email: String? = "",
    val hireable: Boolean? = false,
    val bio: String? = "",

    @SerialName("twitter_username")
    val twitterUsername: String? = "",

    @SerialName("public_repos")
    val publicRepos: Long? = 0,

    @SerialName("public_gists")
    val publicGists: Long? = 0,

    val followers: Long? = 0,
    val following: Long? = 0,

    @SerialName("created_at")
    val createdAt: String? = "",

    @SerialName("updated_at")
    val updatedAt: String? = "",

)
