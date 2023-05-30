package com.example.danp2023room.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.danp2023room.Navigation.AppScreens
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.UserCourseCrossRef
import com.example.danp2023room.model.AppDatabase
import com.example.danp2023room.model.Repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = { Toolbar() },

        ) {
        val context = LocalContext.current
        val repository = Repository(AppDatabase.getInstance(context.applicationContext))

        com.example.danp2023room.Screens.RoomSample(repository,navController)

    }
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Home") }


    )
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RoomSample(repository: Repository,navController: NavController) {
    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillTables(repository, scope)
        }
        val studentsOnClick: () -> Unit = {
            scope.launch {
                repository.getAllStudents().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }
        val booksOnClick: () -> Unit = {
            scope.launch {
                repository.getAllCourses().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }
        val studentWithBooksOnClick: () -> Unit = {
            scope.launch {
                repository.getStudentWithCourses().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }
        val courseWithStudentsOnClick: () -> Unit = {
            scope.launch {
                repository.getCourseWithStudents().forEach {
                    Log.d(TAG, it.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = fillDataOnClick) {
            Text(text = "Fill student & book tables")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentsOnClick) {
            Text(text = "Show students")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = booksOnClick) {
            Text(text = "Show courses")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = studentWithBooksOnClick) {
            Text(text = "Student With Courses")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                    courseWithStudentsOnClick
                    navController.navigate(route = AppScreens.LIST.route)
            }

        ) {
            Text(text = "Courses With Students")
        }
    }
}

fun fillTables(rep: Repository, scope: CoroutineScope) {
    val students = listOf(
        StudentEntity(1, "Cesar Paul Vasquez Alvarez"),
        StudentEntity(2, "Diego Esteban Porto Cruz"),
        StudentEntity(3, "Samir Diego Castillo Martinez"),
        StudentEntity(4, "Gerald Hector Ramos Chalco")
    )
    val courses = listOf(
        CourseEntity(1, "Fundamentos de la programacion"),
        CourseEntity(2, "Programacion web"),
    )
    val studentCourseRelations = listOf(
        UserCourseCrossRef(1, 1),
        UserCourseCrossRef(2, 1),
        UserCourseCrossRef(3, 2),
        UserCourseCrossRef(4, 2),
    )
    scope.launch {
        rep.insertStudents(students)
        rep.insertCourses(courses)
        rep.insertStudentWithCourses(studentCourseRelations)
    }
}

