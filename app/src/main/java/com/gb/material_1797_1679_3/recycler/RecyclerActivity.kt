package com.gb.material_1797_1679_3.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gb.material_1797_1679_3.R
import com.gb.material_1797_1679_3.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyRedTheme)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            Pair(Data("Заголовок", type = TYPE_HEADER), false),
            Pair(Data("Mars1"), false),
            Pair(Data("Mars2"),false),
            Pair(Data("Mars3", ""),false),
            Pair(Data("Mars4", null), false)
        )

        val adapter = RecyclerActivityAdapter(this,object: OnClickItemListener{
            override fun onItemClick(data: Data) {
                Toast.makeText(this@RecyclerActivity, "Work! ${data.name}", Toast.LENGTH_SHORT).show()
            }
        })
        adapter.setData(data)
        binding.recyclerView.adapter = adapter
        binding.recyclerActivityFAB.setOnClickListener {
            adapter.appendItem()
            binding.recyclerView.smoothScrollToPosition(adapter.itemCount)
        }

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
    }

    class ItemTouchHelperCallback(private val recyclerActivityAdapter: RecyclerActivityAdapter) :
        ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }
// 3:07
        override fun onMove(
            recyclerView: RecyclerView,
            from: RecyclerView.ViewHolder,
            to: RecyclerView.ViewHolder
        ): Boolean {
            if (from.adapterPosition > 0 && to.adapterPosition != 0)
                recyclerActivityAdapter.onItemMove(from.adapterPosition, to.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            recyclerActivityAdapter.onItemDismiss(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            if (viewHolder is RecyclerActivityAdapter.DoViewHolder)
                if (actionState!=ItemTouchHelper.ACTION_STATE_IDLE)
                    (viewHolder as RecyclerActivityAdapter.DoViewHolder).onItemSelected()
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            if (viewHolder is RecyclerActivityAdapter.DoViewHolder)
                (viewHolder as RecyclerActivityAdapter.DoViewHolder).onItemClear()
        }
    }
}
