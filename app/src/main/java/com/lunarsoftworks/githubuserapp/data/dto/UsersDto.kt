package com.lunarsoftworks.githubuserapp.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lunarsoftworks.githubuserapp.common.Constant
import kotlinx.serialization.*

@Serializable
@Entity(tableName = Constant.favoriteUserTableName)
data class UsersListDto (

    @PrimaryKey val id: Long,

    @ColumnInfo val login: String,

    @SerialName("node_id")
    @ColumnInfo val nodeID: String,

    @SerialName("avatar_url")
    @ColumnInfo val avatarURL: String,

    @SerialName("gravatar_id")
    @ColumnInfo val gravatarID: String,

    @ColumnInfo val url: String,

    @SerialName("html_url")
    @ColumnInfo val htmlURL: String,

    @SerialName("followers_url")
    @ColumnInfo val followersURL: String,

    @SerialName("following_url")
    @ColumnInfo val followingURL: String,

    @SerialName("gists_url")
    @ColumnInfo val gistsURL: String,

    @SerialName("starred_url")
    @ColumnInfo val starredURL: String,

    @SerialName("subscriptions_url")
    @ColumnInfo val subscriptionsURL: String,

    @SerialName("organizations_url")
    @ColumnInfo val organizationsURL: String,

    @SerialName("repos_url")
    @ColumnInfo val reposURL: String,

    @SerialName("events_url")
    @ColumnInfo val eventsURL: String,

    @SerialName("received_events_url")
    @ColumnInfo val receivedEventsURL: String,

    @ColumnInfo val type: String,

    @SerialName("site_admin")
    @ColumnInfo val siteAdmin: Boolean

)
