package com.example.acase.ui.scores.view


import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acase.R
import com.example.acase.data.remote.model.MatchesResponse
import com.example.acase.data.remote.model.NewsItem
import com.example.acase.data.remote.uistate.UIState
import com.example.acase.ui.news.adapter.NewsRcyclerAdapter
import com.example.acase.ui.scores.adapter.ScoresRcyclerAdapter
import com.example.acase.ui.scores.view_model.ScoresViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_scores.*
import javax.inject.Inject


@AndroidEntryPoint
class ScoresFragment @Inject constructor() : Fragment(), NewsRcyclerAdapter.OnItemClickListener {


    private val scoresViewModel: ScoresViewModel by viewModels()
    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 30 * 1000
    var dialog:ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scores, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
    }

    private fun dialog(){
        dialog = ProgressDialog.show(requireContext(), "", "Veriler Getiriliyor...", true)
    }

    private fun initialize(){
        try{
            dialog()
            scoresViewModel.getMatches.state.observe(viewLifecycleOwner, scoresObserver)
            scoresViewModel.getMatches.load()

            handler.postDelayed(Runnable {
                dialog()
                handler.postDelayed(runnable, delay.toLong())
                scoresViewModel.getMatches.load()

            }.also {
                runnable = it
            },
                delay.toLong()
            )
        }catch (ex: Exception){
            Toast.makeText(requireContext(), "Beklenmedik bir hata oluştu", Toast.LENGTH_SHORT).show()
            Log.e("NewsFragment initilaze", ex.message.toString())
        }
    }

    override fun onPause() {
        handler.removeCallbacks(runnable)
        super.onPause()
    }

    private val scoresObserver = Observer<UIState<MatchesResponse>> { state ->
        when (state) {
            is UIState.Loading -> {
                if (state.isLoading) {

                } else {

                }
            }
            is UIState.Failure -> {
                dialog?.dismiss()
            }
            is UIState.Success -> {
                try {
                    dialog?.dismiss()
                    txtDate.text = state.data.date
                    var scoresList: MatchesResponse? = state.data
                    rcylerScores.adapter =
                        ScoresRcyclerAdapter(scoresList)
                    val layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    rcylerScores.layoutManager = layoutManager
                    rcylerScores.setHasFixedSize(true)
                } catch (e: Exception) {
                    Log.e("scoresObserver", e.message)
                }
            }
        }
    }

    override fun onItemClick(currentItem: NewsItem?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(currentItem?.link)))
    }
}