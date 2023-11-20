package com.anangkur.presentation.blur

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.anangkur.data.local.workmanager.BlurWorker
import com.anangkur.helper.worker.IMAGE_MANIPULATION_WORK_NAME
import javax.inject.Inject

class ApplyBlurUseCase @Inject constructor(
    private val workManager: WorkManager,
) {
    operator fun invoke(imageUri: Uri?) {
        workManager.beginUniqueWork(
            IMAGE_MANIPULATION_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<BlurWorker>()
                .setInputData(setInputDataForUri(imageUri))
                .addTag(com.anangkur.helper.worker.TAG_OUTPUT)
                .build()
        ).enqueue()
    }

    private fun setInputDataForUri(imageUri: Uri?): Data {
        return Data.Builder().apply {
            putString(com.anangkur.helper.worker.KEY_IMAGE_URI, imageUri?.toString())
        }.build()
    }

    fun getWorkManagerLiveData(): LiveData<List<WorkInfo>> {
        return workManager.getWorkInfosByTagLiveData(com.anangkur.helper.worker.TAG_OUTPUT)
    }
}