package io.github.achmadhafid.sample_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.achmadhafid.zpack.delegate.lifecycleValue
import io.github.achmadhafid.zpack.ktx.setToolbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var myInt: Int? by lifecycleValue { null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(R.id.toolbar)

        myInt = null
    }
}
