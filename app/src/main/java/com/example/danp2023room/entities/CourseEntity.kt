package com.example.danp2023room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity (
    @PrimaryKey(autoGenerate = true)
    val courseId:Int,
    val name:String,
)
{
    constructor(name: String) : this(0, name)
}