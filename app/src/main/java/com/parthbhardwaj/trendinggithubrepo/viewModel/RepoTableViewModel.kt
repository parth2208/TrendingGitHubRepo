package com.parthbhardwaj.trendinggithubrepo.viewModel

import com.parthbhardwaj.trendinggithubrepo.databinding.RecyclerItemBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import com.parthbhardwaj.trendinggithubrepo.utils.BaseViewModel

class RepoTableViewModel : BaseViewModel() {

    var repoTable: RepoTable? = null
    var isExpanded: Boolean = false

    fun bind(
        repoTable: RepoTable,
        binding: RecyclerItemBinding,
        expanded: Boolean
    ){

        repoTable.languageColor?.let {
            binding.circularDot.setSolidColor(it) }
        this.repoTable = repoTable;
        this.isExpanded = expanded
    }
}
