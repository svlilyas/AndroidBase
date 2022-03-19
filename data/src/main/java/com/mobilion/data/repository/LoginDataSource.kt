package com.mobilion.data.repository

import com.mobilion.data.remote.ProjectService
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val projectService: ProjectService
) {
    fun fetchLogin(
        gsm: String,
        password: String
    ) = projectService.fetchLogin(
        gsm = gsm,
        password = password
    )
}