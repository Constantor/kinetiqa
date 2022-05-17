package bio.kinetiqa.android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bio.kinetiqa.android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	var substances : MutableList<Substance> = ArrayList()
	lateinit var subList : ListView
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val navView: BottomNavigationView = binding.navView

		val navController = findNavController(R.id.nav_host_fragment_activity_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		var appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
			)
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

		// Not working sheet

		val host: NavHostFragment = supportFragmentManager
			.findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
		val navLeftController = host.navController

		val sideBar = findViewById<NavigationView>(R.id.nav_left_view)
		sideBar?.setupWithNavController(navLeftController)


/*		appBarConfiguration = AppBarConfiguration(navController.graph
			, drawerLayout = drawer_layout) // для бокового меню
		val toolBar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolBar) // для верхнего меню
		toolBar.setupWithNavController(navController, appBarConfiguration)*/
	}
}