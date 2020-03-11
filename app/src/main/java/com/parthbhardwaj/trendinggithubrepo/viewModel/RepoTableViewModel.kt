package com.parthbhardwaj.trendinggithubrepo.viewModel

import com.parthbhardwaj.trendinggithubrepo.databinding.RecyclerItemBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import com.parthbhardwaj.trendinggithubrepo.utils.BaseViewModel

class RepoTableViewModel : BaseViewModel() {

    val repoTable: RepoTable? = null
    val isExpanded: Boolean = false

    fun bind(
        repoTable: RepoTable,
        binding: RecyclerItemBinding,
        expanded: Boolean
    ){

    }
}
