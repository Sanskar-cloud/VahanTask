package com.example.taskk

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vaaahantaskapp.Universities


class UniversityAdapter(private val UniversityArrayList: List<Universities>,
                        private val context: Context
) :
    RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.college_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val university = UniversityArrayList[position]
        holder.textViewName.text = university.name
        holder.textViewCountry.text = university.country
        holder.textViewWebPage.text = "Website"

        holder.textViewWebPage.setOnClickListener {
            val webPageUrl = university.web_pages[0]
            if (!webPageUrl.isNullOrEmpty()) {
                val intent = Intent(context, WebActivity::class.java)
                intent.putExtra(WebActivity.EXTRA_WEB_PAGE_URL, webPageUrl)
                context.startActivity(intent)
            }
            else {
                // Handle the case where webPageUrl is empty or null
                Toast.makeText(context, "Website URL is not available", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return UniversityArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.idTVName)
        val textViewCountry: TextView = itemView.findViewById(R.id.idTVCountry)
        val textViewWebPage: TextView = itemView.findViewById(R.id.textViewWebPage)
    }
}
