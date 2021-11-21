package com.example.journeyapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.journeyapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ListFragment.newInstance()).commit()
        }
    }

    /** Shows the product detail fragment  */
//    fun show(product: Product) {
//        val productFragment: ProductFragment = ProductFragment.forProduct(product.getId())
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("product")
//            .replace(
//                R.id.fragment_container,
//                productFragment, null
//            ).commit()
//    }
}