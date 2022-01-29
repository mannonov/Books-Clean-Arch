package com.mannonov.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.mannonov.myapplication.App
import com.mannonov.myapplication.R
import com.mannonov.myapplication.databinding.ActivityMainBinding
import com.mannonov.myapplication.viewmodel.BookResource
import com.mannonov.myapplication.viewmodel.BookViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: BookViewModel by viewModels { factory }

    private val TAG = "BookResponse"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.appComponent.inject(this)

        lifecycleScope.launch {
            viewModel.getBooks(2).collect {
                when (it) {
                    is BookResource.Loading -> Log.d(TAG, "Loading: $it")
                    is BookResource.Error -> Log.d(TAG, "Error: $it")
                    is BookResource.Success -> Log.d(TAG, "Success: ${it.list}")
                }
            }
        }

    }
}