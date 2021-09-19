package com.example.architecturemvc.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturemvc.R
import com.example.architecturemvc.model.Comments
import org.w3c.dom.Comment

class CommentAdapter(var comments: ArrayList<Comments>,var userImage: ArrayList<Int>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {


    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var commentBody : TextView = view.findViewById(R.id.tv_commentBody)
        var userName : TextView = view.findViewById(R.id.tv_CommenterUserName)
        var commenterImage : ImageView = view.findViewById(R.id.iv_userImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }


    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentComment: Comments = comments[position]
        holder.commentBody.text = currentComment.body
        holder.userName.text = currentComment.email
        holder.commenterImage.setImageResource(userImage[position%3])
    }


    override fun getItemCount(): Int {
        return comments.size
    }


    /**This function adds comments to the recycler view*/
    fun addComments (listOfComments: ArrayList<Comments>) {
        comments.clear()
        comments.addAll(listOfComments)
        notifyDataSetChanged()
    }

    fun addComment(comment: Comments){
        comments.add(comment)
        notifyDataSetChanged()
    }
}