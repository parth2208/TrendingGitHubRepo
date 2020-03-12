package com.parthbhardwaj.trendinggithubrepo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.parthbhardwaj.trendinggithubrepo.R
import com.parthbhardwaj.trendinggithubrepo.databinding.RecyclerItemBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import com.parthbhardwaj.trendinggithubrepo.viewModel.RepoTableViewModel

class TrendingListAdapter: RecyclerView.Adapter<TrendingListAdapter.ViewHolder>() {

    private lateinit var repoList: List<RepoTable>
    private var selectedNode: Int = -1
    private var previousSelectedNode: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: RecyclerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    /**
     * Gets the count of the list
     */
    override fun getItemCount(): Int {
        return if (::repoList.isInitialized) repoList.size else 0

    }

    /**
     * @param holder This will bind it to layout
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (selectedNode != -1 && selectedNode == position) {
            holder.bind(repoList[position], true)
        } else {
            holder.bind(repoList[position], false)
        }
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (position == selectedNode) {
                    selectedNode = -1;
                } else {
                    if (selectedNode != -1) {
                        previousSelectedNode = selectedNode
                        notifyItemChanged(previousSelectedNode)
                    }
                    selectedNode = position
                }
                notifyItemChanged(holder.adapterPosition)
            }
        })
    }

    /**
     * Notify any change in the Rx observable and make the change in recyclerview list
     */
    fun updateItemList(itemList: List<RepoTable>){
        this.repoList = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = RepoTableViewModel()
        fun bind(repoTable: RepoTable, isExpended: Boolean) {
            viewModel.bind(repoTable, binding, isExpended)
            binding.repoViewModel = viewModel
        }
    }
}