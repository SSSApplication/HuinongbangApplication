package com.hnb.huinongbang.ui.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import com.hnb.huinongbang.ui.Planting.PlantingViewModel
import kotlinx.android.synthetic.main.fragment_policy.*

class PolicyFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PolicyViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_policy, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_policy.text = viewModel.text.value
    }
}