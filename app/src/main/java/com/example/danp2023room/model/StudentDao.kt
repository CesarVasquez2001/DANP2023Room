package com.example.danp2023room.model

import androidx.room.*
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.StudentWithCourse
import com.example.danp2023room.entities.UserCourseCrossRef

@Dao
interface StudentDao {
    @Query("select * from student")
    suspend fun getAll(): List<StudentEntity>

    @Insert
    suspend fun insert(studentEntity: StudentEntity)

    @Insert
    suspend fun insert(studentsEntity: List<StudentEntity>)

    @Delete
    suspend fun delete(studentEntity: StudentEntity)

    @Transaction
    @Query("select * from student")
    suspend fun getStudentWithCourses(): List<StudentWithCourse>

    @Insert
    suspend fun insertUserCourseCrossRef(crossRef: List<UserCourseCrossRef>)


}