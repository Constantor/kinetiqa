package bio.kinetiqa.android.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import bio.kinetiqa.android.*
import bio.kinetiqa.android.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

	private lateinit var dashboardViewModel: DashboardViewModel
	private var _binding: FragmentDashboardBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	var substances : MutableList<Substance> = ArrayList()
	lateinit var subList : ListView
	lateinit var add_substance : Button

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

		_binding = FragmentDashboardBinding.inflate(inflater, container, false)
		val root: View = binding.root

		//val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
		setSubstData()
		subList = root.findViewById(R.id.subList)
		val context : Context? = this.context
		if (!isAdded) { Log.e("check_activity", "WTF why everything is not working") }
		val substAdapter = SubstanceAdapter(activity, R.layout.list_item, substances)
		subList.adapter = substAdapter

		/// TODO: вероятно поп-ап с подробным окном лекарства
				val itemListener =
					AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
						val selectedState: Substance =
							parent.getItemAtPosition(position) as Substance
						Toast.makeText(
							activity?.applicationContext, "Был выбран пункт " + selectedState.name,
							Toast.LENGTH_SHORT
						).show()
					}
		subList.setOnItemClickListener(itemListener)
		///

		add_substance = root.findViewById(R.id.button_add_substance)
		//add_substance.setOnClickListener();

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	fun setSubstData() {
		substances = DataBase.getListOfSubstances()
	}
}