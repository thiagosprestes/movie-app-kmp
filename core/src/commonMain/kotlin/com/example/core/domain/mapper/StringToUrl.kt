package com.example.core.domain.mapper

import com.example.core.utils.PATH_BASE_URL

fun String?.toUrl() = "${PATH_BASE_URL}$this"