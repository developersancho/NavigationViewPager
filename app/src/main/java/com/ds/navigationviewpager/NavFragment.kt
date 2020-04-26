package com.ds.navigationviewpager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class NavFragment : Fragment() {

    private val defaultInt = -1
    private var layoutRes: Int = -1
    private var toolbarId: Int = -1
    private var navHostId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutRes = it.getInt(KEY_LAYOUT)
            navHostId = it.getInt(KEY_NAV_HOST)

        } ?: return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return if (layoutRes == defaultInt) null
        else inflater.inflate(layoutRes, container, false)
    }

    fun onBackPressed(): Boolean {
        return requireActivity()
            .findNavController(navHostId)
            .navigateUp()
    }


    fun popToRoot() {
        val navController = requireActivity().findNavController(navHostId)
        navController.popBackStack(navController.graph.startDestination, false)
    }

    fun handleDeepLink(intent: Intent) =
        requireActivity().findNavController(navHostId).handleDeepLink(intent)


    companion object {

        private const val KEY_LAYOUT = "layout_key"
        private const val KEY_NAV_HOST = "nav_host_key"

        fun newInstance(layoutRes: Int, navHostId: Int) = NavFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_LAYOUT, layoutRes)
                putInt(KEY_NAV_HOST, navHostId)
            }
        }
    }
}