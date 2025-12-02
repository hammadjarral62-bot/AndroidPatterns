// RECYCLER VIEW SETUP (GRID)

val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

recyclerView.layoutManager = GridLayoutManager(this, 3)

recyclerView.adapter = MyAdapter(items, this)
