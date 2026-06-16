package com.naufalabdullah0109.assessment3.ui.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.naufalabdullah0109.assessment3.model.Komik

class MainViewModel: ViewModel() {
    var data = mutableStateOf(emptyList<Komik>())
        private set
}