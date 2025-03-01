package com.example.backgroundtask.workManager

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.backgroundtask.utility.NotificationHelper.showNotification
import com.example.backgroundtask.utility.Utility.DELAY_TIMING
import com.example.backgroundtask.utility.Utility.KEY_IMAGE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import android.net.Uri
import androidx.work.workDataOf
import android.provider.MediaStore
import java.util.Date


class SaveImageToFileWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {
    val TAG = SaveImageToFileWorker::class.java.simpleName
        private val title = "Blurred Image"
    private val dateFormatter = SimpleDateFormat(
        "yyyy.MM.dd 'at' HH:mm:ss z",
        Locale.getDefault()
    )

    override suspend fun doWork(): Result {
        showNotification("Saving Image", applicationContext)

        return withContext(Dispatchers.IO) {
            delay(DELAY_TIMING)

            val resolver = applicationContext.contentResolver

            return@withContext try {
                val imageUri = inputData.getString(KEY_IMAGE_URL)
                val bitmap = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(imageUri))
                )
                val imageUrl = MediaStore.Images.Media.insertImage(
                    resolver, bitmap, title, dateFormatter.format(Date())
                )
                if (!imageUrl.isNullOrEmpty()) {
                    val output = workDataOf(KEY_IMAGE_URL to imageUrl)

                    Result.success(output)
                } else {
                    Log.e(
                        TAG,
                        "Unable to store image to media"
                    )
                    Result.failure()
                }
            } catch (exception: Exception) {
                Log.e(
                    TAG,
                    "Error in saving image",
                    exception
                )
                Result.failure()
            }
        }
    }
}