package bio.kinetiqa.android

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import bio.kinetiqa.android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding
	var substances : MutableList<Substance> = ArrayList()
	lateinit var subList : ListView
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var settingsButton: Button

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
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.action_bar, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		val intent = Intent(this, SettingsActivity::class.java)
		startActivity(intent)
		return true
	}

	fun addSubstance(view: View) {
		val subIntent = Intent(this, AddSubActivity::class.java)
		startActivity(subIntent)
	}

}