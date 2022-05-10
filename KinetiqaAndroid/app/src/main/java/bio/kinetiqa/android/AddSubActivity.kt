package bio.kinetiqa.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import bio.kinetiqa.android.ui.dashboard.DashboardFragment

class AddSubActivity : AppCompatActivity() {

    lateinit var add_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_sub)

        add_button = findViewById(R.id.finish_add)

        add_button.setOnClickListener(View.OnClickListener {
            //TODO: add new element in information base
            //TODO: question about update of information inside Dashboard
            finish()
        })
    }
}