package sebgg.dev.workoutdiary.helpers

import android.view.View
import com.google.android.material.snackbar.Snackbar


fun showShortToast(view: View, text: String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
            .setAnchorView(view)
            .show()
}