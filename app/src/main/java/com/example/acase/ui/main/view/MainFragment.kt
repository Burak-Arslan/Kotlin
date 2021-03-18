package com.example.acase.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.acase.R
import com.example.acase.ui.news.view.NewsFragment
import com.example.acase.ui.scores.view.ScoresFragment


class MainFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initilaze()
        events()
    }

    private fun initilaze() {
        try {
            setHasOptionsMenu(true)
        } catch (ex: Exception) {
            Log.e("MainFragment initilaze", ex.message.toString())
        }
    }

    private fun events() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finishAffinity()
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        try {
            inflater.inflate(R.menu.nav_menu, menu)
        } catch (ex: Exception) {
            Log.e("onCreateOptionsMenu", ex.message.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals("News")) {
            var newsFragment = NewsFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, newsFragment)
                ?.commit()
        } else if (item.title.equals("Scores")) {
            var scoresFragment = ScoresFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer, scoresFragment)
                ?.commit()
        }
        return super.onOptionsItemSelected(item)
    }
}