package com.mannonov.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mannonov.domains.models.Book
import com.mannonov.myapplication.App
import com.mannonov.myapplication.R
import com.mannonov.myapplication.databinding.FragmentHistoryBinding
import com.mannonov.myapplication.presentation.adapter.BookRecyclerViewAdapter
import com.mannonov.myapplication.viewmodel.BookViewModel
import javax.inject.Inject


class HistoryFragment : Fragment() {

    private lateinit var _binding: FragmentHistoryBinding
    private val binding get() = _binding

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
        _binding = FragmentHistoryBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getBooksFromDatabaseVM().observe(requireActivity()) {
            setUpAdapter(it)
        }

    }

    private fun setUpAdapter(list:List<Book>){
        binding.root.apply {
            adapter = BookRecyclerViewAdapter(list, BookRecyclerViewAdapter.ItemClickListener {
                findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToBookDetailsFragment(it))
            })
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

}