package com.example.nit3213assessment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213assessment2.R

class MyAdaptor(private var dataList: List<String> = listOf(), private val clickLambdaFunction: () -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position],clickLambdaFunction)
    }

    fun updateData(newDataList:List<String>){
        dataList=newDataList
        notifyDataSetChanged()
    }

}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val textView: TextView =view.findViewById(R.id.RecyclerTextView)
    fun bind(item: String, clickLambdaFunction: () -> Unit) {
        textView.text=item
        textView.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("SelectedItemIndex", this.adapterPosition)

            val navController = Navigation.findNavController(itemView)

            navController.navigate(R.id.action_dashboardFragment_to_detailsFragment, bundle)

            clickLambdaFunction.invoke()
        }
    }

}