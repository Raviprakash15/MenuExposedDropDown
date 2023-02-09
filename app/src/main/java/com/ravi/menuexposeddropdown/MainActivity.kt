package com.ravi.menuexposeddropdown

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.Gson
import com.ravi.menuexposeddropdown.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var stateModel: StateModel
    private var stateName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        //parse and get json value from locally stored value
        stateModel = parseJSON()

        //Init state Adapter
        val stateArrayAdapter = StateArrayAdapter(
            this@MainActivity,
            stateModel.states
        )
        binding.txtState.setAdapter(stateArrayAdapter)
        //On item clicked
        binding.txtState.setOnItemClickListener { parent, v, position, id ->
            //Init District Adapter
            stateName = stateArrayAdapter.getItem(position)?.state.toString()
            val divisionArrayAdapter = DistrictArrayAdapter(
                this@MainActivity,
                stateModel.states[position].districts
            )
            binding.txtDistrict.setAdapter(divisionArrayAdapter)
        }
    }

    //Read Json value stroed in asset folder
    private fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("states.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun parseJSON(): StateModel {
        return Gson().fromJson(readJSONFromAsset(), StateModel::class.java)
    }

}