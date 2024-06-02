package com.example.maketeam_app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**fragment()대신 이걸 상속받으시면 됩니다.*/
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes : Int) : Fragment() {
    lateinit var binding : T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        initClick()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()

    abstract fun initClick()

}