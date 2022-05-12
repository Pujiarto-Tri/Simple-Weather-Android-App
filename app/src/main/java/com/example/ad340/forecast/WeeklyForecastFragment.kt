package com.example.ad340.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340.*
import com.example.ad340.details.ForecastDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [WeeklyForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeeklyForecastFragment : Fragment() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private val forecastRepository = ForecastRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""

        val view =  inflater.inflate(R.layout.fragment_weekly_forecast, container, false)

        val dailyForecastList: RecyclerView = view.findViewById(R.id.forecastList)
        dailyForecastList.layoutManager = LinearLayoutManager(requireContext())
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecast ->
            showForecastDetails(forecast)
        }
        dailyForecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            // update our list adapter
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(viewLifecycleOwner, weeklyForecastObserver)

        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener{
            showLocationEntry()
        }

        forecastRepository.loadForecast(zipcode)

        return view
    }

    private fun showLocationEntry(){
        val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToLocationEntryFragment()
        findNavController().navigate(action)
    }

    private fun showForecastDetails(forecast: DailyForecast){
        val action = WeeklyForecastFragmentDirections.actionWeeklyForecastFragmentToForecastDetailsFragment(forecast.temp, forecast.description)
        findNavController().navigate(action)
    }

    companion object{
        const val KEY_ZIPCODE = "key_zipcode"

        fun newInstance(zipcode: String) : WeeklyForecastFragment{
            val fragment = WeeklyForecastFragment()
            val args = Bundle()
            args.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = args

            return fragment
        }
    }

}