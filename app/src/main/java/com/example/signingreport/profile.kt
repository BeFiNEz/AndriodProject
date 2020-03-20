package com.example.signingreport


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class profile : Fragment() {

    var PhotoURL : String = ""
    var Name : String = ""

    fun newInstance(url: String,name : String): profile {

        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)

        return profile
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val login_button2 = view.findViewById(R.id.login_button2) as Button
        val btn_report = view.findViewById(R.id.btn_report) as Button

        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)

        tvName.setText(Name)

        login_button2.setOnClickListener{

            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()

        }


        btn_report.setOnClickListener{
            val myToast = Toast.makeText(context,"ขอบคุณที่เข้ามาแสดงความคิดเห็นทางทีมงานจะนำไปพัฒนาต่อ",Toast.LENGTH_SHORT)
            myToast.show()


            val report = report().newInstance(Name)
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout,report,"fragment_comment")
            transaction.addToBackStack("fragment_comment")
            transaction.commit()



        }



        // Inflate the layout for this fragment
        return view

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()

        }

    }










}



