package com.example.appmoviles.ui.album

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.appmoviles.R
import com.example.appmoviles.network.NetworkServiceAdapter
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class AlbumCreationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val recordLabelSpinner: Spinner = findViewById(R.id.record_label_spinner)
        ArrayAdapter.createFromResource(
            this, R.array.record_label_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            recordLabelSpinner.adapter = adapter
        }
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
        val coverageTextView = findViewById<TextInputEditText>(R.id.editTextAlbumCoverage)
        val descriptionTextView = findViewById<TextInputEditText>(R.id.editTextAlbumDescription)
        val genreSpinner = findViewById<Spinner>(R.id.genre_spinner)
        val recordLabelSpinner = findViewById<Spinner>(R.id.record_label_spinner)
        val bodyAlbumRequest = JSONObject()

        val formatterAux = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000Z", Locale.US)

        try {
            bodyAlbumRequest.put("name", nameTextView.text.toString())
            bodyAlbumRequest.put("cover", coverageTextView.text.toString())
            bodyAlbumRequest.put("description", descriptionTextView.text.toString())
            bodyAlbumRequest.put(
                "releaseDate", formatterAux.format(calendar.timeInMillis).toString()
                    .replace(" ", "T")
            )
            bodyAlbumRequest.put("genre", genreSpinner.selectedItem.toString())
            bodyAlbumRequest.put("recordLabel", recordLabelSpinner.selectedItem.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        NetworkServiceAdapter.getInstance(application).postAlbum(bodyAlbumRequest,
            {
                onSuccess()
            },
            {
                onError()
            }
        )

    }

    private fun onSuccess() {
        Toast.makeText(this, "Albúm guardado con éxito!", Toast.LENGTH_LONG).show()
    }

    private fun onError() {
        Toast.makeText(this, "Error al guardar el albúm", Toast.LENGTH_LONG).show()
    }


}