package com.example.danp2023room.Screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseWithStudent
import com.example.danp2023room.model.AppDatabase
import com.example.danp2023room.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun List(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Lista de asistentes")
        }
    }) {
        val context = LocalContext.current
        val repository = Repository(AppDatabase.getInstance(context.applicationContext))

        Greeting5(navController, repository)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting5(navController: NavController, repository: Repository) {

    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()
    val coursestudents = remember { mutableStateOf<List<CourseWithStudent>?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            val courses = repository.getCourseWithStudents()
            coursestudents.value = courses
        }
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (coursestudents.value != null) {
            Courses(coursestudents = coursestudents.value!!)
        }
    }


}
@Composable
fun Courses(coursestudents: List<CourseWithStudent>) {
    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp)) {
        items(coursestudents) { courseStudent ->
            Spacer(modifier = Modifier.height(10.dp))
            Card(backgroundColor = Color.Blue) {
                Box(Modifier.padding(10.dp)) {
                    Text(text = courseStudent.course.name)
                    Column(Modifier.padding(top = 26.dp)) {
                        courseStudent.students.map { student ->
                            Text(text = student.fullname)
                        }
                    }
                }
            }
        }
    }
}



