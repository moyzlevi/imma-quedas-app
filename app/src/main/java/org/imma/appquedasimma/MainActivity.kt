package org.imma.appquedasimma

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import org.imma.appquedasimma.dao.MainDatabase
import org.imma.appquedasimma.databinding.ActivityNavigationBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "MainActivity"
    }
    private lateinit var binding: ActivityNavigationBinding
    private lateinit var database: MainDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        database = Room.databaseBuilder(applicationContext, MainDatabase::class.java, "SQLITE_DATABASE")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()
        val users = database.roomDao().getAllFromDb()
        Log.i(TAG, "${users}")

        if (users.isEmpty()) {
            goToFirstPage()
        }
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun goToFirstPage() {
        val intent = Intent(this, FirstPage::class.java)
        startActivity(intent)
    }
}