package com.example.backgroundtask.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.backgroundtask.utility.NotificationHelper.showNotification
import com.example.backgroundtask.utility.Utility.DELAY_TIMING
import com.example.backgroundtask.utility.Utility.OUTPUT_PATH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File

class CleanUpWorker(context: Context, parameter: WorkerParameters):CoroutineWorker(context, parameter) {
    val TAG = CleanUpWorker::class.java.simpleName
    override suspend fun doWork(): Result {
        showNotification("Clean up Done", applicationContext)

        return withContext(Dispatchers.IO){
            delay(DELAY_TIMING)
            return@withContext try {
                val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
                if (outputDirectory.exists()) {
                    val entries = outputDirectory.listFiles()
                    if (entries != null) {
                        for (entry in entries) {
                            val name = entry.name
                            if (name.isNotEmpty() && name.endsWith(".png")) {
                                val deleted = entry.delete()
                                Log.i(TAG, "Deleted $name - $deleted")
                            }
                        }
                    }
                }
                Result.success()
            } catch (exception:Exception){
                Log.e(
                    TAG,
                    "",
                    exception
                )
                Result.failure()
            }
        }
    }

}