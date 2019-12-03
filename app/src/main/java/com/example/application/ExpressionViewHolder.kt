package com.example.application

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class ExpressionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    public var expressionText: TextView = itemView.findViewById(R.id.expression_cell_item)
}