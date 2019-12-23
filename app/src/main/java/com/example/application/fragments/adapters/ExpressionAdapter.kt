package com.example.application.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.application.R

public class ExpressionAdapter(var items: MutableList<String>, val context: Context) :
    Adapter<ExpressionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpressionViewHolder {
        return ExpressionViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.expression_cell,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ExpressionViewHolder, position: Int) {
        holder.expressionText.text = items[position]
    }

    fun addItem(item: String) {
        items.add(0, item)
        notifyDataSetChanged()
    }

    fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    fun replace(old: String, new: String) {
        if (!items.contains(old)) return

        val index = items.indexOf(old)
        items[index] = new
        notifyDataSetChanged()
    }

    fun removeLast() {
        if (items.size == 0) return
        var first = items.first()
        var lastString = first.dropLast(3)
        items.removeAt(0)
        items.add(0,lastString)
        notifyDataSetChanged()




    }


}



