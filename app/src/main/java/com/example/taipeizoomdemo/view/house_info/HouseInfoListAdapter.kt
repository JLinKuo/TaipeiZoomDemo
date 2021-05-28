package com.example.taipeizoomdemo.view.house_info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoomdemo.databinding.ViewListHouseInfoBinding
import com.example.taipeizoomdemo.databinding.ViewListPlantItemBinding
import com.example.taipeizoomdemo.view.pojo.HousePojo
import com.example.taipeizoomdemo.view.pojo.PlantPojo

private const val VIEW_HOLDER_HOUSE_INFO_TYPE = 0
private const val VIEW_HOLDER_PLANT_ITEM_TYPE = 1

class HouseInfoListAdapter(
        private val housePojo: HousePojo,
        private val listener: ItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private val listPlants by lazy { ArrayList<PlantPojo>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return when(viewType) {
            VIEW_HOLDER_HOUSE_INFO_TYPE -> {
                HouseInfoViewHolder(ViewListHouseInfoBinding.inflate(
                        LayoutInflater.from(context), parent, false))
            }
            VIEW_HOLDER_PLANT_ITEM_TYPE -> {
                PlantItemViewHolder(ViewListPlantItemBinding.inflate(
                        LayoutInflater.from(context), parent, false))
            }
            else -> {
                PlantItemViewHolder(ViewListPlantItemBinding.inflate(
                        LayoutInflater.from(context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_HOLDER_HOUSE_INFO_TYPE -> {
                (holder as HouseInfoViewHolder).apply {
                    this.binding.houseInfo.text = housePojo.info
                    this.binding.houseCategory.text = housePojo.category
                    this.binding.houseMemo.text = housePojo.memo
                    this.binding.houseWebUrl.setOnClickListener {
                        listener.onWebLinkClicked(housePojo.webUrl)
                    }
                }
            }

            VIEW_HOLDER_PLANT_ITEM_TYPE -> {
                (holder as PlantItemViewHolder).apply {
                    this.binding.name.text = listPlants[position - 1].nameCh
                    this.binding.alias.text = listPlants[position - 1].nameAlias
                    Glide.with(context).load(listPlants[position - 1].picUrl).into(this.binding.image)

                    this.itemView.setOnClickListener {
                        listener.onPlantSelected(listPlants[holder.adapterPosition - 1])
                    }
                }
            }
        }
    }

    override fun getItemCount() = listPlants.size + 1

    override fun getItemViewType(position: Int) =
            if(position == 0) VIEW_HOLDER_HOUSE_INFO_TYPE else VIEW_HOLDER_PLANT_ITEM_TYPE

    fun setPlantList(list: List<PlantPojo>) {
        this.listPlants.addAll(list)
        notifyDataSetChanged()
    }

    fun clearPlantList() {
        this.listPlants.clear()
    }

    class HouseInfoViewHolder(val binding: ViewListHouseInfoBinding): RecyclerView.ViewHolder(binding.root)

    class PlantItemViewHolder(val binding: ViewListPlantItemBinding): RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun onWebLinkClicked(webUrl: String)
        fun onPlantSelected(plant: PlantPojo)
    }
}