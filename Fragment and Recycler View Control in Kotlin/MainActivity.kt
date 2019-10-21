package com.apps.guida.cs184_fragments_demo

import android.content.res.Configuration
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK
import android.widget.Toast
import android.R.attr.screenSize
import android.content.Context
import android.os.PersistableBundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ListFragment
import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity(), DemoListFragment.OnImageSelectedListener {

    var imageFrag: DemoImageFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageFrag = supportFragmentManager.findFragmentById(R.id.viewer) as DemoImageFragment?
        Log.d("DEMO", "Creating again ...")
    }

    //Use this method to execute logic when a fragment is attached to an activity
    //Here we bind the fragments onImageSelectedListener to the parent activity
    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is DemoListFragment) {
            fragment.setOnImageSelectedListener(this)
        }
    }

    //Implement interface of fragment
    //This method executes when the corresponding function is called by the fragment
    override fun onImageSelected(image_id: Int) {
        Log.d("DEMO", "Activity received action from image $image_id")

        //If the fragment is available we are in large screen mode
        //Update the image directly in this case
        if (imageFrag != null) {
            imageFrag?.updateImage(image_id)
        }
        //We are in small screen mode
        //Swap fragment that is in view
        else {
            val newImageFragment = DemoImageFragment()
            val args = Bundle()
            args.putInt("image_id", image_id)
            newImageFragment.arguments = args

            val transaction = supportFragmentManager.beginTransaction()

            //Replace current visible fragment with new fragment
            //Add transaction to back of stack so they can navigate back
            transaction.replace(R.id.list, newImageFragment)
            transaction.addToBackStack(null)

            //Commit transaction
            transaction.commit()
        }
    }
}

//Recycler view adapter
class MyAdapter(private val myDataset: Array<Int>, val adapterInterface: ImageAdapterInterface) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    //Define a view holder
    class MyViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout)

    //This method is fired when an image in the recyler view is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_view, parent, false) as ConstraintLayout

        //Return view holder and add listener for item selection
        //listen method is an extension function
        return MyViewHolder(imageView).listen { pos, type ->
            val item = myDataset[pos]
            Log.d("DEMO", "Adapter received action from image $item")

            //Call method in fragment through interface
            this.adapterInterface?.OnItemClicked(item)
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.layout.findViewById<TextView>(R.id.textView2).text = myDataset[position].toString()
        holder.layout.findViewById<ImageView>(R.id.imageView3).setImageResource(myDataset[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    //Extend view holder with listen function
    //A convenient way to fire the listen function above with position and type of selected item
    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }

        return this
    }

    //Interface that parent of adapter must implement
    //In this case it is the fragment holding it
    public interface ImageAdapterInterface {
        fun OnItemClicked(item_id: Int)
    }
}

//Fragment for scrolling image view
class DemoListFragment : Fragment() {
    //Define interface that parent activity must implement
    //In this case it is the activity_main
    private var callback: OnImageSelectedListener? = null

    //Provide a method to set the callback to the parent
    fun setOnImageSelectedListener(callback: OnImageSelectedListener) {
        this.callback = callback
    }

    //The interface that the parent must implement
    //In this case it is activity_main
    interface OnImageSelectedListener {
        fun onImageSelected(position: Int)
    }

    //When an item is selected it triggers the callback method in activity_main
    fun onItemSelect(position: Int) {
        // Send the event to the host activity
        callback?.onImageSelected(position)
    }

    //Define recycler view components
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    //Inflate image list fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.demo_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Instantiate a new interface for the adapter
        val adapterInterface = object : MyAdapter.ImageAdapterInterface {

            //This will be triggered when the corresponding method is called in the adapter
            //In this case it simply passes the id to the callback that goes to the main_activity
            override fun OnItemClicked(item_id: Int) {
                Log.d("DEMO", "Fragment received action from image $item_id")
                onItemSelect(item_id)
            }
        }

        val myDataset: Array<Int> = arrayOf(
            R.drawable.ls1,
            R.drawable.ls2,
            R.drawable.ls3,
            R.drawable.ls4,
            R.drawable.ls5,
            R.drawable.ls6,
            R.drawable.ls7,
            R.drawable.ls8,
            R.drawable.ls9,
            R.drawable.dog1
        )

        //Define a view manager
        viewManager = LinearLayoutManager(context)
        //Define a view adapter and give it the interface from above
        viewAdapter = MyAdapter(myDataset, adapterInterface)

        //Find recycler view and bind to manager and adapter
        recyclerView = view.findViewById(R.id.my_recycler_view) as RecyclerView
        recyclerView
            .apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }
    }
}

//Fragment for displaying image when selected
class DemoImageFragment : Fragment() {

    var imageView: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.demo_image_fragment, container, false)

        //Set image appropriately if the fragment is created with an image specified
        //In our case this is done when we are in portrait mode on the small screen
        imageView = view.findViewById(R.id.imageView)
        val bundle: Bundle? = arguments
        if(imageView != null && bundle?.getInt("image_id") != null){
            imageView?.setImageResource(bundle.getInt("image_id"))
        }
        return view
    }

    //A function to update the image of the image fragment
    //In our case this is done when using a table on a big screen
    fun updateImage(image_id: Int) {
        Log.d("DEMO", "Updating image with $image_id")
        imageView?.setImageResource(image_id)
    }
}


