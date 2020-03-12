package com.parthbhardwaj.trendinggithubrepo.viewModel

import com.parthbhardwaj.trendinggithubrepo.databinding.RecyclerItemBinding
import com.parthbhardwaj.trendinggithubrepo.model.db.RepoTable
import com.parthbhardwaj.trendinggithubrepo.utils.BaseViewModel

/**
 * Extend base model
 */
class RepoTableViewModel : BaseViewModel() {

    var repoTable: RepoTable? = null
    var isExpanded: Boolean = false

    /**
     * Binds the language color to the view
     */
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
