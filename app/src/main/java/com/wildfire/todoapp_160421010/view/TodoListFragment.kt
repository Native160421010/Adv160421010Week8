package com.wildfire.todoapp_160421010.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.wildfire.todoapp_160421010.databinding.FragmentTodoListBinding
import com.wildfire.todoapp_160421010.model.Todo
import com.wildfire.todoapp_160421010.viewmodel.ListTodoViewModel

class TodoListFragment : Fragment() {
    private lateinit var viewModel:ListTodoViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf(), { item -> viewModel.clearTask(item) })
    private lateinit var binding:FragmentTodoListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh(0)
        binding.recViewToDo.layoutManager = LinearLayoutManager(context)
        binding.recViewToDo.adapter = todoListAdapter

        binding.btnFab.setOnClickListener {
            val action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        binding.radioGroupToDoState.setOnCheckedChangeListener{ group, checkedId ->
            val radio = view.findViewById<RadioButton>(checkedId)
            val radioButtonTag = radio.tag.toString().toInt()

            viewModel.refresh(radioButtonTag)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            if(it.isEmpty()) {
                binding.recViewToDo?.visibility = View.GONE
                binding.txtEmpty?.visibility = View.VISIBLE
            } else {
                binding.recViewToDo?.visibility = View.VISIBLE
                binding.txtEmpty?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.progressBar?.visibility = View.GONE
            } else {
                binding.progressBar?.visibility = View.VISIBLE
            }
        })

        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.txtError?.visibility = View.GONE
            } else {
                binding.txtError?.visibility = View.VISIBLE
            }
        })

    }

}