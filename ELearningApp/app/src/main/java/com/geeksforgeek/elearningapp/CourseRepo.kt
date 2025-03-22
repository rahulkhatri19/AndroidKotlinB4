package com.geeksforgeek.elearningapp

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.geeksforgeek.elearningapp.model.CourseModel
import com.geeksforgeek.elearningapp.model.ExploreModel
import com.geeksforgeek.elearningapp.model.MyCourseModel
import com.geeksforgeek.elearningapp.model.ProfileModel
import org.json.JSONObject

class CourseRepo {

    fun getJsonData(context: Context): JSONObject {
        val jsonData =
            context.assets.open("course_data.json").bufferedReader().use { it.readText() }
        return JSONObject(jsonData)
    }

    fun getHomeCourseData(jsonObject: JSONObject): ArrayList<CourseModel> {
        val courseList = arrayListOf<CourseModel>()
        val jsonArray = jsonObject.getJSONArray("courseList")
        for (i in 0..<jsonArray.length()) {
            val courseData = jsonArray.getJSONObject(i)
            println("Home Course Data" + jsonArray[i])
            courseList.add(
                CourseModel(
                    courseData.optString("courseImage"),
                    courseData.optString("courseTitle"),
                    courseData.optString("description"),
                    courseData.optString("rating"),
                    courseData.optString("numberOfGeeks"),
                )
            )
        }
        return courseList
    }

    fun getExploreData(jsonObject: JSONObject): ArrayList<ExploreModel> {
        val exploreList = arrayListOf<ExploreModel>()
        val jsonArray = jsonObject.getJSONArray("exploreList")
        for (i in 0..<jsonArray.length()) {
            val courseData = jsonArray.getJSONObject(i)
            val colorList = arrayListOf<Color>()
            for (j in 0..<courseData.getJSONArray("colorList").length()) {
                val colorHex =
                    android.graphics.Color.parseColor(courseData.getJSONArray("colorList")[j].toString())
                colorList.add(Color(colorHex))
            }
            exploreList.add(
                ExploreModel(
                    colorList,
                    courseData.optString("title"),
                    courseData.optString("description"),
                )
            )
            println("Home Explore Data" + jsonArray[i] + "::colorlist:${colorList}")
        }
        return exploreList
    }

    fun myCoursesData(jsonObject: JSONObject): ArrayList<MyCourseModel> {
        val myCourseList = arrayListOf<MyCourseModel>()
        val jsonArray = jsonObject.getJSONArray("myCoursesList")
        for (i in 0..<jsonArray.length()) {
            println("Home My Courses Data" + jsonArray[i])
            val courseData = jsonArray.getJSONObject(i)
            myCourseList.add(
                MyCourseModel(
                    courseData.optString("courseImage"),
                    courseData.optString("courseTitle"),
                    courseData.getBoolean("isPaid"),
                )
            )
        }
        return myCourseList
    }

    fun profileData(jsonObject: JSONObject): ProfileModel {
        val profileData = jsonObject.getJSONObject("profileData")
        println("Home profile Data :: $profileData")
        return ProfileModel(
            profileData.optString("userImage"),
            profileData.optString("userName"),
            profileData.optString("userHandle"),
            profileData.optString("userCollege"),
            profileData.optString("followers"),
            profileData.optString("following"),
            profileData.optString("articles"),
        )
    }
}