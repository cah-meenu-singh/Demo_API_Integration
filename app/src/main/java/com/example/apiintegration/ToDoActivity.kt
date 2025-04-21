package com.example.apiintegration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiintegration.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "ToDoActivity"
class ToDoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        //for calling the suspend function
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.e(TAG, "onCreate: IOException, you might not have internet connection")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "onCreate: HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                todoAdapter.todos = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun setRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@ToDoActivity)
    }
}