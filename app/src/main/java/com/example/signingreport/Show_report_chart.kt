package com.example.signingreport

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * A simple [Fragment] subclass.
 */
class Show_report_chart : Fragment() {

    lateinit var Pie_id : PieChart
    lateinit var Line_id : LineChart
    lateinit var button2 : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_show_report_chart, container, false)
        // Inflate the layout for this fragment
        button2 = view.findViewById(R.id.button2)
        button2.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("ต้องการรายงานปัญหาใช่หรือไม่?")
            builder.setPositiveButton("ใช่", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(this.context, "ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                val fragment_Show_data = ShowData()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout,fragment_Show_data,"fragment_Show_data")
                transaction.addToBackStack("fragment_Show_data")
                transaction.commit()

            })
            builder.setNegativeButton("ไม่ใช่", DialogInterface.OnClickListener { dialog, which ->
                //dialog.dismiss();
            })
            builder.show()
        }


        Pie_id = view.findViewById(R.id.pie_id)
        Line_id = view.findViewById(R.id.line_id)

        Pie_Chart(Pie_id)
        Line_Chart(Line_id)

        return view
    }

    fun Pie_Chart( chart: PieChart){

        //ปิด Description
        chart.description.isEnabled = false

        //สุ่มข้อมูล 5 อัน
        val listStudent = Student.getSampleStudentData(5)

        val entries: ArrayList<PieEntry> = ArrayList()
        for (student in listStudent) {
            entries.add(PieEntry(student.score, student.name))
        }

        val dataset = PieDataSet(entries, "Point Report of Facebookxcd")

        //กำหนดให้มีช่องว่างตรงกลางได้
        dataset.selectionShift = 10f
        dataset.valueTextSize = 5f

        //ตั้งค่า color
        dataset.setColors(*ColorTemplate.COLORFUL_COLORS) // set the color

        //เซ้ทช่องว่างความห่างของข้อมูล
        dataset.setSliceSpace(3f)

        //กำหนดข้อมูล
        val data = PieData(dataset)
        chart.setData(data)

        //กำหนดให้มีช่องว่างตรงกลางได้
        chart.setHoleRadius(30F)
        chart.setTransparentCircleRadius(40F)

        //กำหนด animation
        chart.animateY(3000)

        //อาตัวหนังสือออกมาไว้ข้างนอกตัวแผนภูมิ
        //X คือ ชื่อข้อมูล
        //Y คือ ค่าของข้อมูล
//        dataset.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)

        //เส้นที่โยงออกมา
        dataset.setValueLinePart1Length(0.5f)
        dataset.setValueLinePart2Length(0.5f)

        //กำหนดให้แสดงเป็น %
        chart.setUsePercentValues(true)
        dataset.setValueFormatter(PercentFormatter(chart))

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE)

        //ข้อความตรงกลางแผนภูมิ
        chart.setCenterText("Grap Report of Facebookxcd");
        chart.setCenterTextSize(5F)

    }
    fun Line_Chart(chart : LineChart) {

        //ปิด Description
        chart.description.isEnabled = false

        //ข้อมูล 4 อัน
        val entries: ArrayList<Entry> = ArrayList()
        entries.add(Entry(0F, 4F))
        entries.add(Entry(1F, 1F))
        entries.add(Entry(2F, 2F))
        entries.add(Entry(3F, 4F))

        val dataSet = LineDataSet(entries, "Grap Report of Facebookxcd")
        dataSet.color = ContextCompat.getColor(activity!!.baseContext, R.color.colorPrimary)
        dataSet.valueTextColor = ContextCompat.getColor(activity!!.baseContext, R.color.colorPrimaryDark)

        // Controlling X axis
        val xAxis = chart.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        //เซ้ทเส้นที่แสดง
        xAxis.granularity = 1f

        //Customizing x axis value
        val months = arrayOf("Jan", "Feb", "Mar", "Apr")

        xAxis.setValueFormatter(object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String? {
                return months[value.toInt()]
            }
        })

        // Controlling right side of y axis
        val yAxisRight = chart.axisRight
        yAxisRight.isEnabled = false

        // Controlling left side of y axis
        val yAxisLeft = chart.axisLeft

        //เซ้ทเส้นที่แสดง
        yAxisLeft.granularity = 1f

        // Setting Data
        val data = LineData(dataSet)
        chart.data = data
        chart.animateX(2500)

        //refresh
        chart.invalidate()

    }

}
