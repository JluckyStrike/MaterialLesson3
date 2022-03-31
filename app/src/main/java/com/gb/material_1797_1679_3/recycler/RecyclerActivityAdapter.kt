package com.gb.material_1797_1679_3.recycler

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.ActivityRecyclerItemDoBinding
import com.gb.material_1797_1679_3.databinding.ActivityRecyclerItemHeaderBinding

class RecyclerActivityAdapter(val c: Context, val onClickItemListener: OnClickItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {
    lateinit var listData: MutableList<Pair<Data, Boolean>>

    fun setData(listData: MutableList<Pair<Data, Boolean>>) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_DO -> {
                val binding = ActivityRecyclerItemDoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DoViewHolder(binding.root)
            }
            else -> {
                val binding = ActivityRecyclerItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DO -> {
                (holder as DoViewHolder).bind(listData[position])
            }
            else -> {
                (holder as HeaderViewHolder).bind(listData[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun appendItem() {
        listData.add(generateData())
        notifyItemInserted(listData.size - 1)
    }

    fun generateData() = Pair(Data("Mars", type = TYPE_DO), false)

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class DoViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder{
        override fun bind(data: Pair<Data, Boolean>) {
            ActivityRecyclerItemDoBinding.bind(itemView).apply {
                tvName.text = data.first.name

                ivDo.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }

                addItemImageView.setOnClickListener {
                    listData.add(layoutPosition, generateData())
                    notifyItemInserted(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    listData.removeAt(layoutPosition)
                    notifyItemRemoved(layoutPosition)
                }
                editItemImageView.setOnClickListener {
                    val v = LayoutInflater.from(c).inflate(R.layout.edit_item,null)
                    val name = v.findViewById<EditText>(R.id.header)

                    AlertDialog.Builder(c)
                        .setView(v)
                        .setPositiveButton("Ok"){
                                dialog,_->
                            data.first.name =  name.text.toString()
                            notifyDataSetChanged()
                            Toast.makeText(c,"Information is Edited",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                        }
                        .setNegativeButton("Cancel"){
                                dialog,_->
                            dialog.dismiss()

                        }
                        .create()
                        .show()
                }

                moveItemUp.setOnClickListener {
                    if (layoutPosition > 1) {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition - 1, this)
                        }

                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }
                moveItemDown.setOnClickListener {
                    if (listData.lastIndex > layoutPosition) {
                        listData.removeAt(layoutPosition).apply {
                            listData.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }
                marsDescriptionTextView.visibility = if (listData[layoutPosition].second) View.VISIBLE else View.GONE
                itemView.setOnClickListener {
                    listData[layoutPosition] = listData[layoutPosition].let {
                        it.first to !it.second
                    }
                    notifyItemChanged(layoutPosition)
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.MAGENTA)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    abstract class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: Pair<Data, Boolean>)
    }


    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            ActivityRecyclerItemHeaderBinding.bind(itemView).apply {
                tvName.text = data.first.name

                itemView.setOnClickListener {
                    onClickItemListener.onItemClick(data.first)
                }
            }
        }
    }
}

