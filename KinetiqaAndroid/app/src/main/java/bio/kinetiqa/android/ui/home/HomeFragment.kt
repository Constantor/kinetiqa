package bio.kinetiqa.android.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bio.kinetiqa.android.AddSubActivity
import bio.kinetiqa.android.R
import bio.kinetiqa.android.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate


class HomeFragment : Fragment(), OnSeekBarChangeListener,
	OnChartGestureListener, OnChartValueSelectedListener {

	public lateinit var subIds: MutableList<Integer>
	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	private lateinit var chart: LineChart
	//private lateinit var seekBarX: SeekBar
	//private lateinit var seekBarY:SeekBar
	private lateinit var tvX: TextView
	private lateinit var tvY:TextView

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		homeViewModel =
			ViewModelProvider(this).get(HomeViewModel::class.java)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val root: View = binding.root


		//tvX = root.findViewById(R.id.tvXMax)
		//tvY = root.findViewById(R.id.tvYMax)

		chart = root.findViewById(R.id.chart1)

		setInformationAboutChart();

		addDataOnGraph();

		return root
	}

	private fun setInformationAboutChart() {
/*		seekBarX = root.findViewById(R.id.seekBar1)
		seekBarX.setOnSeekBarChangeListener(this)

		seekBarY = root.findViewById(R.id.seekBar2)
		seekBarY.setOnSeekBarChangeListener(this)*/

		chart.setOnChartValueSelectedListener(this)

		chart.setDrawGridBackground(false)
		chart.getDescription().setEnabled(false)
		chart.setDrawBorders(false)

		chart.getAxisLeft().setEnabled(false)
		chart.getAxisRight().setDrawAxisLine(false)
		chart.getAxisRight().setDrawGridLines(false)
		chart.getXAxis().setDrawAxisLine(false)
		chart.getXAxis().setDrawGridLines(false)

		// enable touch gestures
		chart.setTouchEnabled(true)

		// enable scaling and dragging
		chart.setDragEnabled(true)
		chart.setScaleEnabled(true)

		// if disabled, scaling can be done on x- and y-axis separately
		chart.setPinchZoom(false)

		/*seekBarX.setProgress(20)
		seekBarY.setProgress(100)*/

		val l: Legend = chart.getLegend()
		l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
		l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
		l.orientation = Legend.LegendOrientation.VERTICAL
		l.setDrawInside(false)
	}

	fun setGraphSubstances(view: View) {
		val subIntent = Intent(context, AddSubActivity::class.java)
		startActivity(subIntent)
	}

	fun applyChanges(view: View) {
		//TODO
	}

	private fun addDataOnGraph() {
		val entriesFirst: ArrayList<Entry> = ArrayList()
		entriesFirst.add(Entry(1f, 5f))
		entriesFirst.add(Entry(2f, 2f))
		entriesFirst.add(Entry(3f, 1f))
		entriesFirst.add(Entry(4f, -3f))
		entriesFirst.add(Entry(5f, 4f))
		entriesFirst.add(Entry(6f, 1f))

		val datasetFirst = LineDataSet(entriesFirst, "График первый")


		val entriesSecond: ArrayList<Entry> = ArrayList()
		entriesSecond.add(Entry(0.5f, 0f))
		entriesSecond.add(Entry(2.5f, 2f))
		entriesSecond.add(Entry(3.5f, 1f))
		entriesSecond.add(Entry(3.6f, 2f))
		entriesSecond.add(Entry(4f, 0.5f))
		entriesSecond.add(Entry(5.1f, -0.5f))


		val datasetSecond = LineDataSet(entriesSecond, "График второй")


		val dataSets: ArrayList<ILineDataSet> = ArrayList()
		dataSets.add(datasetFirst)
		dataSets.add(datasetSecond)


		val data = LineData(dataSets)

		chart.data = data


		chart.invalidate()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private val colors = intArrayOf(
		ColorTemplate.VORDIPLOM_COLORS[0],
		ColorTemplate.VORDIPLOM_COLORS[1],
		ColorTemplate.VORDIPLOM_COLORS[2]
	)

	override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

	}

	fun onCreateOptionsMenu(menu: Menu): Boolean {

		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {

		return true
	}

	protected fun saveToGallery() {
		//saveToGallery(chart, "MultiLineChartActivity")
	}

	override fun onChartGestureStart(me: MotionEvent, lastPerformedGesture: ChartGesture?) {
		Log.i("Gesture", "START, x: " + me.x + ", y: " + me.y)
	}

	override fun onChartGestureEnd(me: MotionEvent?, lastPerformedGesture: ChartGesture) {
		Log.i("Gesture", "END, lastGesture: $lastPerformedGesture")

		// un-highlight values after the gesture is finished and no single-tap
		if (lastPerformedGesture != ChartGesture.SINGLE_TAP) chart.highlightValues(null) // or highlightTouch(null) for callback to onNothingSelected(...)
	}

	override fun onChartLongPressed(me: MotionEvent?) {
		Log.i("LongPress", "Chart long pressed.")
	}

	override fun onChartDoubleTapped(me: MotionEvent?) {
		Log.i("DoubleTap", "Chart double-tapped.")
	}

	override fun onChartSingleTapped(me: MotionEvent?) {
		Log.i("SingleTap", "Chart single-tapped.")
	}

	override fun onChartFling(
		me1: MotionEvent?,
		me2: MotionEvent?,
		velocityX: Float,
		velocityY: Float
	) {
		Log.i("Fling", "Chart fling. VelocityX: $velocityX, VelocityY: $velocityY")
	}

	override fun onChartScale(me: MotionEvent?, scaleX: Float, scaleY: Float) {
		Log.i("Scale / Zoom", "ScaleX: $scaleX, ScaleY: $scaleY")
	}

	override fun onChartTranslate(me: MotionEvent?, dX: Float, dY: Float) {
		Log.i("Translate / Move", "dX: $dX, dY: $dY")
	}

	override fun onValueSelected(e: Entry, h: Highlight) {
		Log.i(
			"VAL SELECTED",
			"Value: " + e.y + ", xIndex: " + e.x
					+ ", DataSet index: " + h.dataSetIndex
		)
	}

	override fun onNothingSelected() {}

	override fun onStartTrackingTouch(seekBar: SeekBar?) {}

	override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}