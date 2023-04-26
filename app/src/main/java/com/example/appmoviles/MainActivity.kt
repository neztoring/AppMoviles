package com.example.appmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appmoviles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(com.example.appmoviles.R.layout.activity_performer_list)

        // Get the navigation host fragment from this Activity
        //val navHostFragment = supportFragmentManager
            //.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        //navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        //Log.d("act", navController.toString())
        //setSupportActionBar(findViewById(R.id.my_toolbar))
        //setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

/*class MainActivity : AppCompatActivity() {

    lateinit var networkServiceAdapter: NetworkServiceAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_performer_list)

        networkServiceAdapter = NetworkServiceAdapter(this.applicationContext)

        //val getButton: Button = findViewById(R.id.volver)
        //val getResultTextView : TextView = findViewById(R.id.get_result_text)

        /*volleyBroker.instance.add(VolleyBroker.getRequest("musicians",
            { response ->
                // Display the first 500 characters of the response string.
                getResultTextView.text = "Response is: ${response}"
            },
            {
                Log.d("TAG", it.toString())
                getResultTextView.text = "That didn't work!"
            }
        ))*/
    }
}*/