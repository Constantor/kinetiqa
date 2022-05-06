package bio.kinetiqa.android.ui.dashboard

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bio.kinetiqa.android.R
import bio.kinetiqa.android.Substance
import bio.kinetiqa.android.SubstanceAdapter
import bio.kinetiqa.android.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

	private lateinit var dashboardViewModel: DashboardViewModel
	private var _binding: FragmentDashboardBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	var substances : MutableList<Substance> = ArrayList()
	lateinit var subList : ListView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		dashboardViewModel =
			ViewModelProvider(this).get(DashboardViewModel::class.java)

		_binding = FragmentDashboardBinding.inflate(inflater, container, false)
		val root: View = binding.root

		val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
		setSubstData()// TODO: хардкод
		subList = view.findViewById(R.id.subList)
		val substAdapter = SubstanceAdapter(container?.context ?: activity, R.layout.list_item, substances)
		subList.adapter = substAdapter

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}


	fun setSubstData() {
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
		substances.add(Substance("Каменный уголь", "аыыыаыа", R.drawable.test_photo))
	}
}