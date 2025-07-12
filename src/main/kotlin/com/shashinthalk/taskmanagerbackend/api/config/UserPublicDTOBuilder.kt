package com.shashinthalk.taskmanagerbackend.api.config

import com.shashinthalk.taskmanagerbackend.api.dto.UserPublicDTO
import com.shashinthalk.taskmanagerbackend.data.model.User
import com.shashinthalk.taskmanagerbackend.data.repository.UserProfileRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserPublicDTOBuilder(
    private val userProfileRepository: UserProfileRepository,
) {
    fun buildUserPublicDTO(user: User): Mono<out UserPublicDTO> {
        return userProfileRepository.findByUserId(user.id.toInt()).flatMap { userProfile ->
            Mono.just(
                UserPublicDTO(
                    fname = userProfile.fname,
                    lname = userProfile.lname,
                    email = user.email,
                    phone = userProfile.phone,
                    address = userProfile.address,
                    city = userProfile.city,
                    state = userProfile.state,
                    zip = userProfile.zip,
                    country = userProfile.country,
                    website = userProfile.website,
                    linkedin = userProfile.linkedin,
                    twitter = userProfile.twitter,
                    facebook = userProfile.facebook,
                    instagram = userProfile.instagram,
                    youtube = userProfile.youtube,
                    tiktok = userProfile.tiktok,
                    pinterest = userProfile.pinterest,
                    github = userProfile.github,
                    gitlab = userProfile.gitlab,
                    bitbucket = userProfile.bitbucket,
                    isActive = user.isActive,
                    role = user.role,
                    createdAt = user.createdAt,
                    updatedAt = user.updatedAt,
                ),
            )
        }.switchIfEmpty(
            Mono.just(
                UserPublicDTO(
                    email = user.email,
                    isActive = user.isActive,
                    role = user.role,
                    createdAt = user.createdAt,
                    updatedAt = user.updatedAt,
                ),
            ),
        )
    }
}
