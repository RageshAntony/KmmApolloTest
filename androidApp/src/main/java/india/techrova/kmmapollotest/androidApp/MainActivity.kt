package india.techrova.kmmapollotest.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import india.techrova.kmmapollotest.shared.Greeting
import android.widget.TextView
import india.techrova.kmmapollotest.GetAllCategoriesQuery
import india.techrova.kmmapollotest.shared.data.api.NetworkStatus
import india.techrova.kmmapollotest.shared.data.api.repo.service.CategoryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        GlobalScope.launch(Dispatchers.IO) {
            CategoryService().getAllCategories().collect {
                when (it) {
                    is NetworkStatus.Loading -> TODO()
                    is NetworkStatus.CustomSignal -> TODO()
                    is NetworkStatus.CustomSignalDetailed -> TODO()
                    is NetworkStatus.Failure -> {

                    }
                    is NetworkStatus.Data -> {

                        withContext(Dispatchers.Main) {
                            tv.text = it.data[0].name
                        }
                    }
                }
            }
        }

    }
}
