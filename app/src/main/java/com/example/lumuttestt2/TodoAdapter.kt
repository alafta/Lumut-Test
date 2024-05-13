package com.example.lumuttestt2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    private var todoList: List<Todo> = listOf()

    fun setData(todos: List<Todo>) {
        todoList = todos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.bind(todo)
        holder.itemView.setOnClickListener {
            onItemClick(todo.id) // Pass the ID of the clicked item to the onItemClick lambda
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userIdTextView: TextView = itemView.findViewById(R.id.userid)
        private val idTextView: TextView = itemView.findViewById(R.id.id)
        private val titleTextView: TextView = itemView.findViewById(R.id.title)
        private val completedTextView: TextView = itemView.findViewById(R.id.completed)

        fun bind(todo: Todo) {
            userIdTextView.text = "User ID: ${todo.userId}"
            idTextView.text = "ID: ${todo.id}"
            titleTextView.text = "Title: ${todo.title}"
            completedTextView.text = "Completed: ${todo.completed}"
        }
    }
}
