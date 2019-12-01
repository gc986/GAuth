package ru.gc986.gauth.v.searchUserGithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.gc986.dataprovider.imageDownloader.ImageDownloadHelper
import ru.gc986.gauth.R
import ru.gc986.models.GitUserResult

class UserAdapter(private val context: Context, val users: ArrayList<GitUserResult.GitUser>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.adapter_github_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.tvLogin.text = user.login
        holder.tvScore.text = user.score.toString()
        user.avatar?.let {avatar ->
            ImageDownloadHelper(context)
                .loadRoundImage(avatar, holder.ivAvatar)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        internal val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
        internal val tvLogin = view.findViewById<TextView>(R.id.tvLogin)
        internal val tvScore = view.findViewById<TextView>(R.id.tvScore)
    }

}