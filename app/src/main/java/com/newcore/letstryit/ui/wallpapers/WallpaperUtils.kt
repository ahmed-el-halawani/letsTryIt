package com.newcore.letstryit.ui.wallpapers

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import androidx.core.content.ContextCompat.getSystemService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


object WallpaperUtils {
    fun getAllData(): List<PictureEntity> {
        val listType: Type = object : TypeToken<ArrayList<PictureEntity>>() {}.type
        return Gson().fromJson<List<PictureEntity>>(jsonData, listType)
    }

    fun imageUrlFromImageId(id: String): Uri {
        return Uri.parse("https://drive.google.com/uc?id=$id")

    }

    fun imageUrlStringFromImageId(id: String): String {

        return "https://drive.google.com/uc?id=$id"

    }
//
    //    fun imageFromImageId(id: String): String {
    //        return "http: //drive.google.com/uc?id=$id")
    //
    //    }
}

data class PictureEntity(
    val id: String,
    val cid: String,
    val isFavourite: Boolean = false,
)

val jsonData = """[{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1vHnxMN9qfROGzYWhP5S14k3WrV-i-VMa","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1RmafXM9hUux2ylvX7BE-XxH_TybBSVIZ","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1V-DlHmKdhGG-F3FMWGTg_BqFsCYpCPbA","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1NMn3YSWYqeBLhhhEfSq85FdeKXKPQfZO","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1ZVkrn11Zp0Mu3v4mdjUukOkYkZlp_0MB","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1iz-G03OzQkXhBVLJ4G3IFuMBrdbh10eJ","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1h2myawMCAdoWlvm4eoe_720GJhFe7Vuz","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1QH7rMi0Z3sPD5GlD7Pax7CuvJe9z8Jgt","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1XVBBl33qeZCD3pywBZfAhIQrz1oxOqD_","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1tpRUTYvAWkcUd5J4PagyO_B6y49feMdT","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1v4FlbbSwjHTrUpX0oH77ynq1RX1izmJG","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1EzmEQQUIuKsPCxI8BF0HHynAOOik1U5k","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1Vco_3e--OiBlf_SAOCsQVtyHwaxUOXRo","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1flK_bYaRZdP4huT6PDAcOgSEaKh-jh9N","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1QxpsJEkMjwhF_1MBxLaLD9Gk2FRkmdzp","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1Ynt8t5qjkuDaTGVYieGs4oO-T5WGWWtH","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"11bWEc50EuAOxDsf0aCbwRMCI9EItBsCE","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"112lXatpuXKqDGCmqMKQaWP6PXp0SAxeM","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1TwMEJRWcw2L-wPVQPEZuWfFdg04tLEXb","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1L9jtoLATHcWy7j6_Qq05-9LeDtK4BjRn","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1L4B9tLocOjQbJM89I3Z_mqtiU1hC7-f6","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1bT4Vi8TLSSsml023QPUDo08tFwyJqpZG","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1Yute5B8_yTGfaOUpEF6xCTSBagrr3u7p","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1qtrpZ1RhKpKgCmgwEcK1DFs36gUELcCC","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1F7B-uznFxT_pQoPO_Zmsty46wri3gvr9","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1FEuQCxL_Ubdr_wsrH54-_t-SzO11xs8D","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1f4QFzb60xAY6aHwUVEgZ66YvzWxG9e9w","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1wjaZwZdtOhkDWPLmQ2YxBDtwe4ZABCWV","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1kXJRW1jzk84-q8c_igOBZnl925yER6as","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"11gEuphnDGCYONCOlqh7Reay7wnNOduIX","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1EGv0vKymZRegQsKnrWS6FCQ9BBK-YqZ7","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"16ezwke05-WV0N7NTiOJHSjn-82v__q8p","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1eel73cdb79A-TR_IiNlNDdaSEu6zI52w","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1ef-viduBai4sOETmzA3O5DFXhbDJAg-T","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1smKl1gVPjd1-33N-rlQVCtgX4rUNPu7g","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"16L4TK_qUUAjiz1JaWP23uoLwJvoo37z3","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"1_BU3ylyCS9KiP9vVd6ODzGMMdjPGsqSI","isFavourite":false},{"cid":"1d4nTw4XZyZuUm8niDQ2bUv5XUrkGw3YB","id":"10-5QuZmxjTrS1LIfoOcEXWz_x2U7sUAK","isFavourite":false}]"""