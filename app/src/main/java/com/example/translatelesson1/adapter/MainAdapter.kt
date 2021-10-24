package com.example.translatelesson1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.translatelesson1.R
import com.example.translatelesson1.model.SearchResult

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<SearchResult>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {
    fun setData(data: List<SearchResult>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as
                    View
        )
    }
    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: SearchResult) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text
                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }
    private fun openInNewWindow(listItemData: SearchResult) {
        onListItemClickListener.onItemClick(listItemData)
    }
    interface OnListItemClickListener {
        fun onItemClick(data: SearchResult)
    }
}