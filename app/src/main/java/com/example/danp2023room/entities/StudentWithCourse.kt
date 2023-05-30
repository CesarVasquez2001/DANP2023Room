package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentWithCourse(
    @Embedded val student: StudentEntity,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(UserCourseCrossRef::class)
    )
    val courses: List<CourseEntity>

)