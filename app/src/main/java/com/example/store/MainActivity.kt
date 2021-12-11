import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stores_da.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            mBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            setupRecyclerView()
        }

        private fun setupRecyclerView() {
            mAdapter = StoreAdapter(mutableListOf(), this)
            mGridLayout = GridLayoutManager(this, 2)

            mBinding.recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = mGridLayout
                adapter = mAdapter
            }
        }
        mBinding.btnSave.setOnClickListener {
            val store = StoreEntity(name = mBinding.etName.text.toString().trim())
            mAdapter.add(store)
        }

        /*
        * OnClickListener
        * */
        override fun onClick(store: StoreEntity) {
        }
    }