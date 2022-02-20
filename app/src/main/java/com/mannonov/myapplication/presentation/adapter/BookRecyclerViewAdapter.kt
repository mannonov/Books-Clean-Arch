package com.mannonov.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mannonov.domains.models.Book
import com.mannonov.myapplication.databinding.ItemBookBinding

class BookRecyclerViewAdapter(val books: List<Book>, val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val book = books[position]

        holder.binding.apply {
            Glide.with(imgBook.context).asBitmap().load(book.image).into(imgBook)
            tvTitle.text = book.title
            tvDesc.text = book.description
        }

        holder.binding.root.setOnClickListener {
            itemClickListener.onClick(book)
        }

    }

    class ItemClickListener(val onClick: (book: Book) -> Unit)

    override fun getItemCount(): Int = books.size

    class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

}