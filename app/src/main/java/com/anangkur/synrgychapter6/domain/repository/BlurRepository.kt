package com.anangkur.synrgychapter6.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface BlurRepository {
    suspend fun saveProfilePhoto(profilePhoto: String)
    fun applyBlur(imageUri: Uri?)
    fun getWorkManagerLiveData(): LiveData<List<WorkInfo>>
}