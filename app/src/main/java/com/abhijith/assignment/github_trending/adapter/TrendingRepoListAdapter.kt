package com.abhijith.assignment.github_trending.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhijith.assignment.github_trending.R
import com.abhijith.assignment.github_trending.models.GithubRepo
import com.bumptech.glide.Glide


class TrendingRepoListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TrendingRepoListAdapter.RepoViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var repos = emptyList<GithubRepo>()
    private var mExpandedPosition = -1
    private var previousExpandedPosition = -1

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author: TextView = itemView.findViewById(R.id.item_author)
        val name: TextView = itemView.findViewById(R.id.item_name)
        val description: TextView = itemView.findViewById(R.id.item_description)
        val language: TextView = itemView.findViewById(R.id.item_language)
        val starCount: TextView = itemView.findViewById(R.id.item_star_count)
        val forkCount: TextView = itemView.findViewById(R.id.item_fork_count)
        val avatar: ImageView = itemView.findViewById(R.id.item_avatar)
        val itemExtraContainer: View = itemView.findViewById(R.id.item_extra_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = inflater.inflate(R.layout.layout_trendinglist_item, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val current = repos[position]
        holder.author.text = current.author
        holder.name.text = current.name
        holder.description.text = current.description
        holder.language.text = current.language
        holder.starCount.text = current.stars.toString()
        holder.forkCount.text = current.forks.toString()
        Glide.with(holder.avatar.context)
            .load(current.avatar)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .into(holder.avatar)

        val isExpanded = (position == mExpandedPosition)
        holder.itemExtraContainer.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.itemView.isActivated = isExpanded

        if (isExpanded) previousExpandedPosition = position

        holder.itemView.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)
        }
    }

    internal fun setRepos(repos: List<GithubRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun getItemCount() = repos.size
}