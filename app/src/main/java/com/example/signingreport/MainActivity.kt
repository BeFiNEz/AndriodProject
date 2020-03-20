package com.example.signingreport

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val authen = authen()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

//        transaction.addToBackStack("login")
//        transaction.commit()
        transaction.replace(R.id.layout, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()
        debugHashKey()
    }

    override fun onBackPressed() {

        val manager = supportFragmentManager.findFragmentById(R.id.layout)
//when finish fragment_recycler_view will exit app
        if (manager is authen ) {
            finish()
        }else if(manager is  profile){
            finish()
        }
        else{
            super.onBackPressed();
        }

    }

    private fun debugHashKey() {
        try {
            val info = packageManager.getPackageInfo(
                "com.example.signingreport",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

}
