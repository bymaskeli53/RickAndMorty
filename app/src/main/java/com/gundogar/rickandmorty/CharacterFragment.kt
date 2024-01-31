package com.gundogar.rickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.gundogar.rickandmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private var binding: FragmentCharacterBinding by autoCleared()

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        val dividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        binding.recyclerview.addItemDecoration(dividerItemDecoration)

        viewModel.characters.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.swipeRefreshLayout.isRefreshing = true
                    binding.progressBar.visibility = View.VISIBLE

                }

                is Resource.Success -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility = View.INVISIBLE
//                    val adapter = CharacterAdapter(resource.data) { position ->
//                       val action = CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(resource.data.results[position])
//                        findNavController().navigate(action)
//
//                    }
                    val adapter = CharacterAdapter { position ->
                        val action =
                            CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(
                                resource.data.results[position]
                            )
                        findNavController().navigate(action)

                    }

                    binding.recyclerview.adapter = adapter
                    adapter.submitList(resource.data.results)

//                    val dividerItemDecoration =
//                        DividerItemDecoration(requireContext() , DividerItemDecoration.VERTICAL)
//                    binding.recyclerview.addItemDecoration(dividerItemDecoration)

                }

                is Resource.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext() , resource.message , Toast.LENGTH_LONG).show()
                    val adapter = CharacterAdapter { position ->
                        val action =
                            CharacterFragmentDirections.actionCharacterFragmentToDetailFragment(
                                viewModel.cachedData[position]
                            )
                        findNavController().navigate(action)
                    }
                    adapter.submitList(viewModel.cachedData)
                    binding.recyclerview.adapter = adapter
//                    val dividerItemDecoration = DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
//                    binding.recyclerview.addItemDecoration(dividerItemDecoration)



                }
            }
            binding.swipeRefreshLayout.setOnRefreshListener {
                viewModel.getAllCharacters()
            }


        }
        super.onViewCreated(view , savedInstanceState)
    }


}