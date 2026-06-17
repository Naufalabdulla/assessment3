package com.naufalabdullah0109.assessment3.ui.screen


import android.graphics.Bitmap
import android.util.Base64
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalabdullah0109.assessment3.model.Komik
import com.naufalabdullah0109.assessment3.network.KomikApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

enum class ApiStatus { LOADING, SUCCESS, FAILED }

class MainViewModel : ViewModel() {

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var data = mutableStateOf<List<Komik>>(emptyList())
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = KomikApi.service.getKomik(userId)
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                status.value = ApiStatus.FAILED
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toBase64(): String {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun saveData(userId: String, judul: String, chapter: Int, chapterDibaca: Int, bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val komikBaru = Komik(
                    userId = userId,
                    judul = judul,
                    chapter = chapter,
                    chapterDibaca = chapterDibaca,
                    gambar = bitmap.toBase64(),
                    id = "",
                    mine = 0
                )
                KomikApi.service.postKomik(komikBaru)
                retrieveData(userId)
            } catch (e: Exception) {
                errorMessage.value = "Gagal menyimpan: ${e.message}"
            }
        }
    }

    fun clearMessage() { errorMessage.value = null }

    fun deleteData(userId: String, komikId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                KomikApi.service.deleteKomik(komikId)
                retrieveData(userId)
            } catch (e: Exception) {
                errorMessage.value = "Gagal menghapus: ${e.message}"
            }
        }
    }
}