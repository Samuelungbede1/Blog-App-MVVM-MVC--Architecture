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


    class CommentAdapter (var context: Context, var data: ArrayList<Comment>):
        RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

        class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
            var comment: TextView =  view.findViewById(R.id.tv_comment)
        }

        override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
            var view : View = LayoutInflater.from(parent.context).inflate(R.layout.single_comment_layout,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            var currentComment : Comment = data[position]
            holder.comment.text = currentComment.body
        }


        /**This function returns the size of the elements in the recylcer view*/
        override fun getItemCount(): Int {
            return data.size
        }


        /**This function adds pokemon to the recycler view*/
        fun addittionalListOfComment (listOfComment: ArrayList<Comment>) {
            data.clear()
            data.addAll(listOfComment)
            notifyDataSetChanged()
        }


        fun addComment(comment: Comment){
            data.add(comment)
            notifyDataSetChanged()
        }
    }



