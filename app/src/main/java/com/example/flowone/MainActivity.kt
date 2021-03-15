package com.example.flowone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "Log Debug"
    }

    private lateinit var flow: Flow<Int>

    private lateinit var flowOne: Flow<String>
    private lateinit var flowTwo: Flow<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFlow()
        setupClick()
    }

    private fun setupFlow() {
//        flow = flow {
//            Log.d(TAG, "Start Flow")
//            (0..10).forEach {
//                delay(500)
//                Log.d(TAG, "Emmit $it")
//                emit(it)
//            }
//        }.map {
//            it * it
//        }.flowOn(Dispatchers.Default)

//        flow = flowOf(4, 2, 5, 1, 7).onEach { delay(400) }.flowOn(Dispatchers.Default)

//        flow = (1..5).asFlow().onEach { delay(300) }.flowOn(Dispatchers.Default)

        flowOne = flowOf("Himanshu", "Amit", "Janishar").flowOn(Dispatchers.Default)
        flowTwo = flowOf("Singh", "Shekhar", "Ali").flowOn(Dispatchers.Default)
    }

    private fun setupClick() {
        button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
//                flow.collect {
//                    Log.d(TAG, "collect $it")
//                }
                flowOne.zip(flowTwo) { x, y ->
                    "$x $y"
                }.collect {
                    Log.d(TAG, "collect $it")
                }
            }
        }
    }
}