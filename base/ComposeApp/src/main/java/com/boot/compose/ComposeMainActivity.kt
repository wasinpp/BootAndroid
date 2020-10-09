package com.boot.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.boot.compose.databinding.ComposeMainBinding

class ComposeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ComposeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = binding.navHost.getNavCtrl(this)
        if (savedInstanceState == null && navController != null) {
            navController.navigate(R.id.movie)
            finish()
        }
    }
}

private fun FragmentContainerView.getNavCtrl(activity: AppCompatActivity): NavController? {
    val navHostFragment = activity.supportFragmentManager.findFragmentById(id) as? NavHostFragment
    return navHostFragment?.navController
}