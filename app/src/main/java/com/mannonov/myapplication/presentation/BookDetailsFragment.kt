package com.mannonov.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mannonov.myapplication.App
import com.mannonov.myapplication.R
import com.mannonov.myapplication.databinding.FragmentBookDetailsBinding
import com.mannonov.myapplication.viewmodel.BookViewModel
import javax.inject.Inject


class BookDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentBookDetailsBinding
    private val binding get() = _binding

    private val args: BookDetailsFragmentArgs by navArgs()

    private val TAG = "BookDetailsFragment"

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: BookViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        Log.d(TAG, "onCreate: ${args.book}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBookDetailsBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            Glide.with(imgBook.context).asBitmap().load(args.book.image).into(imgBook)
            tvTitle.text = args.book.title
            tvAuthor.text = args.book.author
            tvDescription.text = args.book.description
            tvGenre.text = args.book.genre
            tvDescription.text = args.book.description
            tvIsbn.text = args.book.isbn
            tvPublished.text = args.book.published
            tvPublisher.text = args.book.publisher
        }

        binding.btnAddFavorites.setOnClickListener {
            viewModel.insertBookToDatabase(args.book)
        }

    }

}