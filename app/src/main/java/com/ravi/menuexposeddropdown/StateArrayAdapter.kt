package com.ravi.menuexposeddropdown

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class StateArrayAdapter(context: Context, objects: List<State>) :
    ArrayAdapter<State>(context, android.R.layout.simple_list_item_1, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = super.getView(position, convertView, parent) as TextView
        itemView.text = getItem(position)?.state.toString()
        return itemView
    }
}