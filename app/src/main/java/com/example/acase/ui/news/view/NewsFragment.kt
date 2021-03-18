package com.example.acase.ui.news.view


import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.example.acase.data.remote.model.NewsItem
import com.example.acase.data.remote.model.NewsResponse
import com.example.acase.data.remote.uistate.UIState
import com.example.acase.ui.news.adapter.NewsRcyclerAdapter
import com.example.acase.ui.news.view_model.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


@AndroidEntryPoint
class NewsFragment @Inject constructor() : Fragment(), NewsRcyclerAdapter.OnItemClickListener {


    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialize()
    }

    private fun initialize(){
        try{
            newsViewModel.getNews.state.observe(viewLifecycleOwner, newsObserver)
            newsViewModel.getNews.load()
        }catch (ex: Exception){
            Toast.makeText(requireContext(), "Beklenmedik bir hata oluştu", Toast.LENGTH_SHORT).show()
            Log.e("NewsFragment initilaze", ex.message.toString())
        }
    }

    private val newsObserver = Observer<UIState<NewsResponse>> { state ->
        when (state) {
            is UIState.Loading -> {
                if (state.isLoading) {

                } else {

                }
            }
            is UIState.Failure -> {

            }
            is UIState.Success -> {
                try {
                    var newsList: NewsResponse? = state.data

                    rcyclerNews.adapter =
                        NewsRcyclerAdapter(newsList, this, requireContext())
                    val layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    rcyclerNews.layoutManager = layoutManager
                    rcyclerNews.setHasFixedSize(true)
                } catch (e: Exception) {
                    Log.e("newsObserver", e.message)
                }
            }
        }
    }

    override fun onItemClick(currentItem: NewsItem?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(currentItem?.link)))
    }
}