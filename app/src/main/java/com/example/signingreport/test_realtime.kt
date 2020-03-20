package com.example.signingreport

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_test_realtime.*
import org.json.JSONArray
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class test_realtime : Fragment() {
    lateinit var buttonShowdata : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_test_realtime, container, false)
        // Inflate the layout for this fragment

        val btn1 = view.findViewById<Button>(R.id.btn1)
//        val btn2 = view.findViewById<Button>(R.id.btn2)
//        val btn3 = view.findViewById<Button>(R.id.btn3)
//        val btn4 = view.findViewById<Button>(R.id.btn4)

        //ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")

        btn1.setOnClickListener {
            //setValue() เป็นการ write หรือ update ข้อมูล ไปยัง path ที่เราอ้างถึงได้ เช่น users/<user-id>/<username>
            mUsersRef.child("id-60160185").setValue("Artit")
        }
//        buttonShowdata = view.findViewById(R.id.buttonShowdata)
//        buttonShowdata.setOnClickListener{
//            val fragment_showreal = showreal()
//            val fm = fragmentManager
//            val transaction : FragmentTransaction = fm!!.beginTransaction()
//            transaction.replace(R.id.layout,fragment_showreal,"fragment_showreal")
//            transaction.addToBackStack("fragment_showreal")
//            transaction.commit()

        return view


        }





    data class FriendlyMessage(
        var username: String? = "",
        var text: String? = ""
    )


}
