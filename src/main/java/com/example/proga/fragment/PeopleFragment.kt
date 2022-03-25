package com.example.proga.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proga.CustomRecyclerAdapter
import com.example.proga.data.firebase.FirebaseManager
import com.example.proga.data.firebase.UserFirebase
import com.example.proga.databinding.FragmentPeopleBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PeopleFragment : Fragment(), FirebaseManager.ReadDataCallback {

    lateinit var binding: FragmentPeopleBinding
    val auth = Firebase.auth

    companion object {
        fun newInstance() = PeopleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseManager = FirebaseManager()
        firebaseManager.getAllUsers(this)
        binding.recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun readData(list: List<UserFirebase>) {

        val filterList = filterRecommendation(list.toMutableList())
        val adapter = CustomRecyclerAdapter(filterList)

        binding.recyclerView2.adapter = adapter
    }

    private fun filterRecommendation(list: MutableList<UserFirebase>): List<UserFirebase> {
        val filterList = mutableListOf<UserFirebase>()
        val currentUser = list.findLast { userFirebase ->
            auth.uid == userFirebase.uid
        }
        list.filter { userFirebase ->
            auth.uid != userFirebase.uid
        }.forEach { user ->
            if (user.name == currentUser?.name
                && user.name2 == currentUser?.name2
                && user.name3 == currentUser?.name3
                && user.name4 == currentUser?.name4
            ) {
                filterList.add(user)
            }
        }
        return filterList
    }

}