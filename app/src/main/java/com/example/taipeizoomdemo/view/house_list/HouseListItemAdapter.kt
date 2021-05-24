package com.example.taipeizoomdemo.view.house_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taipeizoomdemo.R
import com.example.taipeizoomdemo.databinding.ViewListHouseItemBinding
import com.example.taipeizoomdemo.view.pojo.HousePojo

class HouseListItemAdapter: RecyclerView.Adapter<HouseListItemAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val listHouses by lazy { ArrayList<HousePojo>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(ViewListHouseItemBinding.inflate(
            LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.text = listHouses[position].name
        holder.binding.description.text = listHouses[position].info
        holder.binding.memo.text = memoMessage(listHouses[position].memo)
    }

    override fun getItemCount() = listHouses.size

    private fun memoMessage(memo: String): String {
        return if(memo.isBlank()) {
            context.getString(R.string.taipei_zoom_none_close_date)
        } else {
            memo
        }
    }

    fun updateList(list: ArrayList<HousePojo>) {
        this.listHouses.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        listHouses.clear()
    }

    inner class ViewHolder(val binding: ViewListHouseItemBinding): RecyclerView.ViewHolder(binding.root)
}