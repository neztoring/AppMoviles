package com.example.appmoviles.ui.album

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appmoviles.R
import com.example.appmoviles.models.Album
import com.example.appmoviles.viewmodels.AlbumViewModel
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer


class AlbumCreationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {


    private lateinit var viewModel: AlbumViewModel
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        setContentView(R.layout.activity_album_creation)
        addArraysSpinners()
        createDatePicker()
        createButton()

    }

    fun addArraysSpinners() {
        val genreSpinner: Spinner = findViewById(R.id.genre_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.genre_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genreSpinner.adapter = adapter
        }
        genreSpinner.setSelection(0)
        val recordLabelSpinner: Spinner = findViewById(R.id.record_label_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.record_label_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            recordLabelSpinner.adapter = adapter
        }
        recordLabelSpinner.setSelection(0)
    }

    private fun createButton() {
        val button: Button = findViewById(R.id.button_save_album)
        button.setOnClickListener {
            this.saveAlbum()
        }

    }

    private fun createDatePicker() {
        findViewById<TextView>(R.id.albumReleaseDate).setOnClickListener {
            DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
        val releaseDateTextView = findViewById<TextView>(R.id.albumReleaseDate)
        releaseDateTextView.setError(null)

    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp: Long) {
        findViewById<TextView>(R.id.albumReleaseDate).text = formatter.format(timestamp)
        Log.i("Formatting", timestamp.toString())
    }

    private fun saveAlbum() {
        val nameTextView = findViewById<TextInputEditText>(R.id.editTextAlbumName)
        val coverTextView = findViewById<TextInputEditText>(R.id.editTextAlbumCoverage)
        val descriptionTextView = findViewById<TextInputEditText>(R.id.editTextAlbumDescription)
        val releaseDateTextView = findViewById<TextView>(R.id.albumReleaseDate)
        val genreSpinner = findViewById<Spinner>(R.id.genre_spinner)
        val recordLabelSpinner = findViewById<Spinner>(R.id.record_label_spinner)

        val formatterAux = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000Z", Locale.US)
        val defaultReleaseText: String = getString(R.string.album_release_date)
        val nameValidation = nameTextView.text.toString().isEmpty() || nameTextView.text.toString().length < 3
        val coverValidation = coverTextView.text.toString().isEmpty() || coverTextView.text.toString().length < 3
        val descriptionValidation = descriptionTextView.text.toString().isEmpty() || descriptionTextView.text.toString().length < 3
        if (nameValidation){
            nameTextView.setError("El campo debe diligenciado y cumplir con las condiciones")
        }
        if (coverValidation){
            coverTextView.setError("El campo debe diligenciado y cumplir con las condiciones")
        }
        if (descriptionValidation){
            descriptionTextView.setError("El campo debe diligenciado y cumplir con las condiciones")
        }
        if (releaseDateTextView.text.equals(defaultReleaseText)){
            releaseDateTextView.setError("Debe seleccionar una fecha")
        }
        if ( nameValidation || coverValidation || descriptionValidation || releaseDateTextView.text.equals(defaultReleaseText)
        ) {
            Toast.makeText(this, "Algunos campos no cumplen las condiciones o no fueron registrados.", Toast.LENGTH_LONG).show()
        } else {

            viewModel.addAlbum(nameTextView.text.toString(),coverTextView.text.toString(),descriptionTextView.text.toString(),formatterAux.format(calendar.timeInMillis).toString().replace(" ", "T"),genreSpinner.selectedItem.toString(),recordLabelSpinner.selectedItem.toString())

            viewModel.album.observe(this, Observer<Album> { t ->
                album = t
                Log.println(Log.INFO,"Informacion Album",album.albumId.toString())
                onSuccess()
            })

            viewModel.eventNetworkError.observe(this, Observer<Boolean> { isNetworkError ->
                if(isNetworkError)  onError()
            })
        }

    }

    private fun onSuccess() {
        findViewById<TextInputEditText>(R.id.editTextAlbumName).setText("")
        findViewById<TextInputEditText>(R.id.editTextAlbumCoverage).setText("")
        findViewById<TextInputEditText>(R.id.editTextAlbumDescription).setText("")

        Toast.makeText(this, "Albúm guardado con éxito!", Toast.LENGTH_LONG).show()
    }

    private fun onError() {
        Toast.makeText(this, "Error al guardar el albúm", Toast.LENGTH_LONG).show()
    }


}