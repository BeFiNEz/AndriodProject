package com.example.signingreport


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class item_selected : Fragment() {

    private var head : String = ""
    private var body : String = ""
    private var img : String = ""

    //function get data from Adapter class
    fun newInstance(head: String,body: String,img: String): item_selected {

        val fragment = item_selected()
        val bundle = Bundle()
        bundle.putString("head", head)
        bundle.putString("body", body)
        bundle.putString("img", img)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            head = bundle.getString("head").toString()
            body = bundle.getString("body").toString()
            img = bundle.getString("img").toString()

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_item_selected, container, false)

        //get id from fragment_item_select file
        val imgVi : ImageView = view.findViewById(R.id.imgV)
        val headTxt : TextView = view.findViewById(R.id.tv_name)
        val bodyTxt : TextView = view.findViewById(R.id.tv_description)
        //set data from resource file to fragment_item_select file
        headTxt.setText(head)
        bodyTxt.setText(body)

        //get image from resource file
        Glide.with(activity!!.baseContext)
            .load(img)
            .into(imgVi);
        // Inflate the layout for this fragment



        //get id from fragment_item_select file


        return view
    }

}
