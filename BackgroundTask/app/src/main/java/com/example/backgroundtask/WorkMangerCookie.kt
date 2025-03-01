package com.example.backgroundtask

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.convertTo
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.backgroundtask.utility.Utility.KEY_BLUR_LEVEL
import com.example.backgroundtask.utility.Utility.KEY_IMAGE_URL
import com.example.backgroundtask.workManager.BlurAmount
import com.example.backgroundtask.workManager.BlurWork
import com.example.backgroundtask.workManager.CleanUpWorker
import com.example.backgroundtask.workManager.SaveImageToFileWorker

@Composable
fun WorkMangerCookie() {

    val blurList = arrayListOf<BlurAmount>()
    blurList.add(BlurAmount("A little Blur", 1))
    blurList.add(BlurAmount("Medium Blur", 2))
    blurList.add(BlurAmount("Most Blur", 3))
    val context = LocalContext.current
    val workManager = WorkManager.getInstance(context)
    var selectedValue by remember { mutableStateOf(1) }

    Box(Modifier.background(Color.White)) {
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.height(56.dp))
            Image(
                painter = painterResource(R.drawable.cookie_img),
                contentDescription = "",
                alignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            )

            Text(
                "Select Blur Amount",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            blurList.forEach { list ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            role = Role.RadioButton,
                            selected = selectedValue == list.blurValue,
                            onClick = {
                                selectedValue = list.blurValue
                            }
                        )
                        .size(56.dp)
                ) {

                    RadioButton(
                        selected = selectedValue == list.blurValue,
                        onClick = null,
                        modifier = Modifier.size(48.dp)
                    )

                    Text(text = list.amountBlur)
                }
            }

            Button(
                onClick = {
                    blurTask(selectedValue, workManager, context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start")
            }

        }
    }
}

fun blurTask(blurLevel: Int, workManager: WorkManager, context: Context) {
    var continuation =
        workManager.beginWith(OneTimeWorkRequest.Companion.from(CleanUpWorker::class.java))

    val blurBuilder = OneTimeWorkRequestBuilder<BlurWork>()

    blurBuilder.setInputData(createInputDataForWorkManagerRequest(blurLevel, getImageUrl(context)))

    continuation = continuation.then(blurBuilder.build())

    val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>().build()
    continuation = continuation.then(save)
    continuation.enqueue()

}

fun createInputDataForWorkManagerRequest(blurLevel: Int, imageUri: Uri): Data {
    val builder = Data.Builder()
    builder.putString(KEY_IMAGE_URL, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
    return builder.build()
}

fun getImageUrl(context: Context): Uri {
    val resource = context.resources
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(resource.getResourcePackageName(R.drawable.cookie_img))
        .appendPath(resource.getResourceTypeName(R.drawable.cookie_img))
        .appendPath(resource.getResourceEntryName(R.drawable.cookie_img))
        .build()
}




















