package com.boot.projectMgr.entry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.boot.projectMgr.createTask.CreateTaskScreen
import com.boot.projectMgr.ui.BootAndroidTheme

class ProjectManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //                    TimelineScreen()
                    CreateTaskScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BootAndroidTheme {
        Surface(color = MaterialTheme.colors.background) {

            TimelineScreen()
        }
    }
}