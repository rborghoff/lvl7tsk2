package com.example.lvl7tsk2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_lokatie.*

/**
 * A simple [Fragment] subclass.
 */
class LokatieFragment : Fragment() {

    private lateinit var viewModel : QuizViewModel;
    private lateinit var question: Question;
     val args: LokatieFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity as AppCompatActivity).get(QuizViewModel::class.java)

        return inflater.inflate(R.layout.fragment_lokatie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
initViews()
        btnNext.setOnClickListener {
            onClick()
        }
    }

    private fun initViews(){
        question = viewModel.getQuestion(args.pageIndex)
        var image = question.clue
        ivLokatie.setImageDrawable(ContextCompat.getDrawable(context!!,image))
        if(args.pageIndex == 9) {
            btnNext.text = getString(R.string.claim)
        }
    }
    private fun onClick(){

        if (args.pageIndex  != 9) {
            val action = LokatieFragmentDirections.actionLokatieFragmentToVraagFragment(args.progressIndex + 1, args.pageIndex + 1)
            findNavController().navigate(action)

        } else {
            val action = LokatieFragmentDirections.actionLokatieFragmentToFeestFragment()
            findNavController().navigate(action)
        }
    }
}
