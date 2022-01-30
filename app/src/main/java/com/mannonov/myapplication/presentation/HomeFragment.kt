package com.mannonov.myapplication.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.mannonov.myapplication.R
import com.mannonov.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetBooks.setOnClickListener {
            showAlertDialog()
        }

        binding.btnHistory.setOnClickListener {

        }

    }

    private fun showAlertDialog() {

        val editText = EditText(requireActivity())
        editText.inputType = InputType.TYPE_CLASS_NUMBER

        AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.enter_count))
            .setView(editText)
            .setPositiveButton("Enter", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    if (editText.text.isNotEmpty()) {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBooksFragment(
                            editText.text.toString().toInt()))
                    }
                }
            })
            .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            }).show()
    }


}