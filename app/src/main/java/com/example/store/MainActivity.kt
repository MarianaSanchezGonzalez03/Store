package com.example.stores_da
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stores_da.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener, MainAux {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
/*mBinding = ActivityMainBinding.inflate(layoutInflater)
setContentView(mBinding.root)

setupRecyclerView()
}

private fun setupRecyclerView() {
mAdapter = StoreAdapter(mutableListOf(), this)
mGridLayout = GridLayoutManager(this, 2)
getStores()
mBinding.recyclerView.apply {
    setHasFixedSize(true)
    layoutManager = mGridLayout
    adapter = mAdapter
}
}
mBinding.btnSave.setOnClickListener {
val store = StoreEntity(name = mBinding.etName.text.toString().trim())

Thread{
    StoreApplication.database.storeDao().addStore(store)
}.start()
mAdapter.add(store)
}
}*/

            mBinding.fab.setOnClickListener { launchEditFragment() }
 */
            private fun launchEditFragment(args: Bundle? = null) {

                if (args != null) fragment.arguments = args

                val fragment = EditStoreFragment()


                val fraManager = supportFragmentManager
                val fragmentTransaction = fraManager.beginTransaction()

                fragmentTransaction.add(R.id.containerMain, fragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                //mBinding.fab.hide()
                hideFab()
            }
private fun getStores(){
doAsync {
    val stores = StoreApplication.database.storeDao().getAllStores()
    uiThread {
        updateStore(storeEntity)
    }

    override fun addStore(storeEntity: StoreEntity) {
        mAdapter.add(storeEntity)
    }

    override fun onClick(storeId: Long) {
        val args = Bundle()
        args.putLong(getString(R.string.arg_id), storeId)

        launchEditFragment(args)
    }
}

}
/*
* OnClickListener
* */
override fun onClick(store: StoreEntity) {
}

override fun onFavoriteStore(storeEntity: StoreEntity) {
storeEntity.isFavorite = !storeEntity.isFavorite
doAsync {
    StoreApplication.database.storeDao().updateStore(storeEntity)
    uiThread {
        mAdapter.update(storeEntity)
    }
}
}
}
override fun onDeleteStore(storeEntity: StoreEntity) {
    val items = arrayOf("Eliminar","Llamar","Ir al sitio web")

    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.dialog_options_title)
        .setItems(items, { dialogInterface, i ->
            when(i){
                0 -> confirmDelete(storeEntity)
                1-> dial(storeEntity.phone)
                2 -> goToWebsite(storeEntity.website)
            }
        })
        .show()
    private fun dial(phone: String){
        val callIntent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$phone")
        }

        if (callIntent.resolveActivity(packageManager) != null)
            startActivity(callIntent)
        else
            Toast.makeText(this,R.string.main_error_no_resolve,Toast.LENGTH_LONG).show()
    }
    private fun goToWebsite(website: String){
        if (website.isEmpty()){
            Toast.makeText(this,R.string.main_error_no_website, Toast.LENGTH_LONG).show()
        }else {
            val websiteIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(website)
            }

            if (websiteIntent.resolveActivity(packageManager) != null)
                startActivity(websiteIntent)
            else
                Toast.makeText(this,R.string.main_error_no_resolve,Toast.LENGTH_LONG).show()
        }
    }
}

        private fun confirmDelete(storeEntity: StoreEntity){
}
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.dialog_delete_title)
        .setPositiveButton(R.string.dialog_delete_confirm, { dialogInterface, i ->
            doAsync {
                StoreApplication.database.storeDao().deleteStore(storeEntity)
                uiThread {
                    mAdapter.delete(storeEntity)
                }
            }
        })
        .setNegativeButton(R.string.dialog_delete_cancel,null)
        .show()

    mAdapter.update(storeEntity)
        startIntent(websiteIntent)
    }
}

private fun startIntent(intent: Intent){
    if (intent.resolveActivity(packageManager) != null)
        startActivity(intent)
    else
        Toast.makeText(this,R.string.main_error_no_resolve,Toast.LENGTH_LONG).show()
}
}}