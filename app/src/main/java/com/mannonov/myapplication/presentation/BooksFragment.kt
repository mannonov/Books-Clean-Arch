package com.mannonov.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mannonov.domains.models.Book
import com.mannonov.myapplication.App
import com.mannonov.myapplication.R
import com.mannonov.myapplication.databinding.FragmentBooksBinding
import com.mannonov.myapplication.presentation.adapter.BookRecyclerViewAdapter
import com.mannonov.myapplication.viewmodel.BookResource
import com.mannonov.myapplication.viewmodel.BookViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class BooksFragment : Fragment() {

    private lateinit var _binding: FragmentBooksBinding
    private val binding get() = _binding

    private val args: BooksFragmentArgs by navArgs()

    private val TAG = "BooksFragment"

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: BookViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBooksBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getBooks(args.count).collect {
                when (it) {
                    is BookResource.Loading -> Log.d(TAG, "Loading: $it")
                    is BookResource.Error -> Log.d(TAG, "Error: $it")
                    is BookResource.Success -> setUpAdapter(it.list)
                }
            }
        }

    }

    private fun setUpAdapter(list:List<Book>){
        binding.root.apply {
            adapter = BookRecyclerViewAdapter(list, BookRecyclerViewAdapter.ItemClickListener {
                findNavController().navigate(BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(it))
            })
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}