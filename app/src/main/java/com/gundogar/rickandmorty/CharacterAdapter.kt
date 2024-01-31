package com.gundogar.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.gundogar.rickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter(val onItemClick: (position: Int) -> Unit) :
    ListAdapter<Character , CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder , position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

    }


    inner class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name
                tvInfo.text = character.status
                tvType.text = character.type
                ivCharacter.load(character.image) {
                    placeholder(R.drawable.ic_launcher_background)
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                if (character.status == LifeStatus.ALIVE.status) {
                    ivAlive.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context ,
                            R.color.color_alive
                        )
                    )
                } else if (character.status == LifeStatus.UNKNOWN.status) {
                    ivAlive.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context ,
                            R.color.color_unknown
                        )
                    )
                } else {
                    ivAlive.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context ,
                            R.color.color_dead
                        )
                    )
                }

            }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character , newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character , newItem: Character): Boolean {
        return oldItem == newItem
    }
}

// TODO: Burası list adapter olarak düzenlenecek.