package com.example.lab1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.FragmentHomeBinding
import com.example.lab1.databinding.FragmentMainBinding
import java.util.Calendar

data class Item(
    val itemName: String,
    val description: String,
    val date: String
)
class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(val view: android.view.View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ViewHolder {
        val view = android.view.LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val text1 = holder.view.findViewById<android.widget.TextView>(android.R.id.text1)
        val text2 = holder.view.findViewById<android.widget.TextView>(android.R.id.text2)

        text1.text = item.itemName
        text2.text = "${item.description} - ${item.date}"
    }

    override fun getItemCount(): Int = items.size
}

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemsLists = listOf(
            Item("Вышел альбом 'бла-бла-бла'", "Сегодня группа 'бла' выпустила альбом 'бла-бла-бла'", "01.01.2022"),
            Item("Пополнение в магазине", "В магазин поступили новые товары", "12.03.2021"),
            Item("Вышел альбом 'абоба'", "Сегодня группа 'баба' выпустила альбом 'абоба'", "01.02.2021"),
            Item("Вышел альбом 'биба'", "Сегодня группа 'боба' выпустила альбом 'биба'", "12.01.2021"),
            Item("Вышел альбом 'боба'", "Сегодня группа 'биба' выпустила альбом 'боба'", "09.01.2021")
        )

        val recyclerView = binding.recyclerView
        recyclerView.adapter = ItemAdapter(itemsLists)
        Log.d("HOME_LOGGER","Home fragment started at " + Calendar.getInstance().time)
        return binding.root
    }
    }