package com.example.danp2023room.model

import com.example.danp2023room.entities.*

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllStudents(): List<StudentEntity> {
        return appDatabase.studentDao().getAll()
    }

    suspend fun getStudentWithCourses(): List<StudentWithCourse> {
        return appDatabase.studentDao().getStudentWithCourses()
    }

    suspend fun getCourseWithStudents(): List<CourseWithStudent> {
        return appDatabase.courseDao().getCoursesWithStudents()
    }

    suspend fun insertStudents(studentsEntity: List<StudentEntity>) {
        appDatabase.studentDao().insert(studentsEntity)
    }

    suspend fun insertCourses(courses: List<CourseEntity>) {
        appDatabase.courseDao().insert(courses)
    }

    suspend fun insertStudentWithCourses(crossRef: List<UserCourseCrossRef>) {
        appDatabase.studentDao().insertUserCourseCrossRef(crossRef)
    }

    suspend fun insertCourse(course: CourseEntity) {
        appDatabase.courseDao().insert(course)
    }

    suspend fun getAllCourses(): List<CourseEntity> {
        return appDatabase.courseDao().getAll()
    }
}