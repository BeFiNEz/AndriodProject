package com.example.signingreport

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class report : Fragment() {
    fun newInstance(name: String): report {

        val report = report()
        val bundle = Bundle()
        bundle.putString("Name", name)
        report.setArguments(bundle)

        return report
    }
    lateinit var button : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_report, container, false)
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            builder.setMessage("ต้องการรายงานปัญหาใช่หรือไม่?")
            builder.setPositiveButton("ใช่", DialogInterface.OnClickListener { dialog, id ->
                Toast.makeText(this.context, "ขอบคุณครับ", Toast.LENGTH_SHORT).show()
                val fragment_Show_report_chart = Show_report_chart()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.layout, fragment_Show_report_chart,"fragment_Show_report_chart")
                transaction.addToBackStack("fragment_Show_report_chart")
                transaction.commit()


            })
            builder.setNegativeButton("ไม่ใช่", DialogInterface.OnClickListener { dialog, which ->
                //dialog.dismiss();
            })
            builder.show()
        }
        return view
    }
}
