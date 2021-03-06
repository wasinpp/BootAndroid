package com.med.feature.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitcompat.SplitCompat
import com.med.coreui.viewModelWithFactory
import kotlinx.android.synthetic.main.search.*

class SearchFragment : Fragment() {
	private val viewModel by viewModelWithFactory {
		SearchViewModel()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.search, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		root_searh.transitionToEnd()
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		SplitCompat.installActivity(context)
	}

}