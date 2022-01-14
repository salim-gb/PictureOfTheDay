package com.example.pictureoftheday.ui.notes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pictureoftheday.R
import com.example.pictureoftheday.databinding.NotesFragmentBinding
import com.example.pictureoftheday.model.ListItem
import com.example.pictureoftheday.model.NoteBig
import com.example.pictureoftheday.model.NoteSmall
import com.example.pictureoftheday.util.AdapterDelegates
import com.example.pictureoftheday.util.CommonCallbackImpl
import com.example.pictureoftheday.util.HeaderAdapter
import java.util.*

class Notes : Fragment(R.layout.notes_fragment) {

    private var _binding: NotesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotesViewModel> {
        NotesViewModelFactory()
    }

    var items = emptyList<ListItem>()
        set(value) {
            val callback = CommonCallbackImpl(
                oldItems = field,
                newItems = value,
            )
            field = value

            val diffResult = DiffUtil.calculateDiff(callback)
            adapter.currentList = field
            diffResult.dispatchUpdatesTo(adapter)
        }

    private val adapter = AdapterDelegates(
        delegates = listOf(NoteSmallDelegate(), NoteBigDelegate())
    ) { listItem, isChecked ->
        adapterOnClick(listItem, isChecked)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NotesFragmentBinding.bind(view)

        val headerAdapter = HeaderAdapter()

        val concatAdapter = ConcatAdapter(headerAdapter, adapter)

        binding.notesRecyclerView.adapter = concatAdapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or
                    ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPos: Int = viewHolder.bindingAdapterPosition
                val toPos: Int = target.bindingAdapterPosition
                val list = items.toMutableList()
                Collections.swap(list, fromPos, toPos)
                items = list
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val mList = items.toMutableList()
                val note = mList[position]
                viewModel.removeNote(note)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.notesRecyclerView)

        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                items = it as MutableList<ListItem>
                headerAdapter.updateNotesCount(it.size)
            }
        }

        binding.notesFab.setOnClickListener {
            viewModel.insertNote("New Note Title", "New note description..")
            binding.notesRecyclerView.scrollToPosition(0)
        }

        binding.notesRecyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.notesRecyclerView.canScrollVertically(-1).let {
                binding.notesToolbar.elevation = if (it) 8f else 0f
            }
        }
    }

    private fun adapterOnClick(listItem: ListItem, isChecked: Boolean?) {
        if (isChecked != null) {
            viewModel.onCheckBoxFavoriteClick(listItem)
        } else {
            when (listItem) {
                is NoteBig -> {
                    Toast.makeText(context, "Title: ${listItem.title}", Toast.LENGTH_LONG).show()
                }
                is NoteSmall -> {
                    Toast.makeText(context, "Title: ${listItem.title}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}