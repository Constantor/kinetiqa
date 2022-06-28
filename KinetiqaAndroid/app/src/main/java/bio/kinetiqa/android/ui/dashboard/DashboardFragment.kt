package bio.kinetiqa.android.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bio.kinetiqa.android.*
import bio.kinetiqa.android.databinding.FragmentDashboardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


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

		updateList(root)

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	fun setSubstData() {
		substances = DataBase.getListOfSubstances()
	}

	fun updateList(root: View) {
		setSubstData()
		subList = root.findViewById(R.id.subList)
		val context : Context? = this.context
		if (!isAdded) { Log.e("check_activity", "WTF why everything is not working") }
		val substAdapter = SubstanceAdapter(activity, R.layout.list_item, substances)

		// TODO: вероятно поп-ап с подробным окном лекарства
		// 1 variant
		val itemListener =
			AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
				updateNewList(root)
			}
		subList.onItemClickListener = itemListener
		// 2 variant
		//TODO
/*		subList.onItemClickListener =
			OnItemClickListener { parent, v, position, id -> // по позиции получаем выбранный элемент
				updateNewList(root)
			}*/
		//TODO

		subList.adapter = substAdapter

		//

		val xe: FloatingActionButton = root.findViewById(R.id.floatingActionButton2)
		xe.setOnClickListener {
			updateNewList(root)
			val addIntent = Intent(context, AddSubActivity::class.java)
			startActivity(addIntent)
		}
/*		add_substance = root.findViewById(R.id.button_add_substance)
		add_substance.setOnClickListener {
			updateNewList(root)
			val addIntent = Intent(context, AddSubActivity::class.java)
			startActivity(addIntent)
		}*/
	}

	private fun updateNewList(root: View) {
		setSubstData()
		val desc1 = "Ибупрофен применяется как обезболивающее и противовоспалительное средство при лечении ревматоидного артрита, анкилозирующего спондилита, остеоартроза и других неревматоидных артропатий."
		substances.add(Substance("Нурофен", desc1, R.drawable.nurofen, 5))
		subList = root.findViewById(R.id.subList)
		val context : Context? = this.context
		if (!isAdded) { Log.e("check_activity", "WTF why everything is not working") }
		val substAdapter = SubstanceAdapter(activity, R.layout.list_item, substances)

		// TODO: вероятно поп-ап с подробным окном лекарства
		// 1 variant
		val itemListener =
			AdapterView.OnItemClickListener { parent, v, position, id -> // получаем выбранный пункт
				val graphIntent = Intent(context, GraphInfoActivity::class.java)
				startActivity(graphIntent)
			}
		//subList.onItemClickListener = itemListener
		// 2 variant
		subList.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
			val graphIntent = Intent(context, GraphInfoActivity::class.java)
			startActivity(graphIntent)
		})

		subList.adapter = substAdapter
	}
}