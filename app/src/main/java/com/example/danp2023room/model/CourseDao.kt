package com.example.danp2023room.model

import androidx.room.*
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseWithStudent

@Dao
interface CourseDao {
    @Query("select * from course")
    suspend fun getAll(): List<CourseEntity>

    @Insert
    suspend fun insert(courseEntity: CourseEntity)

    @Insert
    suspend fun insert(coursesEntity: List<CourseEntity>)

    @Delete
    suspend fun delete(courseEntity: CourseEntity)

    @Transaction
    @Query("select * from course")
    suspend fun getCoursesWithStudents(): List<CourseWithStudent>

}