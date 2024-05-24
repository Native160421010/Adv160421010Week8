package com.wildfire.todoapp_160421010.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.wildfire.todoapp_160421010.R
import com.wildfire.todoapp_160421010.databinding.TodoItemLayoutBinding
import com.wildfire.todoapp_160421010.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClick  {

    //class TodoViewHolder(var binding: TodoItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    class TodoViewHolder(var view:TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)

//        var binding = TodoItemLayoutBinding.inflate(
//            LayoutInflater.from(parent.context), parent,false)
//        return TodoViewHolder(binding)
    }

    // NEW! WEEK 10
    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this

//        holder.binding.checkTask.text = todoList[position].title
//        holder.binding.txtNotesToDo.text = todoList[position].notes

//        holder.binding.checkTask.setOnCheckedChangeListener {
//                compoundButton, b ->
//            if(compoundButton.isPressed) {
//                adapterOnClick(todoList[position])
//            }
//        }
//
//        holder.binding.imgEdit.setOnClickListener {
//            val action =
//                TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
//
//            Navigation.findNavController(it).navigate(action)
//        }

    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(v).navigate(action)
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