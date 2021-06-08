package com.thecoolture.batikdetectionapp.ui.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thecoolture.batikdetectionapp.databinding.FragmentHistoryBinding
import com.thecoolture.batikdetectionapp.viewmodel.ViewModelFactory

class HistoryFragment : Fragment() {

  private lateinit var historyViewModel: HistoryViewModel
private var _binding: FragmentHistoryBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val factory = activity?.let { ViewModelFactory.getInstance(it.application) }
    historyViewModel =
      factory?.let { ViewModelProvider(this, it).get(HistoryViewModel::class.java) }!!


    historyViewModel.history.observe(viewLifecycleOwner, {
      Log.d("TAG", "onCreateView: $it")
    })

    _binding = FragmentHistoryBinding.inflate(inflater, container, false)
    val root: View = binding.root

//    val textView: TextView = binding.textSlideshow
//    historyViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}