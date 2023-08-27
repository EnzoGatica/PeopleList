package com.example.peoplelist.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.peoplelist.R
import com.example.peoplelist.databinding.FragmentListadoBinding

class ListadoFragment : Fragment() {

    lateinit var binding: FragmentListadoBinding
    private val peopleVM: PeopleViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListadoBinding.inflate(layoutInflater,container,false)
        initAdapter()
        peopleVM.getAllPeople()
        return binding.root
    }

    private fun initAdapter() {
        val adapter  = AdapterPeople()

        binding.recyclePeople.adapter  = adapter
        peopleVM.peopleLiveData().observe(viewLifecycleOwner){
            if (it != null) {
                adapter.setData(it)
            }
        }
    }


}