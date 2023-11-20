package com.anangkur.data.local.workmanager

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.anangkur.helper.worker.KEY_IMAGE_URI
import com.anangkur.helper.worker.blurBitmap
import com.anangkur.helper.worker.makeStatusNotification
import com.anangkur.helper.worker.writeBitmapToFile

class BlurWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        makeStatusNotification("Blurring image", context)

        return try {
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            val picture = BitmapFactory.decodeStream(
                context.contentResolver.openInputStream(Uri.parse(resourceUri))
            )
            val output = blurBitmap(picture, context)
            val outputUri = writeBitmapToFile(context, output)

            makeStatusNotification("Output is $outputUri", context)

            Result.success(workDataOf(KEY_IMAGE_URI to outputUri.toString()))
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }
}