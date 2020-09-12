package com.hegazy.ebtikar.ui.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.adapters.PopularPeoplesAdapter
import com.hegazy.ebtikar.databinding.ActivityMainBinding
import com.hegazy.ebtikar.utils.enableFullScreen
import com.hegazy.ebtikar.utils.matchDestination

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var mAdapterPopularPeople: PopularPeoplesAdapter
    private lateinit var navigationView: com.google.android.material.navigation.NavigationView
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableFullScreen(this)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        setupNavigation()
    }

    private fun setupNavigation() {
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
    }

    fun navigateTo(@IdRes resId: Int, bundle: Bundle? = null, popBackStack: Boolean? = false) {
        runOnUiThread {

            if (navController!!.currentDestination != null
                && matchDestination(
                    navController!!.currentDestination!!,
                    resId
                )
            )
                return@runOnUiThread


            if (popBackStack!!)
                navController!!.popBackStack()

            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.nothing)
                .setExitAnim(R.anim.nothing)
                .setPopEnterAnim(R.anim.nothing)
                .setPopExitAnim(R.anim.nothing)
                .build()

            navController!!.navigate(resId, bundle, navOptions)
        }

    }


}