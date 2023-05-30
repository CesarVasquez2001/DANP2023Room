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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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


    var test: List<CourseWithStudent>


    val courseWithStudentsOnClick: () -> Unit = {
        scope.launch {
            repository.getCourseWithStudents().forEach {
                Log.d(TAG, it.toString())
            }
        }
    }



    LazyColumn(
        contentPadding = PaddingValues(all = 20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),

        ) {


    }

}


@Composable
fun ListItemRow(courseWithStudent: CourseWithStudent) {
    var more = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                Text(
                    text = courseWithStudent.course.name,
                    style = MaterialTheme.typography.body2
                )
                if (more.value) {
                    Text(
                        text = "Monto pagado: " + courseWithStudent.students.toString()
                    )
                }

            }
            OutlinedButton(onClick = {
                /*TODO*/
                more.value = !more.value
            }) {

                Text(if (more.value) "Ocultar" else "Mas")
            }
        }

    }
}
