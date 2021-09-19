package com.example.architecturemvc.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturemvc.R
import com.example.architecturemvc.model.Posts
import com.example.architecturemvc.model.Users

class PostAdapter(var context: Context, var post: ArrayList<Posts>, var users: ArrayList<Users>,
                  var postImage: ArrayList<Int>, var userImage: ArrayList<Int>,
                  val listener: OnItemClickListener, var addComment: OnAddCommentClickListener):
    RecyclerView.Adapter<PostAdapter.ViewHolder>(){


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var postTitle: TextView =  view.findViewById(R.id.tv_CommentActivityPostTitle)
        var postBody: TextView =  view.findViewById(R.id.tv_postBodyCommentActivity)
        var postImage : ImageView = view.findViewById(R.id.iv_PostimageView)
        var userImage : ImageView = view.findViewById(R.id.iv_userImage)
        var commenterImage : ImageView = view.findViewById(R.id.iv_commenterImage)
        var commentIcon : ImageView = view.findViewById(R.id.iv_CommentIcon)
        var userName : TextView = view.findViewById(R.id.tv_CommenterUserName)
        var postText : TextView = view.findViewById(R.id.tv_postText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view: View = LayoutInflater.from(parent.context).inflate(R.layout
           .post_item_layout, parent,false)
           return ViewHolder(view)
    }

    override fun onBindViewHolder(postholder: ViewHolder, position: Int) {
        var currentUserName: Users = users[position]
        var currentPost: Posts = post[position]
        postholder.postTitle.text = currentPost.title
        postholder.postBody.text = currentPost.body
        postholder.postImage.setImageResource(postImage[position%postImage.size])
        postholder.userImage.setImageResource(userImage[position%userImage.size])
        postholder.commenterImage.setImageResource(userImage[position%3])
        postholder.userName.text = currentUserName.name


        /**Setting Onclick listener to the comment Icon and Post Text*/
        postholder.commentIcon.setOnClickListener{
            listener.OnItemClick(currentPost,currentUserName)
        }

        postholder.postText.setOnClickListener{
            addComment.addCommentClick(currentPost)
        }

    }

    override fun getItemCount(): Int {
       return post.size
    }


    /**This function adds Post to the recycler view*/
    fun addPost (listOfPost: ArrayList<Posts>) {
        post.clear()
        post.addAll(listOfPost)
        notifyDataSetChanged()
    }

    /**This function adds Users to the recycler view*/
    fun addUsers (listOfUsers: ArrayList<Users>) {
        users.clear()
        users.addAll(listOfUsers)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun OnItemClick(post: Posts, user: Users)
    }

    interface OnAddCommentClickListener {
        fun addCommentClick(post: Posts)
    }
}