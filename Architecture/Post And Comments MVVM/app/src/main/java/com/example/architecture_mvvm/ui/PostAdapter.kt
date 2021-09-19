package com.example.architecture_mvvm.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_mvvm.R
import com.example.architecture_mvvm.model.Comment
import com.example.architecture_mvvm.model.Post
import com.example.architecture_mvvm.model.Users


class PostAdapter (var context: Context, var data: ArrayList<Post>, val listener: OnItemClickListener):
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        var postTitle: TextView =  view.findViewById(R.id.tv_PostTitle)
        var postBody: TextView =  view.findViewById(R.id.tv_postBody)
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.single_post_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentPost : Post = data[position]
        holder.postTitle.text = currentPost.title
        holder.postBody.text =currentPost.body


        /**Setting click listener to the item in the recyclerview*/
        holder.itemView.setOnClickListener {
            listener.OnItemClick(currentPost)
        }
    }


    /**This function returns the size of the elements in the recylcer view*/
    override fun getItemCount(): Int {
        return data.size
    }


    /**This function adds pokemon to the recycler view*/
    fun addittionalListOfPost ( listOfPost: ArrayList<Post>) {
        data.clear()
        data.addAll(listOfPost)
        notifyDataSetChanged()
    }


    fun addPost(post: Post){
        data.add(post)
        notifyDataSetChanged()
    }


    /**The interface class for the on click listener, the function(s) inside
     * should be overridden in the class implementing this class*/
    interface OnItemClickListener {
        fun OnItemClick(post: Post)
    }
}

