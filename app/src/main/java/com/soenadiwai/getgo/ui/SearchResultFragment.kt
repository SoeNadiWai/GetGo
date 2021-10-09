package com.soenadiwai.getgo.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soenadiwai.getgo.Car
import com.soenadiwai.getgo.MainActivity
import com.soenadiwai.getgo.R
import com.soenadiwai.getgo.RVAdapterForCarList
import com.soenadiwai.getgo.databinding.FragmentSearchresultBinding

class SearchResultFragment: Fragment() {

    private var _binding: FragmentSearchresultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchresultBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.carinfoRecyclerView.apply {
            val layoutManager =
                LinearLayoutManager(this.context)
            val decoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
            addItemDecoration(decoration)
            binding.carinfoRecyclerView.layoutManager = layoutManager
            var car_one = Car("Mazada 3","SMS1000Z",5,3.00.toFloat(),0.40.toFloat(),"0.5KM", R.drawable.car_attrage)
            var car_two = Car("Ssangyong Tivoli","SMS1234Z",5,3.00.toFloat(),0.40.toFloat(),"0.5KM", R.drawable.car_silvermetalic)
            var car_three = Car("Honda Shuttle Hybrid","SMSN7000B",7,3.00.toFloat(),0.40.toFloat(),"0.5KM", R.drawable.car_silvermetalic)
            var carlist= listOf(car_one,car_two,car_three)
            var carListAdapter = RVAdapterForCarList(carlist)
            adapter = carListAdapter
        }

        binding.backBtn.setOnClickListener{
            (activity as MainActivity).onBackPressed()
        }

        return root
    }
}