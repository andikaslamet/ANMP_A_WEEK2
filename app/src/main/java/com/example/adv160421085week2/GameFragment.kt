package com.example.adv160421085week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.adv160421085week2.databinding.FragmentGameBinding
import android.widget.TextView;
import kotlin.random.Random
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {
    private lateinit var binding:FragmentGameBinding
    private var num1: Int = 0
    private var num2: Int = 0
    private var answer: Int = 0
    private var scores: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.ResultTxt.text = "$playerName's Turn"
        }
        binding.backBtns.setOnClickListener {
            val action = GameFragmentDirections.actionMainFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.answerBtn.setOnClickListener {
            checkAnswer()
        }
        generateQuestion()
    }
    private fun checkAnswer() {
        val playerAnswer = binding.Jawabantxt.text.toString()
        if (playerAnswer.isNotEmpty()) {
            if (playerAnswer.toInt() == answer) {
                scores++
                generateQuestion()
            } else {
                endGame()
            }
        }
    }
    private fun generateQuestion() {
        num1 = Random.nextInt(100)
        num2 = Random.nextInt(100)
        answer = num1 + num2
        binding.Soaltxt.text = "$num1 + $num2 = ?"
    }
    private fun endGame() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(scores)
        findNavController().navigate(action)
    }
}



