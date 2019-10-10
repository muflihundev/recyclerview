package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.adapter.ListHeroAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var rv_Heroes: RecyclerView
    private var list: ArrayList<Hero> = arrayListOf()
    private var title: String = "Mode List"
    private lateinit var onItemClickCallBack: ListHeroAdapter.OnItemClickCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)
        rv_Heroes = findViewById(R.id.rv_heroes)
        rv_Heroes.setHasFixedSize(true)

        list.addAll(HeroesData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList(){
        rv_Heroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rv_Heroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object: ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero){
                showSelectedHero(data)
            }
        })

    }
    private fun setActionBarTitle(Title: String){
        if (supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerGrid(){
        rv_Heroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rv_Heroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallBack(object: GridHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
    private fun showRecyclerCardView(){
        rv_Heroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rv_Heroes.adapter = cardViewHeroAdapter
    }

    private fun setMode(selectedMode: Int){
        when (selectedMode){
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }
        setActionBarTitle(title)
    }
    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this, "Kamu Memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}
