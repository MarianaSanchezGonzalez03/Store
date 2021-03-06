package com.example.stores_da

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stores_da.databinding.ItemStoreBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class StoreAdapter(private var stores: MutableList<Store>, private  var listener: OnClickListener) :
    RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_store, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewH    older(holder: ViewHolder, position: Int) {
        val store = stores.get(position)

        with(holder){
            setListener(store)
            binding.tvName.text = store.name
            binding.cbFavorite.isChecked = store.isFavorite
            Glide.with(mContext)
                .load(store.photoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = stores.size
    fun setStores(stores: MutableList<StoreEntity>) {
        this.stores = stores
        notifyDataSetChanged()
    }

    fun add(store: Store) {
        stores.add(store)
        notifyDataSetChanged()
    }
    fun update(storeEntity: StoreEntity) {
        if (!stores.contains(storeEntity)){

        val index = stores.indexOf(storeEntity)
        if (index != -1){
            stores.set(index, storeEntity)
            notifyItemInserted(stores.size-1)
        }
            }
        fun delete(storeEntity: StoreEntity) {
            val index = stores.indexOf(storeEntity)
            if (index != -1){
                stores.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemStoreBinding.bind(view)

        fun setListener(store: Store){
            binding.root.setOnClickListener { listener.onClick(store) }
            with(binding.root){
                setOnClickListener { listener.onClick(storeEntity.id) }
                setOnLongClickListener {
                    listener.onDeleteStore(storeEntity)
                    true
                }
            }
        }
    }
}