package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodel.ShoeViewModel
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: ShoeDetailFragmentBinding

    private val shoeViewModel : ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )
        setHasOptionsMenu(true)

        binding.shoeViewModel = shoeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        shoeViewModel.shouldShowWarning.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.empty_shoe_warning), Toast.LENGTH_SHORT).show()
                shoeViewModel.onWarningShowed()
            }
        })

        shoeViewModel.detailReturnToList.observe(viewLifecycleOwner, {
            if (it) {
                shoeViewModel.onShoeAdded()
                findNavController().navigateUp()
            }
        })

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shoeListFragment) {
           addShoe()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addShoe() {
        shoeViewModel.addShoe()
    }

}