package com.example.journeyapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.journeyapp.databinding.FragmentListBinding
import com.example.journeyapp.databinding.ListItemBinding
import com.example.journeyapp.domain.data.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()

    private var adapter: PostAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListBinding.inflate(inflater, container, false)

        adapter = PostAdapter(emptyList()) {

        }
        binding.list.let {
            it.adapter = adapter
            val layoutManager = LinearLayoutManager(requireContext())
            it.layoutManager = layoutManager
            it.addItemDecoration(DividerItemDecoration(it.context, layoutManager.orientation))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.allPosts.observe(viewLifecycleOwner, {
            adapter?.updateData(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}

class PostAdapter(
    private var items: List<Post>,
    private val listener: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    fun updateData(items: List<Post>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[holder.bindingAdapterPosition]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.title.text = item.title
            binding.content.text = item.body
        }
    }

}