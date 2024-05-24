package com.wildfire.todoapp_160421010.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.wildfire.todoapp_160421010.R
import com.wildfire.todoapp_160421010.databinding.FragmentCreateTodoBinding
import com.wildfire.todoapp_160421010.databinding.FragmentEditTodoBinding
import com.wildfire.todoapp_160421010.model.Todo
import com.wildfire.todoapp_160421010.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), TodoSaveChangesClick, RadioClick {
    //private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        // NEW! WEEK 10
        dataBinding.radioListener = this
        dataBinding.saveListener = this

//        binding.txtJudulToDo.text = "Edit Todo"
//        binding.btnAdd.text = "Save Changes"
//
//        binding.btnAdd.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(binding.RadioGroupPriority.checkedRadioButtonId)
//            viewModel.update(binding.txtTitle.text.toString(),
//                binding.txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                else -> binding.radioHigh.isChecked = true
//            }
//        })
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container:
    ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }

}