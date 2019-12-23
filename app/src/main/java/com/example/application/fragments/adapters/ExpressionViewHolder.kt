package com.example.application.fragments.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R

public class ExpressionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    public var expressionText: TextView = itemView.findViewById(R.id.expression_cell_item)
}