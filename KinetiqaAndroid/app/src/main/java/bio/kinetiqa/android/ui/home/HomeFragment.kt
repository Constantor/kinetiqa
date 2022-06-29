package bio.kinetiqa.android.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bio.kinetiqa.android.DataBase
import bio.kinetiqa.android.GraphInfoActivity
import bio.kinetiqa.android.R
import bio.kinetiqa.android.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import java.lang.Float.max
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), OnSeekBarChangeListener,
	OnChartGestureListener, OnChartValueSelectedListener {

	public lateinit var subIds: MutableList<Integer>
	private lateinit var homeViewModel: HomeViewModel
	private var _binding: FragmentHomeBinding? = null
	private lateinit var config: Button
	private lateinit var apply: Button

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

		config = root.findViewById(R.id.confButton)
		config.setOnClickListener {
			val graphIntent = Intent(context, GraphInfoActivity::class.java)
			startActivity(graphIntent)
		}

		apply = root.findViewById(R.id.applyButton)
		apply.setOnClickListener {
			addDataOnGraph()
		}

		return root
	}

	private fun setInformationAboutChart() {

		chart.setOnChartValueSelectedListener(this)

		chart.setDrawGridBackground(false)
		chart.getDescription().text = "day"
		//chart.setDrawBorders(false)

		var xAxis = chart.xAxis
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);                // Положение оси X по умолчанию выше

		chart.getAxisRight().setEnabled(false)
		xAxis.setAxisLineWidth(2F);


		var yAxis = chart.axisLeft             // Положение оси X по умолчанию выше
		yAxis.setAxisLineWidth(2F);


		 chart.setPinchZoom(true)


		//TODO


/*		val l: Legend = chart.getLegend()
		l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
		l.orientation = Legend.LegendOrientation.VERTICAL
		l.setDrawInside(false)*/
	}

	fun setGraph(view: View) {
		//val graphIntent = Intent(context, GraphInfoActivity::class.java)
		//startActivity(graphIntent)
	}

	fun applyChanges(view: View) {
		//TODO
	}

    private fun addDataOnGraph() {
        chart.clear()
        val dataSet: ArrayList<ILineDataSet> = ArrayList()
        val rnd = Random()
        var maxY = 1f
        var ind = 0
        val colors = listOf(
            Color.parseColor("#A167A5"),
            Color.parseColor("#F3752B"),
			Color.parseColor("#72B01D"),
            Color.parseColor("#FFC800"),
            Color.parseColor("#EF233C"),
			Color.parseColor("#02C3BD"),
        )
        for (id in DataBase.getGraphInfoId()) {
            val entries: ArrayList<Entry> = DataBase.getGraphLine(id)
            for (entry in entries) {
                maxY = max(maxY, entry.y)
            }
            val col: Int = if (ind < 5)
                colors[ind]
            else
                Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            ind++
            val dataset = LineDataSet(entries, DataBase.getSubstanceFromId(id).name)
            dataset.color = col
            dataset.setDrawCircles(false)
			dataset.lineWidth = 3f
			dataset.valueTextColor = Color.TRANSPARENT
            dataSet.add(dataset)
        }
        maxY *= 1.5f
        val getData = LineData(dataSet)
        chart.data = getData
        chart.setVisibleYRange(0f, maxY, null)
        chart.animateY(50)
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