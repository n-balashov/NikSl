package com.pavesid.niksl.data

import com.pavesid.niksl.data.local.LocalSource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : DataRepository
