package com.example.proyectofinalevaluacin_paulaplazaguirado.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.proyectofinalevaluacin_paulaplazaguirado.Model.OnFragmentActionListener
import com.example.proyectofinalevaluacin_paulaplazaguirado.R
import com.example.proyectofinalevaluacin_paulaplazaguirado.databinding.FragmentMenuBinding

class Menu : Fragment() {

    private var _binding:FragmentMenuBinding?=null
    private val binding get() = _binding!!
    private val botones= arrayOf<Int>(R.id.iv_localizacion,R.id.iv_compra,R.id.iv_reparto, R.id.iv_sesion)
    private var btn=7
    private var listener:OnFragmentActionListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var img: ImageView
        if (arguments!=null){
            btn = requireArguments().getInt("BOTON")
        }
        for (i in 0 until botones.size){
            img=view.findViewById(botones[i])
            if (btn==i){
                img.setImageResource(botones[i])
            }
            img.setOnClickListener {
                listener?.onClickMenu(i)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionListener)listener=context
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }

    companion object{
        @JvmStatic
        fun newInstance():Menu{
            return Menu()
        }
    }

}