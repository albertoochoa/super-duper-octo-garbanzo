package com.example.rickmortyretrofit.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.rickmortyretrofit.R
import com.example.rickmortyretrofit.databinding.ActivityMainBinding
import com.example.rickmortyretrofit.model.Character

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillWithDummys()
        fillWithCharacters()
        configureInsets()
        observerData()
    }
//    private fun fillWithCharacters() {
//        viewModel.characters.observe(this) { chars ->
//            if(chars.isNotEmpty()){
//                binding.container.removeAllViews()
//                chars.forEach { char ->
//                    val tv = TextView(this)
//                    tv.text = "${char.name} from ${char.planet}"
//                    binding.container.addView(tv)
//                }
//            }
//        }
//        viewModel.getCharacter()
//    }

    private fun observerData(){
        viewModel.charactersFlow.asLiveData().observe(this) {chars ->
            handleCharacterChanged(chars)
        }
    }

    private fun handleCharacterChanged(characters: List<Character>?){
    characters?.let {chars ->
        if(chars.isNotEmpty()){
            binding.container.removeAllViews()
            chars.forEach{char ->
                val tv = TextView(this)
                tv.text = "${char.name} from ${char.origin.name}"
                binding.container.addView(tv)

            }
        }
    }
    }

    private fun fillWithCharacters(){
        viewModel.getAllCharacters()
        viewModel.getCharacter()
        viewModel.getSomeCharacters()
    }

    private fun fillWithDummys() {

        for (i in 0..10) {
            val tv = TextView(this)
            tv.text = "Hello There $i"
            binding.container.addView(tv)
        }
    }

    private fun configureInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}