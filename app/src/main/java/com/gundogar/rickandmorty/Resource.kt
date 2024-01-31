package com.gundogar.rickandmorty

sealed class Resource<out T> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String, val data: T? = null) : Resource<T>()
}


/*
class MyViewModel(private val myRepository: MyRepository) : ViewModel() {

    private val _dataState = MutableLiveData<Resource<MyData>>()
    val dataState: LiveData<Resource<MyData>> get() = _dataState

    fun fetchData() {
        _dataState.value = Resource.Loading() // Set loading state

        viewModelScope.launch {
            try {
                val result = myRepository.getData()
                _dataState.value = Resource.Success(result) // Set success state
            } catch (e: Exception) {
                _dataState.value = Resource.Error("An error occurred", null) // Set error state
            }
        }
    }
}
class MyFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataState.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state, optionally use resource.data if needed
                }
                is Resource.Success -> {
                    // Show success state, access resource.data for the successful data
                }
                is Resource.Error -> {
                    // Show error state, access resource.message and resource.data if needed
                }
            }
        })

        // Trigger data fetching
        viewModel.fetchData()
    }
}

 */