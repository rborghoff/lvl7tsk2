package com.example.lvl7tsk2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_vraag.*

class VraagFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var question: Question
    private val args: VraagFragmentArgs by navArgs()
     private lateinit var viewview:View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(activity as AppCompatActivity).get(QuizViewModel::class.java)

        viewview = inflater.inflate(R.layout.fragment_vraag, container, false)
        return viewview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnconfirm.setOnClickListener {
            onClick()

        }
        initViews()

    }

    private fun initViews() {

        question = viewModel.getQuestion(args.pageIndex)

        tvProgress.text = getString(R.string.vooruitgeng, args.pageIndex)
        textView.text = question.question
        radioButton.text = question.choices[0]
        radioButton2.text = question.choices[1]
        radioButton3.text = question.choices[2]
        viewModel.questIndexAt = args.progressIndex
    }


    private fun onClick(){

        if (radioGroup.getCheckedRadioButtonId() != -1) {

            val choiceText = viewview.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)?.text
            if ( choiceText == question.correctAnswer) {
                val action = VraagFragmentDirections.actionVraagFragmentToLokatieFragment(args.progressIndex, args.pageIndex)
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Wrong answer.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Please select an answer.", Toast.LENGTH_LONG).show()
        }
    }

}