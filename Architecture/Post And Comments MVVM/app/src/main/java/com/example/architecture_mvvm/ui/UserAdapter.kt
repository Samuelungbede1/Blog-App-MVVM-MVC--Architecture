package com.example.architecture_mvvm.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_mvvm.R
import com.example.architecture_mvvm.model.Users


class UserAdapter(var context: Context, var data: ArrayList<Users>, val listener: OnItemClickListener):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


   inner class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        var userID: TextView =  view.findViewById(R.id.tv_UserID)
        var userName: TextView =  view.findViewById(R.id.tv_UserName)
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.single_user_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentUser : Users = data[position]
        holder.userID.text = currentUser.id.toString()
        holder.userName.text = currentUser.name


        /**Setting click listener to the item in the recyclerview*/
        holder.itemView.setOnClickListener {
            listener.OnItemClick(currentUser)
        }
    }



    /**This function returns the size of the elements in the recylcer view*/
    override fun getItemCount(): Int {
        return data.size
    }


    /**This function adds pokemon to the recycler view*/
    fun addittionalListOfUsers ( listOfUsers: ArrayList<Users>) {
        data.clear()
        data.addAll(listOfUsers)
        notifyDataSetChanged()
    }






    /**The interface class for the on click listener, the function(s) inside
     * should be overridden in the class implementing this class*/
    interface OnItemClickListener {
        fun OnItemClick(user: Users)
    }
}