package com.example.proga.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proga.INavMainActivity
import com.example.proga.databinding.FragmentMessageBinding

class MessageFragment : Fragment(){
    lateinit var binding: FragmentMessageBinding
    companion object {
        fun newInstance() = MessageFragment()
    }
    lateinit var listener : INavMainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is INavMainActivity){
            listener = context
        }
    }

    fun clic() {
        binding.btnMes.setOnClickListener {
            listener.goToChat()
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clic()

        }
    }

