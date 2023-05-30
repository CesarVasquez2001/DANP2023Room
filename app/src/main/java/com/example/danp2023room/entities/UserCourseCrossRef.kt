package com.example.danp2023room.entities

import androidx.room.Entity


@Entity(primaryKeys = ["studentId", "courseId"])
data class UserCourseCrossRef(
    val studentId: Int,
    val courseId: Int
)