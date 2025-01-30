import androidx.compose.ui.window.ComposeUIViewController
import com.llinsoft.finalchat.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
