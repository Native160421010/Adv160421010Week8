package com.wildfire.todoapp_160421010.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.wildfire.todoapp_160421010.databinding.TodoItemLayoutBinding
import com.wildfire.todoapp_160421010.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.checkTask.text = todoList[position].title
        holder.binding.txtNotesToDo.text = todoList[position].notes

        holder.binding.checkTask.setOnCheckedChangeListener {
                compoundButton, b ->
            if(compoundButton.isPressed) {
                adapterOnClick(todoList[position])
            }
        }

        holder.binding.imgEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}