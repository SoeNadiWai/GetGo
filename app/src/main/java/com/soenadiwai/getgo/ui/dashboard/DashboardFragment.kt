package com.soenadiwai.getgo.ui.dashboard

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.soenadiwai.getgo.MainActivity
import com.soenadiwai.getgo.databinding.FragmentDashboardBinding
import com.soenadiwai.getgo.ui.SearchResultFragment


class DashboardFragment : Fragment(),OnMapReadyCallback {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        try {
            MapsInitializer.initialize(activity)
        } catch (e: GooglePlayServicesNotAvailableException) {
            Toast.makeText(
                context,
                "Hot spots are not supported",
                Toast.LENGTH_LONG
            ).show()
            return null
        }

         var googleMap = (binding.mapView as MapView).getMapAsync(this)
        if (googleMap == null) {
            Toast.makeText(
                context,
                "Hot spots are not supported",
                Toast.LENGTH_LONG
            ).show()
            return null
        }

        binding.goTextView.setOnClickListener{
            (activity as MainActivity).replaceFragment(SearchResultFragment(), "SearchResultFragment")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(p0: GoogleMap?) {
        var mGoogleMap = p0;
        val sydney = LatLng(-34.0, 151.0)
        mGoogleMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true;true
    }

}