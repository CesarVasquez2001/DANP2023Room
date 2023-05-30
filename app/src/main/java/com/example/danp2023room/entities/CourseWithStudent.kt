package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudent(
    @Embedded val course: CourseEntity,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(UserCourseCrossRef::class)
    )
    val students: List<StudentEntity>

)