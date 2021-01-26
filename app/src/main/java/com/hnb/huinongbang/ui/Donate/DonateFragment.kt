package com.hnb.huinongbang.ui.Donate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hnb.huinongbang.R
import kotlinx.android.synthetic.main.fragment_donate.*

class DonateFragment : Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(DonateViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        text_donate.text = viewModel.text.value
    }
}