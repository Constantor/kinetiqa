package bio.kinetiqa.android

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val navView: BottomNavigationView = binding.navView

		val navController = findNavController(R.id.nav_host_fragment_activity_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		val appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
			)
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

		// some unfinished sheet

/*		setContentView(R.layout.fragment_dashboard)

	    setSubstData()// TODO: хардкод
		subList = findViewById(R.id.subList)
		if (subList == null) { Log.e("sublist", "sublist is null")}
		val substAdapter = SubstanceAdapter(this, R.layout.list_item, substances)
		subList.adapter = substAdapter*/

/*		val itemListener =
			OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
				val selectedState: Substance = parent.getItemAtPosition(position) as Substance
				Toast.makeText(
					applicationContext, "Был выбран пункт " + selectedState.name,
					Toast.LENGTH_SHORT
				).show()
			}

		subList.setOnItemClickListener(itemListener)*/
	}

	fun setSubstData() {
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
	}
}